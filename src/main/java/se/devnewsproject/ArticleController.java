package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    ArticleRepository articleRepository;

    // ? is this what connects the repo-interface to this controller?
    // Autowired: finds a class impl in Spring that impls the repository. So, spring creates classes auto when it runs
    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //get all articles
    // todo : ? how come im able to do List? Guess: I think its because Article is an Entity. So this gets some in-built methods from Spring
    @GetMapping("")
    public List<Article> listAllArticles(){
        List<Article> article = articleRepository.findAll();
        return article;
    }

    // todo : i didnt do a docker exec command - do i need to connect the database? only if you want to check

    // post a new article
    // 1. call spring Class 'ResponseEntity'
    // todo practice: I just remembered the 'save' part.
    @PostMapping ("")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // call repository and save the article-external-input there
        // todo NOTE: you don't need to create an object when posting
        articleRepository.save(article);
        // connect the output to a HTTP response
        // ? why does ResponseEntity needs to be in the method declaration?
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // get specific article based on the provided id
    @GetMapping ("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }

    // update the given article
    // todo: test it
    @PutMapping ("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return ResponseEntity.ok(article);
    }

    // delete the given article
    @DeleteMapping ("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return ResponseEntity.ok(article);
    }


}


