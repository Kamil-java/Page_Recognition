package pl.bak.pageRecognition.domain;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.bak.pageRecognition.dto.PageCategoryDto;
import pl.bak.pageRecognition.model.PageCategory;
import pl.bak.pageRecognition.uri.CallerTheAPI;
import pl.bak.pageRecognition.uri.body_from_request.Root;
import pl.bak.pageRecognition.util.DataRequestMapper;

import java.util.Optional;

@Service
public class PageCategoryService {
    private final PageCategoryRepository pageCategoryRepository;
    private final DataRequestMapper dataRequestMapper;
    private final ModelMapper modelMapper;
    private final CallerTheAPI callerTheAPI;

    public PageCategoryService(PageCategoryRepository pageCategoryRepository, DataRequestMapper dataRequestMapper, ModelMapper modelMapper, CallerTheAPI callerTheAPI) {
        this.pageCategoryRepository = pageCategoryRepository;
        this.dataRequestMapper = dataRequestMapper;
        this.modelMapper = modelMapper;
        this.callerTheAPI = callerTheAPI;
    }

    public String getPageCategory(String websiteURL) {
        if (websiteURL.isEmpty()) {
            return null;
        }

        Optional<PageCategory> pageCategory = pageCategoryRepository.findByUrl(websiteURL);

        if (pageCategory.isPresent()) {
            return pageCategory.get().getCategory();
        }

        Root result = callerTheAPI.getResponseFromApi(websiteURL);

        if (responseIsOK(result)) {
            PageCategoryDto pageCategoryDto = dataRequestMapper.requestBodyToDto(result);

            return pageCategoryDto.getCategory();
        }

        return null;
    }

    public Optional<PageCategoryDto> saveWebsiteCategory(String websiteURL) {
        if (websiteURL.isEmpty()) {
            return Optional.empty();
        }

        Root result = callerTheAPI.getResponseFromApi(websiteURL);

        if (responseIsOK(result)) {
            PageCategoryDto pageCategoryDto = dataRequestMapper.requestBodyToDto(result);
            pageCategoryDto.setUrl(websiteURL);

            PageCategory pageCategory = modelMapper.map(pageCategoryDto, PageCategory.class);

            pageCategoryRepository.save(pageCategory);

            return Optional.of(pageCategoryDto);
        }

        return Optional.empty();
    }

    public Optional<PageCategoryDto> updatePageCategory(String category, String websiteURL) {
        if (category.isEmpty() || websiteURL.isEmpty()) {
            return Optional.empty();
        }
        Optional<PageCategory> pageCategory = pageCategoryRepository.findByUrl(websiteURL);

        System.out.println(pageCategory);

        if (pageCategory.isPresent()) {
            pageCategory.get().setCategory(category);
            pageCategoryRepository.save(pageCategory.get());

            return Optional.of(modelMapper.map(pageCategory.get(), PageCategoryDto.class));
        }
        System.out.println(pageCategory);

        return Optional.empty();
    }

    public void deleteWebsite(String websiteURL) {
        if (!websiteURL.isEmpty()) {
            Optional<PageCategory> pageCategory = pageCategoryRepository.findByUrl(websiteURL);

            if (pageCategory.isEmpty()) {
                throw new IllegalArgumentException("This Website doesn't exist in database!");
            }

            pageCategory.ifPresent(pc -> pageCategoryRepository.deleteById(pc.getId()));
            return;
        }

        throw new IllegalArgumentException("Website url can not be empty!");
    }

    private boolean responseIsOK(Root result) {
        return result != null && result.isSuccess();
    }
}
