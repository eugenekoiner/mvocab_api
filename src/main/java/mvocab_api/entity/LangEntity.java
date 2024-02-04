package mvocab_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lang")
public class LangEntity{
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
}
