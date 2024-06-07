package filter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SrtToWords {

    public TreeSet<String> getWordsAsIs(File file) {
        //Без фильтров как есть
        TreeSet<String> setWords = new TreeSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                String[] filteredWords = Arrays.stream(line.split("\n")).toArray(String[]::new);
                Collections.addAll(setWords, filteredWords);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return setWords;
    }


    public TreeSet<String> getWords(File file) {
        TreeSet<String> setWords = new TreeSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                String[] filteredWords = Arrays.stream(line.replaceAll("[^a-zA-Z\\s']", "")
                        .replaceAll("[a-z*]['][a-z*]", "")
                        .replaceAll("[']", "").toLowerCase(Locale.ROOT).split("\\s")).filter(e -> e.trim().length() > 0).toArray(String[]::new);
                Collections.addAll(setWords, filteredWords);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return setWords;
    }

}
