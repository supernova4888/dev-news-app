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

    // create a new topic
    // todo: disallow for duplication?
    @PostMapping("/topics")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        topicsRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    // todo: not tested
    @PostMapping("/articles/{id}/topics")
    public ResponseEntity<Article> associateTopicWithArticleById(@PathVariable Long id, @RequestBody Topics topic) {
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        topic = topicsRepository
                .findById(id)
                .orElse(topicsRepository.save(topic));
        article.getTopics().add(topic);
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
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

    // return all articles associated with the topic given by topicID
    @GetMapping("/topics/{id}/articles")
    public ResponseEntity<Article> getArticleByTopicId(@PathVariable Long id){
        Article article = articleRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }

    // update the given topic.
    @PutMapping("/topics/{id}")
    public ResponseEntity<Topics> updateTopic(@PathVariable Long id, @RequestBody Topics updatedTopic){
        Topics topics = topicsRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedTopic.setId(id);
        topicsRepository.save(updatedTopic);
        return ResponseEntity.ok(updatedTopic);
    }

    // delete the given topic.
    @DeleteMapping("/topics/{id}")
    public ResponseEntity<Topics> deleteTopic(@PathVariable Long id){
        Topics topic = topicsRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topic);
        return ResponseEntity.ok(topic);
    }

    // delete the association of a topic for the given article. The topic & article themselves remain.
    @DeleteMapping("/articles/{id}/topics/{id}")
    public ResponseEntity<Article> deleteAssociationArticleTopic(@PathVariable Long id, @PathVariable Long id){
        // how do I deal with two IDs?
    }

}
