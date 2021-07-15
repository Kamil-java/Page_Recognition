package pl.bak.pageRecognition.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bak.pageRecognition.dto.PageCategoryDto;

@RestController
@RequestMapping("/api")
public class PageCategoryController {
    private final PageCategoryService pageCategoryService;

    public PageCategoryController(PageCategoryService pageCategoryService) {
        this.pageCategoryService = pageCategoryService;
    }

    @GetMapping
    public String categoryInfo(@RequestParam("websiteUrl") String websiteUrl) {
        String category = pageCategoryService.getPageCategory(websiteUrl);

        if (category == null || category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return category;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PageCategoryDto save(@RequestParam("websiteUrl") String websiteUrl) {
        return pageCategoryService.saveWebsiteCategory(websiteUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping
    public PageCategoryDto updateCategory(@RequestParam("category") String category,
                                          @RequestParam("websiteUrl") String websiteUrl) {
        return pageCategoryService.updatePageCategory(category, websiteUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("websiteUrl") String websiteUrl) {
        try {
            pageCategoryService.deleteWebsite(websiteUrl);

        } catch (IllegalArgumentException e) {

            if (e.getMessage().equals("This Website doesn't exist in database!")) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
