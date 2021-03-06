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

    // this is what connects the repo-interface to this controller
    // Autowired: finds a class impl in Spring that impls the repository. So, spring creates classes auto when it runs
    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //get all articles
    @GetMapping("")
    public List<Article> listAllArticles(){
        List<Article> article;
        article = articleRepository.findAll();
        return article;
    }

    // post a new article
    // 1. call spring Class 'ResponseEntity'
    @PostMapping ("")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // call repository and save the article-external-input there
        // NOTE: you don't need to create an object when posting because you do it in the request body
        articleRepository.save(article);
        // connect the output to a HTTP response
        // ? why does ResponseEntity needs to be in the method declaration?
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // get specific article based on the provided id
    @GetMapping ("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }

    // update the given article
    @PutMapping ("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Article article = articleRepository.save(updatedArticle);
        return ResponseEntity.ok(article);
    }

    // delete the given article. After item is deleted it doesnt not update the generated ID
    @DeleteMapping ("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return ResponseEntity.ok(article);
    }


}


