package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // todo: not tested
    // return all comments on article given by articleId.
    @GetMapping("/articles/{id}/comment")
    public ResponseEntity<Comment> getCommentByArticleId(@PathVariable Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(comment);
    }

    // return all comments made by author given by authorName.
    @GetMapping(value = "/comments", params = {"authorName"})
    public ResponseEntity<List<Comment>> viewAllCommentsByAuthor(@RequestParam String authorName) {
        List<Comment> articleComment = commentRepository.findByAuthorName(authorName);
        return ResponseEntity.ok(articleComment);
    }


    //create a new comment on article given by articleId.
    @PostMapping("/articles/{id}/comment")
    public ResponseEntity<Comment> createComment (@PathVariable Long id, @RequestBody Comment comment){
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        comment.setArticle(article);
        commentRepository.save(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }

    // todo: not tested
    // update the given comment.
    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment updatedComment) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return ResponseEntity.ok(updatedComment);
    }

    // delete the given comment. After item is deleted it doesnt not update the generated ID
    // todo: not tested
    @DeleteMapping ("/comment/{id}")
    public ResponseEntity<Comment> deleteArticle(@PathVariable Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
        return ResponseEntity.ok(comment);
    }

}
