package translator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class GoogleTranslate {
    public static String translateWord(String word) {
        open("https://translate.google.com/");
        getFocusedElement().sendKeys(word);
        String translation = $x("(//tbody)[2]").innerHtml();
        getFocusedElement().sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
            Document document = Jsoup.parse(translation);
            String text = document.text();
            String text2 = text.replaceAll("Распространенный вариант", "***\n").replaceAll("Менее распространенный вариант", "**\n").replaceAll("Редко используемый вариант", "*\n").replaceAll("имя существительное", "Cущ. \n").replaceAll("глагол", "Глаг. \n").replaceAll("([а-я].)( )([a-z].)", "$1. Син: $3");
            return text2;
        }
    }
