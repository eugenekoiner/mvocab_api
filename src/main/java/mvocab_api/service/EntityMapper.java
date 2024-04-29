package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    // Методы маппинга для Word
    WordList toWordForList(WordEntity entity);

    @Mapping(source = "translation", target = "translation", qualifiedByName = "translationToString")
    WordById toWordById(WordEntity entity);

    @Named("translationToString")
    default String translationToString(TranslationEntity translationEntity) {
        return translationEntity != null ? translationEntity.getTranslation() : null;
    }

    // Методы маппинга для Movie
    MovieList toMovieForList(MovieEntity entity);

    @Mapping(source = "movieWords", target = "movieWords", qualifiedByName = "movieWordsToString")
    //@Mapping(source = "movieWords", target = "langs", qualifiedByName = "movielangsToString")
    MovieById toMovieById(MovieEntity entity);

    @Named("movieWordsToString")
    default List<String> movieWordsToString(List<WordEntity> movieWords) {
        List <String> wordList = new ArrayList<>();
        for (WordEntity word : movieWords) {
            wordList.add(word.getWord());
        }
        return wordList;
    }

    // Методы маппинга для User

    @Mapping(source = "userLangs", target = "langs")
    UserList toUserForList(UserEntity entity);
}
