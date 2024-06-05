package mvocab_api.service;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginationResponseForOmdbSearch<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PaginationResponseForOmdbSearch(Page<T> page, long total) {
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = total;
        this.totalPages = (int) Math.ceil((double) totalElements /10);
        this.last = this.page == totalPages;
    }
}
