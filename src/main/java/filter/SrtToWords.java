package filter;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SrtToWords {
    public static TreeSet<String> getWordsFromSrt(InputStream inputStream) {
        TreeSet<String> setWords = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] filteredWords = Arrays.stream(line.replaceAll("[^a-zA-Z\\s']", "").replaceAll("[a-z*]['][a-z*]", "").replaceAll("[']", "").toLowerCase(Locale.ROOT).split("\\s")).filter(e -> !e.trim().isEmpty()).toArray(String[]::new);
                Collections.addAll(setWords, filteredWords);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return setWords;
    }

}
