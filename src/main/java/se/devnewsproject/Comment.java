package se.devnewsproject;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;
    private String authorName;

    @ManyToOne
    // logically speaking the Article should keep track of comments. But because of how
    // relational databases work, in the code it is the Comment (MANY) who 'holds' the reference (foreign key) to the Article (ONE)
    private Article article;


    public Comment() {

    }

    public Article getOwner() {
        return article;
    }

    public void setOwner(Article article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
