package mvocab_api.service;

import lombok.Data;
import mvocab_api.model.User;

import java.util.List;

@Data
public class UsersResponse {
    private List<User> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
