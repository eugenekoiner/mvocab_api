package com.mvocab.mvocab_api.repository;

import com.mvocab.mvocab_api.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository <WordEntity, Integer> {
}
