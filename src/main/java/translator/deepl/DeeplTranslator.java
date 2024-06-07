package translator.deepl;

import com.google.gson.JsonObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.join;

public class DeeplTranslator extends DeeplApiSteps {

    String partOfSpeech;
    String translation;
    String example;
    String lessCommon;
    String result;

    public String getDeeplTranslation(String word) {
        int i = 0;
        JsonObject jsonTranslation = new JsonObject();
        String responseFull = getDeeplPage(word);
        System.out.println("Слово: " + word + "\n\n");
        Element rawTranslation = getResult(responseFull, "//div[@class='exact']");
        if (rawTranslation != null) {
            Elements headers = rawTranslation.selectXpath("*/*/*/span[@class='tag_lemma']/a[@class='dictLink']");
            List<String> lct = new ArrayList<>();
            for (Element header : headers) {
                if (header.text().toLowerCase().replaceAll("([^A-Za-z])", "").equals(word)) {
                    Element partOfSpeechElement = header.selectXpath("../span[@class='tag_wordtype']").first();
                    partOfSpeech = partOfSpeechElement != null ? partOfSpeechElement.text() : "";
                    Elements translationHeaders = header.selectXpath("../../../div[@class=\"lemma_content\"]/*/*/div/*/span//a[.!='']");
                    for (Element translationHeader : translationHeaders) {
                        i++;
                        Element exampleElement = translationHeader.selectXpath("../../../div[@class='example_lines']").first();
                        Element lessCommonElement = header.selectXpath("../../..//span[@class='notascommon']/../../div[@class='line group_line translation_group_line']").first();
                        try {
                            translation = translationHeader.text();
                            example = exampleElement.text();
                        } catch (NullPointerException ex) {
                            example = null;
                        }
                        try {
                            lct.add(lessCommonElement.text().replaceAll("менее частотные: ", "").replaceAll(" · ", ", "));
                        } catch (NullPointerException ex) {
                        }
                        jsonTranslation.addProperty("translation_" + i, translation);
                        jsonTranslation.addProperty("partOfSpeech_" + i, partOfSpeech);
                        jsonTranslation.addProperty("example_" + i, example);
                    }
                    Set<String> lcSet = new HashSet<>();
                    String[] lcStringArray = join(", ", lct).split(", ");
                    for (String trranslation : lcStringArray) {
                        lcSet.add(trranslation);
                    }
                    lessCommon = join(", ", lcSet);
                    lessCommon = lessCommon.isBlank() ? null : lessCommon;
                }
            }
            try {
                if (translation == null || translation.isBlank()) simpleTransition(word, jsonTranslation);
            } catch (NullPointerException ex) {
            }
        } else {
            simpleTransition(word, jsonTranslation);
        }
        if (translation != null) jsonTranslation.addProperty("lessCommon", lessCommon);
        result = translation != null ? jsonTranslation.toString() : null;
        return result;
    }

    private void simpleTransition(String word, JsonObject jsonTranslation) {
        translation = getSimpleDeeplTranslation(word);
        translation = translation.isBlank() ? null : "*Simple* " + translation;
        if (translation != null) {
            jsonTranslation.addProperty("translation_1", translation);
            jsonTranslation.addProperty("partOfSpeech_1", partOfSpeech);
            jsonTranslation.addProperty("example_1", example);
        }
    }


}
