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


    /**
     * FAQ를 생성합니다.
     *
     * @param faqDTO FAQ 생성에 필요한 정보가 담긴 DTO. categoryId 필드를 통해 카테고리 ID를 받습니다.
     * @throws ResourceNotFoundException 주어진 카테고리 ID를 가진 카테고리를 찾을 수 없을 때 발생합니다.
     */
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


    /**
     * 주어진 ID를 가진 FAQ를 삭제합니다.
     *
     * @param id 삭제할 FAQ의 ID.
     * @throws ResourceNotFoundException 주어진 ID를 가진 FAQ를 찾을 수 없을 때 발생합니다.
     */
    @Override
    public void deleteFAQ(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new ResourceNotFoundException("FAQ를 찾을 수 없습니다.: " + id);
        }
        faqRepository.deleteById(id);
    }

    /**
     * 주어진 카테고리 ID와 서브 카테고리 ID에 따라 FAQ 목록을 가져옵니다.
     *
     * @param categoryId    카테고리 ID.
     * @param subCategoryId 서브 카테고리 ID (null 가능).
     * @return 해당 카테고리와 서브 카테고리에 속하는 FAQ 목록.
     * @throws ResourceNotFoundException 주어진 카테고리 ID 또는 서브 카테고리 ID를 찾을 수 없을 때 발생합니다.
     */
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

    /**
     * 모든 FAQ 목록을 가져옵니다.
     *
     * @return 모든 FAQ 목록.
     */
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

    /**
     * 모든 부모 카테고리 목록을 가져옵니다.
     *
     * @return 부모 카테고리가 없는 카테고리 목록.
     */
    @Override
    public List<FAQCategory> getParentCategories() {
        return faqCategoryRepository.findByParentCategoryIsNull();
    }

    /**
     * 주어진 부모 ID를 가진 모든 서브 카테고리 목록을 가져옵니다.
     *
     * @param parentId 부모 카테고리의 ID.
     * @return 주어진 부모 ID를 가진 서브 카테고리 목록.
     */
    @Override
    public List<FAQCategory> getSubCategories(Long parentId) {
        return faqCategoryRepository.findByParentCategory_Id(parentId);
    }



}
