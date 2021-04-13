package se.devnewsproject;

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

    @ManyToMany(mappedBy = "topics", cascade = CascadeType.PERSIST)
    private List<Article> article;

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
}
