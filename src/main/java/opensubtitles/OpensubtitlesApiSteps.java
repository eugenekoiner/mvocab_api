package opensubtitles;




import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getStringProperty;

public class OpensubtitlesApiSteps {

    public static Response findByName(String movieName) {
        JsonPath response = given()
                .spec(mainReqSpec("subtitles"))
                .queryParam("type", "movie")
                .queryParam("type", "movie")
                .queryParam("query",movieName)
                .when()
                .get()
                .then()
                .statusCode(200).extract().body().jsonPath();
        int actualId = Integer.parseInt(response.get("id"));
        return response.getDetailedCookies();
    }


    protected static RequestSpecification mainReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getStringProperty("api.server") + "/api/v1")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

}
