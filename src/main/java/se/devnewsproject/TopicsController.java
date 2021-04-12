package se.devnewsproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicsController {

    TopicsRepository topicsRepository;
    ArticleRepository articleRepository;

    @Autowired
    public TopicsController(TopicsRepository topicsRepository, ArticleRepository articleRepository) {
        this.topicsRepository = topicsRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/topics")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        topicsRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    // todo: not tested
    @PostMapping("/articles/{id}/topics")
    public ResponseEntity<Topics> associateTopicWithArticleById(@PathVariable Long id, @RequestBody Topics topic) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        topicsRepository
                .findById(id)
                .orElse(topicsRepository.save(topic));
        topic.setArticle(article);
        topicsRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    // Return all topics
    @GetMapping("/topics")
    public List<Topics> listAllTopics(){
        List<Topics> topics = topicsRepository.findAll();
        return topics;
    }

    // return all topics associated w/ article given by articleID
    @GetMapping("/articles/{id}/topics")
    public ResponseEntity<Topics> getTopicsByArticleId(@PathVariable Long id) {
        Topics topic = topicsRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(topic);
    }

    


}
