package subtitles_api.opensubtitles;


import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mvocab_api.entity.MovieEntity;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getStringProperty;

public class OpensubtitlesApiSteps {



    public Response findByName(String movieName) {
        JsonObject response = given()
                .spec(opensubtitlesReqSpec("subtitles"))
                .queryParam("imdb_id", (movieName))
                .when()
                .get()
                .then()
                .statusCode(200).extract().body().jsonPath().getJsonObject("data");

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName(response.getAsJsonObject("feature_details").get("title").toString());
        movieEntity.setName(response.getAsJsonObject("feature_details").get("title").toString());



         //todo вопрос что вернуть, наверно movieDTO
        return null;
    }






    protected RequestSpecification opensubtitlesReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getStringProperty("opensubtitle", "api.server") + "/api/v1")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }



}
