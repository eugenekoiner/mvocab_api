package mvocab_api.entity;

import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {
    private List<UserEntity> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
