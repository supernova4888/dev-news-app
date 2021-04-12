package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    // ?? why we need this one?
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }
//authorName
    //findByauthorName
    // ? actually the {articleID} here is just placeholder. It could have any name.dcom
    @PostMapping("/articles/{articleId}/comment")
    public ResponseEntity<Comment> createComment (@PathVariable Long articleID, @RequestBody Comment comment){
        Article article = articleRepository
                .findById(articleID)
                .orElseThrow(ResourceNotFoundException::new);
        comment.setOwner(article);
        commentRepository.save(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }


}
