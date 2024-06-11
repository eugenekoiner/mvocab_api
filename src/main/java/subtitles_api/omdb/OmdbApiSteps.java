package subtitles_api.omdb;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import subtitles_api.omdb.dto.OmdbMovieIdDTO;
import subtitles_api.omdb.dto.OmdbMovieListDTO;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OmdbApiSteps {
    public int total;
    public Page<OmdbMovieListDTO> getOmdbMovieListByName(String title, int page) {
        JsonPath data = given()
                .spec(omdbReqSpec())
                .queryParam("apikey", "e36e79ce")
                .queryParam("s", title)
                .queryParam("page", page)
                .header("User-Agent", "v1.2.3")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().jsonPath();
        total = data.getInt("totalResults");
        List<OmdbMovieListDTO> moviesDTO = data.getList("Search", OmdbMovieListDTO.class);
        return new PageImpl<>(moviesDTO, PageRequest.of(page, moviesDTO.size()), total);
    }

    public OmdbMovieIdDTO getOmdbMovieByImdbId(String imdbId) {
        JsonPath data = given()
                .spec(omdbReqSpec())
                .queryParam("apikey", "e36e79ce")
                .queryParam("i", imdbId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response().jsonPath();
        return data.getObject("", OmdbMovieIdDTO.class);
    }

    protected RequestSpecification omdbReqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://www.omdbapi.com")
                .setContentType(ContentType.JSON)
                .build();
    }

}
