package pl.bak.pageRecognition.util;

import org.springframework.stereotype.Component;
import pl.bak.pageRecognition.dto.PageCategoryDto;
import pl.bak.pageRecognition.uri.body_from_request.Root;

@Component
public class DataRequestMapper {
    public PageCategoryDto requestBodyToDto(Root requestBody){
        PageCategoryDto pageCategoryDto = new PageCategoryDto();

        requestBody.getDomain().getCategories()
                .forEach(element -> {
                    pageCategoryDto.setCategory(element.getName());
                    pageCategoryDto.setConfidence(element.getConfidence());
                });

        return pageCategoryDto;
    }
}
