package mvocab_api.model;

import lombok.Data;

@Data
public class WordById {
    private Integer id;
    private String word;
    private Integer lang_id;
    private String translation;
}
