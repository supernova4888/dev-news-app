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

    // associate the topic with the article given by articleId. If topic does not already exist, it is created
    // todo: not tested
    // The {id} name doesn't need to be the same as the Topic Class neither between methods in the Controller. However, it needs to be consistent between @PostMapping and @PathVariable/ @Param in the method
    @PostMapping("/articles/{articleId}/topics")
    public ResponseEntity<Article> associateTopicWithArticleById(@PathVariable Long articleId, @RequestBody Topics topic) {
        // if 1 or more operations in the database than save it on a single variable: "Article article"
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        // 'find by name' is defined in TopicsRepository
        List<Topics> searchedTopic = topicsRepository
                .findByName(topic.getName());
        if (searchedTopic.isEmpty()) {
            // If the topic doesn't exist -> create and save topic
            topicsRepository.save(topic);
        }
        article.getTopics().add(topic);
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // Return all topics
    @GetMapping("/topics")
    public List<Topics> listAllTopics(){
        List<Topics> topics;
        topics = topicsRepository.findAll();
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
        topicsRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        updatedTopic.setId(id);
        Topics topic = topicsRepository.save(updatedTopic);
        return ResponseEntity.ok(topic);
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
    @DeleteMapping("/articles/{articleId}/topics/{topicsId}")
    public ResponseEntity<Article> deleteAssociationOfArticleTopic(@PathVariable Long articleId, @PathVariable Long topicsId) {
        Topics topic = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        article.getTopics().remove(topic);
        return ResponseEntity.ok(article);

    }
}