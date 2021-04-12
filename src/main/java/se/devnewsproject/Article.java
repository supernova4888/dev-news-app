package se.devnewsproject;
import javax.persistence.*;
import java.util.List;

@Entity
public class Article {

    // unique id, title, body (article text content) and the authorName
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private String authorName;

    // this is controlled by Comments. In our example it is the 'owner'
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

//    public Article(String title, String body, String authorName){
//        this.title = title;
//        this.body = body;
//        this.authorName = authorName;
//    }

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
