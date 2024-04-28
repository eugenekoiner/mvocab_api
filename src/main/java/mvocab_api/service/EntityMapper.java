package mvocab_api.service;

import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.MovieListDTO;
import mvocab_api.model.UserListDTO;
import mvocab_api.model.WordByIdDTO;
import mvocab_api.model.WordListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    // Методы маппинга для Word
    WordListDTO toWordForList(WordEntity entity);

    @Mapping(source = "translation", target = "translation", qualifiedByName = "translationToString")
    WordByIdDTO toWordById(WordEntity entity);

    @Named("translationToString")
    default String translationToString(TranslationEntity translationEntity) {
        return translationEntity != null ? translationEntity.getTranslation() : null;
    }

    // Методы маппинга для Movie
    MovieListDTO toMovieForList(MovieEntity entity);


    // Методы маппинга для User
    @Mapping(source = "userLangs", target = "langs")
    UserListDTO toUserForList(UserEntity entity);

}
