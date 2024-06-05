package mvocab_api.model;

import lombok.Data;

@Data
public class WordList {
    private Integer id;
    private String word;
    private Integer lang_id;
}
