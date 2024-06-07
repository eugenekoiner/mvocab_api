package translator;

import com.codeborne.selenide.Selenide;
import translator.deepl.DeeplTranslator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

public class Translator {
    public static TreeSet<String> getTranslations(Connection connection, List<String> movieWords) throws SQLException {
        TreeSet<String> csvTable = new TreeSet<>();
        int totalWordCounter = 0;
        int toBaseCounter = 0;
        int fromBaseCounter = 0;
        boolean editMode = false;
        if (!movieWords.isEmpty()){
            for (String word: movieWords) {
                ResultSet resultSet = connection.createStatement().executeQuery("SELECT id FROM word where word = '" + word + "'");
                if (resultSet.next()) {
                    totalWordCounter++;
                    String wordId = resultSet.getString("id");
                    if (wordId != null) {
                        ResultSet resultSet2 = connection.createStatement().executeQuery("SELECT translation FROM translation WHERE lang_id = 1 AND word_id = " + wordId);
                        if (resultSet2.next() && !editMode) {
                            String translationFromDb = resultSet2.getString("translation");
                            csvTable.add(word + "!-%" + translationFromDb);
                            fromBaseCounter++;
                        } else {
                            String translation = new DeeplTranslator().getDeeplTranslation(word);
                            csvTable.add(word + "!-%" + translation);
                            if (translation != null) {
                                toBaseCounter++;
                            }
                            String sql = editMode ? "UPDATE translation SET translation = ? WHERE lang_id = 1 AND word_id = ?" : "INSERT INTO translation (translation, lang_id, word_id) VALUES (?, 1, ?)";
                            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                                pstmt.setString(1, translation);
                                pstmt.setInt(2, Integer.parseInt(wordId));
                                pstmt.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Количество переведенных слов: " + totalWordCounter);
        System.out.println("Переводов слов добавлено в базу: " + toBaseCounter);
        System.out.println("Переводов слов взято из базы: " + fromBaseCounter);
        Selenide.closeWebDriver();
        return csvTable;

    }
}

