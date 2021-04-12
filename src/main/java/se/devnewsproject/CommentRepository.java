package se.devnewsproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findByauthorName(String authorName); OK
    //List<Comment> findauthorByName(String authorName); NOT ERROR OK
}
