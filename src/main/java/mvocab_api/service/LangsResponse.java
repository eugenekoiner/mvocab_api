package mvocab_api.service;

import lombok.Data;
import mvocab_api.entity.LangEntity;
import mvocab_api.model.Movie;

import java.util.List;

@Data
public class LangsResponse {
    private List<LangEntity> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}