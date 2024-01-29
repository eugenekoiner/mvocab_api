package com.mvocab.mvocab_api.model.translation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "translation")
public class Translation {
    @EmbeddedId
    private TranslationId id;
    private String translation;
}
