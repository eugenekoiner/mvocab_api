package translator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class DeeplTranslator extends DeeplApiSteps {

    public TranslationsDTO getDeeplTranslation(String word) {

        TranslationsDTO translationsDTO = new TranslationsDTO();
        Map<String, TranslationDTO> translationsMap = new LinkedHashMap<>();
        List<String> lct = new ArrayList<>();
        int i = 0;

        String responseFull = getDeeplPage(word);
        Element rawTranslation = getResult(responseFull, "//div[@class='exact']");
        if (rawTranslation != null) {
            Elements headers = rawTranslation.selectXpath("*/*/*/span[@class='tag_lemma']/a[@class='dictLink']");
            for (Element header : headers) {
                if (header.text().toLowerCase().replaceAll("([^A-Za-z])", "").equals(word)) {
                    Element partOfSpeechElement = header.selectXpath("../span[@class='tag_wordtype']").first();
                    String partOfSpeech = partOfSpeechElement != null ? partOfSpeechElement.text() : "";
                    Elements translationHeaders = header.selectXpath("../../../div[@class=\"lemma_content\"]/*/*/div/*/span//a[.!='']");
                    for (Element translationHeader : translationHeaders) {
                        i++;
                        Element exampleElement = translationHeader.selectXpath("../../../div[@class='example_lines']").first();
                        Element lessCommonElement = header.selectXpath("../../..//span[@class='notascommon']/../../div[@class='line group_line translation_group_line']").first();
                        String translation = translationHeader.text();
                        String example = exampleElement != null ? exampleElement.text() : null;

                        TranslationDTO translationDTO = new TranslationDTO();
                        translationDTO.setTranslation(translation);
                        translationDTO.setPartOfSpeech(partOfSpeech);
                        translationDTO.setExample(example);

                        translationsMap.put("translation" + i, translationDTO);

                        try {
                            lct.add(lessCommonElement.text().replaceAll("менее частотные: ", "").replaceAll(" · ", ", "));
                        } catch (NullPointerException ex) {
                            // nothing to do
                        }
                    }
                }
            }
            Set<String> lcSet = new HashSet<>();
            String[] lcStringArray = String.join(", ", lct).split(", ");
            for (String trans : lcStringArray) {
                lcSet.add(trans);
            }
            translationsDTO.setLessCommon(String.join(", ", lcSet).isBlank() ? null : String.join(", ", lcSet));
        } else {
            translationsDTO = simpleTransition(word);
        }

        if (translationsMap.isEmpty()) {
            translationsDTO = simpleTransition(word);
        } else {
            translationsDTO.setTranslations(translationsMap);
        }

        return translationsDTO;
    }

    private TranslationsDTO simpleTransition(String word) {
        TranslationsDTO translationsDTO = new TranslationsDTO();
        TranslationDTO translationDTO = new TranslationDTO();
        String translation = getSimpleDeeplTranslation(word);
        translation = translation.isBlank() ? null : "*Simple* " + translation;
        if (translation != null) {
            translationDTO.setTranslation(translation);
            translationDTO.setPartOfSpeech(null);
            translationDTO.setExample(null);
            translationsDTO.getTranslations().put("translation1", translationDTO);
        }
        return translationsDTO;
    }
}




