package se.devnewsproject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Topics> topics;

    private Article article;

    // Constructor
    public Topics(){
    }


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
