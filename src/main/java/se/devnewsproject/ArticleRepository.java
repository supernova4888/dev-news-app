package se.devnewsproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// The objective of this interface: this is the class handling/taking the object and turning into database code. Because this is an interface we cannot create an instance of this interface. But the controller is able to construct an item of this class because of Spring.

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
