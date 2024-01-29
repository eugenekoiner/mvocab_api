package com.mvocab.mvocab_api.repository;

import com.mvocab.mvocab_api.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository <Word, Integer> {
}
