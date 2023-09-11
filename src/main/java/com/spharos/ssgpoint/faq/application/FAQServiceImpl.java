package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.Exception.ResourceNotFoundException;
import com.spharos.ssgpoint.faq.domain.FAQ;
import com.spharos.ssgpoint.faq.domain.FAQCategory;
import com.spharos.ssgpoint.faq.dto.FAQDTO;
import com.spharos.ssgpoint.faq.infrastructure.FAQCategoryRepository;
import com.spharos.ssgpoint.faq.infrastructure.FAQRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;
    private final FAQCategoryRepository faqCategoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void createFAQ(FAQDTO faqDTO) {
        FAQCategory category = faqCategoryRepository.findById(faqDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다. " + faqDTO.getCategoryId()));


        if (category.getParentCategory() == null) {
            throw new ResourceNotFoundException("소분류 값을 선택해주세요");
        }

        FAQ faq = modelMapper.map(faqDTO, FAQ.class);
        faq.setCategory(category);

        faqRepository.save(faq);
    }



    @Override
    public void deleteFAQ(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new ResourceNotFoundException("FAQ를 찾을 수 없습니다.: " + id);
        }
        faqRepository.deleteById(id);
    }


    @Override
    public List<FAQDTO> getFAQsByCategory(Long categoryId, Long subCategoryId) {
        FAQCategory category = faqCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다.: " + categoryId));

        List<FAQCategory> relevantCategories = new ArrayList<>();
        if (subCategoryId != null) {
            FAQCategory subCategory = faqCategoryRepository.findById(subCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("서브카테고리를 찾을 수 없습니다.: " + subCategoryId));
            relevantCategories.add(subCategory);
        } else {
            relevantCategories.add(category);
            relevantCategories.addAll(getAllSubCategories(category));
        }

        List<FAQ> faqs = faqRepository.findByCategoryIn(relevantCategories);

        return faqs.stream()
                .map(faq -> modelMapper.map(faq, FAQDTO.class))
                .collect(Collectors.toList());
    }

    private List<FAQCategory> getAllSubCategories(FAQCategory category) {
        List<FAQCategory> subCategories = new ArrayList<>(category.getSubCategories());

        for (FAQCategory subCategory : category.getSubCategories()) {
            subCategories.addAll(getAllSubCategories(subCategory));
        }

        return subCategories;
    }

    @Override
    public List<FAQDTO> getAllFAQs() {
        List<FAQ> faqs = faqRepository.findAll();
        return faqs.stream()
                .map(faq -> modelMapper.map(faq, FAQDTO.class))
                .collect(Collectors.toList());
    }

    public List<FAQCategory> getMainCategories() {
        return faqCategoryRepository.findByParentCategoryIsNull();
    }

    public List<FAQCategory> getSubCategories(Long mainCategoryId) {
        return faqCategoryRepository.findByParentCategoryId(mainCategoryId);
    }


}
