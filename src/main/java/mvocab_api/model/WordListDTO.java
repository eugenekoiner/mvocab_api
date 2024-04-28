package mvocab_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class WordListDTO {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String word;
    private Integer lang_id;
}
