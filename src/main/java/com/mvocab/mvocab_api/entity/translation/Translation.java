package com.mvocab.mvocab_api.entity.translation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "translation")
public class Translation {
    @Id
    private int id;
    private String translation;
}
