package com.mvocab.mvocab_api.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User {
    @NonNull
    private Integer id;
    @NonNull
    private String email;
    private String name;
    private String phone;
}
