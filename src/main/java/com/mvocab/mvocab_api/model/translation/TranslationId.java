package com.mvocab.mvocab_api.model.translation;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TranslationId implements Serializable {
    private Integer wordId;
    private Integer langId;
}
