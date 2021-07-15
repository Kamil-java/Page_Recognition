package pl.bak.pageRecognition.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bak.pageRecognition.model.PageCategory;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PageCategoryRepository extends JpaRepository<PageCategory, Integer> {

    Optional<PageCategory> findByUrl(String url);
}
