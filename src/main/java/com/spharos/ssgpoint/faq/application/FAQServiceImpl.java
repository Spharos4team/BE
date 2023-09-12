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

        if (category.getParentCategory() != null) {
            FAQ faq = modelMapper.map(faqDTO, FAQ.class);
            faq.setCategory(category);

            faqRepository.save(faq);
        } else {
            throw new ResourceNotFoundException("소분류 값을 선택해주세요");
        }
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
                .orElseThrow(() -> new ResourceNotFoundException("카테고리를 찾을 수 없습니다. ID: " + categoryId));

        List<FAQCategory> relevantCategories = new ArrayList<>();
        if (subCategoryId != null) {
            FAQCategory subCategory = faqCategoryRepository.findById(subCategoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("서브카테고리를 찾을 수 없습니다. ID: " + subCategoryId));
            relevantCategories.add(subCategory);
        } else {
            relevantCategories.add(category);
            relevantCategories.addAll(faqCategoryRepository.findByParentCategory(category));
        }

        if (relevantCategories.isEmpty()) {
            throw new ResourceNotFoundException("관련 카테고리를 찾을 수 없습니다.");
        }

        List<FAQ> faqs = faqRepository.findByCategoryIn(relevantCategories);

        return faqs.stream()
                .map(faq -> {
                    FAQDTO faqDTO = modelMapper.map(faq, FAQDTO.class);
                    FAQCategory parentCategory = faq.getCategory().getParentCategory();
                    if (parentCategory != null) {
                        faqDTO.setParentCategoryName(parentCategory.getName());
                    }
                    return faqDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FAQDTO> getAllFAQs() {
        List<FAQ> faqs = faqRepository.findAll();

        return faqs.stream()
                .map(faq -> {
                    FAQDTO faqDTO = modelMapper.map(faq, FAQDTO.class);
                    if (faq.getCategory().getParentCategory() != null) {
                        faqDTO.setParentCategoryName(faq.getCategory().getParentCategory().getName());
                    }
                    return faqDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<FAQCategory> getParentCategories() {
        return faqCategoryRepository.findByParentCategoryIsNull();
    }

    @Override
    public List<FAQCategory> getSubCategories(Long parentId) {
        return faqCategoryRepository.findByParentCategory_Id(parentId);
    }



}
