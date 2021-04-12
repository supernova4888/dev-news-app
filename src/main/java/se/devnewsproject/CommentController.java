package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }
//authorName
    //findByauthorName
    // ? actually the {articleID} here is just placeholder. Could it have any name?
    // 'comments' come from Article - it is an endpoint
    @PostMapping("/articles/{articleId}/comment")
    public ResponseEntity<Comment> createComment (@PathVariable Long articleId, @RequestBody Comment comment){
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        comment.setArticle(article);
        commentRepository.save(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

//    @GetMapping("/articles/{articleId}/comment")
//    public ResponseEntity<Comment> getCommentByArticleId(@PathVariable Long id) {
//        Article article = articleRepository
//            .findById(id)
//            .orElseThrow(ResourceNotFoundException::new);
//        return ResponseEntity.ok(comment);
//}


}
