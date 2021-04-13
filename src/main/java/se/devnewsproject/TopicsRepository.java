package se.devnewsproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicsRepository extends JpaRepository<Topics, Long> {
    // ! careful with the variables names.
    // builds a SQL code
    List<Topics> findByName(String name);

}
