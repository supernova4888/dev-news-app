package se.devnewsproject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

// THIS IS HOW IT WILL BE CALLED IN DATABASE
@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // DO NOT CHANGE THIS. THIS JUST CREATE THE COLUMN IN THE TABLE
    private Long id;

    private String name;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(mappedBy = "topics")
    @JsonIgnore
    private List<Article> articles;

    // Constructor
    public Topics(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Article> getArticle() {
        return articles;
    }
}
