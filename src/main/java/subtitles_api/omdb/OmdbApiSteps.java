package subtitles_api.omdb;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import subtitles_api.omdb.dto.OmdbMovieListDTO;

import java.util.List;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getStringProperty;

public class OmdbApiSteps {
    public Page<OmdbMovieListDTO> getOmdbMovieListByName(String title, int page) {
        JsonPath data = given()
                .spec(omdbReqSpec())
                .queryParam("apikey", getStringProperty("omdb", "api.key"))
                .queryParam("s", title)
                .queryParam("page", page)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response().jsonPath();
        int total = data.getInt("totalResults");

        List<OmdbMovieListDTO> moviesDTO = data.getList("Search", OmdbMovieListDTO.class);
        //todo надо разобраться как работает пагинация, сейчас на последней странице показывает неверное количество страниц а также неверно работает last
        return new PageImpl<>(moviesDTO, PageRequest.of(page, moviesDTO.size()), total);
    }


    protected RequestSpecification omdbReqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getStringProperty("omdb", "api.server"))
                .setContentType(ContentType.JSON)
                .build();
    }




}
