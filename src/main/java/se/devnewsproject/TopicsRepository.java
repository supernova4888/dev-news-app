package se.devnewsproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {
    // ! careful with the variables names. The "Name" in "findByName" has to be the same as in the Topic Class. The only difference is that is it Capitalized
    // Repository connects the Controller to the Spring and converts the Java code in the controller into SQL code so the database can proceed with the commands.
    // Alternatively, one could write SQL code directly in the controller. But using Spring makes it easier to use only Java for that.
    List<Topics> findByName(String name);

}
