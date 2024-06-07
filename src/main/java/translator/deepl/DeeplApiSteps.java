package translator.deepl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.TreeSet;

import static io.restassured.RestAssured.given;
import static java.lang.String.join;

public class DeeplApiSteps {

    protected static String getDeeplPage(String word) {
        String response = given()
                .spec(DeeplTranslator.fullReqSpec("search"))
                .queryParam("ajax", 1)
                .queryParam("source", "english")
                .queryParam("onlyDictEntries", 1)
                .queryParam("translator", "dnsof7h3k2lgh3gda")
                .queryParam("kind", "context")
                .queryParam("eventkind", "click")
                .queryParam("forleftside", false)
                .queryParam("il", "ru")
                .queryParam("query", word)
                .post()
                .then()
                .statusCode(200)
                .extract().response().getBody().asString();
        return response;
    }

    protected static String getSimpleDeeplTranslation(String word) {
        String result;
        TreeSet<String> translationsSet = new TreeSet<>();
        String response = given()
                .spec(DeeplTranslator.simpleReqSpec("jsonrpc"))
                .queryParam("method", "LMT_handle_jobs")
                .body("{\"jsonrpc\":\"2.0\",\"method\": \"LMT_handle_jobs\",\"params\":{\"jobs\":[{\"kind\":\"default\",\"sentences\":[{\"text\":\"" + word + "\",\"id\":1,\"prefix\":\"\"}],\"raw_en_context_before\":[],\"raw_en_context_after\":[],\"preferred_num_beams\":4,\"quality\":\"fast\"}],\"lang\":{\"target_lang\":\"RU\",\"preference\":{\"weight\":{\"DE\":0.77973,\"EN\":4.17736,\"ES\":0.18744,\"FR\":0.37069,\"IT\":0.32847,\"JA\":0.10957,\"NL\":0.27776,\"PL\":0.1753,\"PT\":0.15125,\"RU\":2.43581,\"ZH\":0.09792,\"BG\":0.45227,\"CS\":0.12135,\"DA\":0.21675,\"EL\":0.06131,\"ET\":0.18102,\"FI\":0.20279,\"HU\":0.10958,\"LT\":0.13412,\"LV\":0.08434,\"RO\":0.19092,\"SK\":0.10887,\"SL\":0.10046,\"SV\":0.2646,\"TR\":0.10233,\"ID\":0.15036,\"UK\":0.33697,\"KO\":0.06388,\"NB\":0.19294},\"default\":\"default\"},\"source_lang_user_selected\":\"EN\"},\"priority\":-1,\"commonJobParams\":{\"mode\":\"translate\",\"textType\":\"plaintext\",\"browserType\":1},\"timestamp\":1702892291316},\"id\":78400003}")
                .post()
                .then()
                .statusCode(200)
                .extract().response().getBody().asString();
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        JsonArray array = jsonResponse
                .getAsJsonObject("result")
                .getAsJsonArray("translations")
                .get(0)
                .getAsJsonObject()
                .getAsJsonArray("beams");
        for (JsonElement j : array) {
            result = j.getAsJsonObject()
                    .getAsJsonArray("sentences")
                    .get(0)
                    .getAsJsonObject()
                    .get("text")
                    .toString().replaceAll("[^А-Яа-я\\s]", "").toLowerCase().trim();
            if (!result.isEmpty()) {
                translationsSet.add(result);
            }
        }
        System.out.println(translationsSet);
        return join(", ", translationsSet);
    }

    protected Element getResult(String responseBody, String xPath) {
        Document document = Jsoup.parse(responseBody);
        Elements elements = document.selectXpath(xPath);
        elements.select("span.tag_type").remove();
        elements.select("span.tag_usage").remove();
        elements.select("span.placeholder").remove();
        elements.select("span.tag_lemma_context").remove();
        Element value = elements.first();
        return value;
    }

    static RequestSpecification fullReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri("https://dict.deepl.com/english-russian")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    static RequestSpecification simpleReqSpec(String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri("https://www2.deepl.com")
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }



}
