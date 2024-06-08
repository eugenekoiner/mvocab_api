package subtitles_api.opensubtitles;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class OpensubtitlesApiSteps {
    public static InputStream findSrtByImdbId(String imdbId) throws IOException {
        URL url = new URL(getDownloadLink(getFileId(imdbId)));
        return url.openStream();

    }

    private static String getDownloadLink(String fileId) {
        return given()
                .spec(opensubtitlesReqSpec("download"))
                .header("Api-Key", "SFYJhhez7oVWteDGWyj91EbqlQKPWwmB")
                //.header("Authorization", "Bearer "+ authorization())
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

    private static String getFileId(String imdbId) {
        return given()
                .spec(opensubtitlesReqSpec("subtitles"))
                .queryParam("imdb_id", (imdbId.replaceAll("tt", "")))
                //todo: сюда надо будет добавить изучаемый язык как то
                .queryParam("trusted_sources", "only")
                .queryParam("languages", "en")
                .header("Api-Key", "SFYJhhez7oVWteDGWyj91EbqlQKPWwmB")
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
                .jsonPath()
                .getList("data.attributes.files.file_id")
                .get(0).toString().replaceAll("[^0-9]*", "");
    }

    private static RequestSpecification opensubtitlesReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.opensubtitles.com/api/v1")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }
}
