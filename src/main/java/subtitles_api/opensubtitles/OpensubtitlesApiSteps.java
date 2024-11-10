package subtitles_api.opensubtitles;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getStringProperty;

public class OpensubtitlesApiSteps {
    public static InputStream findSrtBySrtId(int srtId) throws IOException {
        URL url = new URL(getDownloadLink(srtId));
        return url.openStream();
    }

    public static int getSrtByImdbId(String imdbId) {
        return getFileId(imdbId);
    }

    private static String getDownloadLink(int fileId) {
        return given()
                .spec(opensubtitlesReqSpec("api/v1/download"))
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

    private static int getFileId(String imdbId) {
        JsonPath data = given()
                .spec(opensubtitlesReqSpec("api/v1/subtitles"))
                .queryParam("imdb_id", (imdbId.replaceAll("tt", "")))
                //todo: сюда надо будет добавить изучаемый язык как то
                .queryParam("trusted_sources", "only")
                .queryParam("languages", "en")
                .header("Api-Key", getStringProperty("opensubtitles", "api.key"))
                //.header("Authorization", "Bearer "+ authorization())
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
                .jsonPath();
        if (data.get("total_pages").equals(0)) {
            return -1;
        }
        return Integer.parseInt(data.getList("data.attributes.files.file_id").get(0).toString().replaceAll("[^0-9]*", ""));
    }

    private static RequestSpecification opensubtitlesReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(getStringProperty("opensubtitles", "api.server"))
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }
}
