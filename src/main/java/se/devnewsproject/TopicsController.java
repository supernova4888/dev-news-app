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

    // todo: disallow for duplication?
    // create a new topic
    @PostMapping("/topics")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        topicsRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    // associate the topic with the article given by articleId. If topic does not already exist, it is created
    // The {id} name doesn't need to be the same as the Topic Class neither between methods in the Controller. However, it needs to be consistent between Method's @PostMapping and @PathVariable/ @Param and in the Body
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
    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<List<Topics>> getTopicsByArticleId(@PathVariable Long articleId) {
        Article article = articleRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        List<Topics> topics = article.getTopics();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/topics/{topicsId}/articles")
    public ResponseEntity<List<Article>> getArticleByTopicId(@PathVariable Long topicsId){
        Topics topic = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        List<Article> article = articleRepository.findAll();
        return ResponseEntity.ok(article);
    }

    // update the given topic.
    @PutMapping("/topics/{topicsId}")
    public ResponseEntity<Topics> updateTopic(@PathVariable Long topicsId, @RequestBody Topics updatedTopic){
        topicsRepository
                .findById(topicsId)
                .orElseThrow(ResourceNotFoundException::new);
        // todo: what doest setId do again?
        updatedTopic.setId(topicsId);
        Topics topic = topicsRepository.save(updatedTopic);
        return ResponseEntity.ok(topic);
    }

    // delete the given topic.
    @DeleteMapping("/topics/{topicsId}")
    public ResponseEntity<Topics> deleteTopic(@PathVariable Long topicsId){
        Topics topic = topicsRepository
                .findById(topicsId)
                .orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topic);
        return ResponseEntity.ok(topic);
    }

    // todo: deletes but "get all articles" doesnt update
    // delete the association of a topic for the given article. The topic & article themselves remain.
    @DeleteMapping("/articles/{articleId}/topics/{topicsId}")
    public ResponseEntity<Article> deleteAssociationOfArticleTopic(@PathVariable Long articleId, @PathVariable Long topicsId) {
        Topics topic = topicsRepository.findById(topicsId).orElseThrow(ResourceNotFoundException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        article.getTopics().remove(topic);
        return ResponseEntity.ok(article);
    }
}