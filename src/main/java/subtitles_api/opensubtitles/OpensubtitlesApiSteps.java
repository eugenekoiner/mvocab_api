package subtitles_api.opensubtitles;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getStringProperty;

public class OpensubtitlesApiSteps {
    public File findSubtitleByImdbId(String imdbId) {
        return new File(getDownloadLink(getFileId(imdbId)));
    }

    private String getDownloadLink(String fileId) {
        return given()
                .spec(opensubtitlesReqSpec("download"))
                .header("Api-Key", getStringProperty("opensubtitles", "api.key"))
                .header("User-Agent", "v1.2.3")
                .body("{\"file_id\": " + fileId + "}")
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .get("link").toString();
    }

    private String getFileId(String imdbId) {
        return given()
                .spec(opensubtitlesReqSpec("subtitles"))
                .queryParam("imdb_id", (imdbId.replaceAll("tt", "")))
                .header("Api-Key", getStringProperty("opensubtitles", "api.key"))
                .header("User-Agent", "v1.2.3")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList("data.attributes.files.file_id")
                .get(0).toString().replaceAll("[^0-9]*", "");
    }

    private RequestSpecification opensubtitlesReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getStringProperty("opensubtitles", "api.server") + "/api/v1")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }
}
