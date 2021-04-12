package se.devnewsproject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "topics")
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
}
