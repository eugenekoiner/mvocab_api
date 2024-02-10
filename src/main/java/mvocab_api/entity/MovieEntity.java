package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private String name;
    private String img;
    private Integer rating; 
    private String trailer;
}
