package mvocab_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mvocab_api.entity.MovieEntity;
import mvocab_api.entity.TranslationEntity;
import mvocab_api.entity.UserEntity;
import mvocab_api.entity.WordEntity;
import mvocab_api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import subtitles_api.omdb.dto.OmdbMovieIdDTO;
import subtitles_api.omdb.dto.OmdbMovieListDTO;
import subtitles_api.omdb.dto.RatingsDTO;

import java.io.IOException;
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

    @Mapping(source = "title", target = "name")
    @Mapping(source = "poster", target = "img")
    MovieList omdbToMovieList(OmdbMovieListDTO movieOmdb);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "poster", target = "img")
    @Mapping(source = "plot", target = "description")
    @Mapping(source = "language", target = "langs")
    MovieById omdbToMovieId(OmdbMovieIdDTO movieOmdb);

    @Mapping(source = "ratings", target = "ratings", qualifiedByName = "ratingsDtoToratings")
    MovieEntity toMovieEntity(MovieById movieById);

    @Named("ratingsDtoToratings")
    default String ratingsDtoToratings(List<RatingsDTO> ratingsList) throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(ratingsList);
    }


    @Mapping(source = "ratings", target = "ratings", qualifiedByName = "ratingsToRatingsDTO")
    MovieById toMovieById(MovieEntity entity);

    @Named("ratingsToRatingsDTO")
    default List<RatingsDTO> ratingsToRatingsDTO(String ratings) {
        try {
            return new ObjectMapper().readValue(ratings, new TypeReference<>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    // Методы маппинга для User

    @Mapping(source = "userLangs", target = "langs")
    UserList toUserForList(UserEntity entity);
}
