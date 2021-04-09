package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ArticleController {

    ArticleRepository articleRepository;

    // ? is this what connects the repo-interface to this controller?
    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //get all articles
    @GetMapping("/articles")
    public List<Article> listAllArticles(){
        List<Article> article = articleRepository.findAll();
        return article;
    }


    // get specific article based on the provided id

    // post a new article
    // 1. call spring Class 'ResponseEntity'
    // todo practice: I just remembered the 'save' part.
    @PostMapping ("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // call repository and save the article-external-input there
        // todo NOTE: you don't need to create an object when posting
        articleRepository.save(article);
        // connect the output to a HTTP response
        // ? why does ResponseEntity needs to be in the method declaration?
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }


}

    // update the given article

    // delete the given article
}
