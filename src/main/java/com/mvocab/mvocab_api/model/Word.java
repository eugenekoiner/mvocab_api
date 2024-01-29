package com.mvocab.mvocab_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String word;
    private String transcription;
    private String audio;
    private String lang_id;
    private String translation;
}
