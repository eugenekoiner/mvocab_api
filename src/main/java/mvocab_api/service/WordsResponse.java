package mvocab_api.service;

import lombok.Data;
import mvocab_api.model.Word;
import mvocab_api.model.WordListDTO;

import java.util.List;

@Data
public class WordsResponse {
    private List<WordListDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
