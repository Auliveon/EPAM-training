package engine;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Engine {

    private final String[] filters = {",", ".", "!", "?", "(", ")", ":", ";", "— ", "»", "«"};

    private final String consonants = "бвгджзйклмнпрстфхцчшщ";

    private final String vowels = "аеёиоуыэюя";

    private final String[] consonantsArr = {"б", "в", "г", "д", "ж", "з", "й", "к", "л", "м", "н", "п", "р", "с", "т", "ф", "х", "ц", "ч", "ш", "щ"};

    private final String[] vowelsArr = {"а", "е", "ё", "и", "о", "у", "ы", "э", "ю", "я"};

    private String fileName;

    private ArrayList<String> words;

    private File file;

    private String text;

    private final String sourcePathPath = "lab2/src/main/resources/files/source/";

    private final String resultPath = "lab2/src/main/resources/files/modified/";

    public Engine(String fileName) throws IOException {
        this.fileName = fileName;
        this.file = new File(sourcePathPath + fileName);
        this.text = new String(Files.readAllBytes(file.toPath()));
        this.words = new ArrayList<>();
    }

    public void modify() throws IOException {

        String[] textArray = text.split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (String word : textArray) {
            sb.append(word);
            sb.append(" ");
        }

        FileOutputStream fileOutputStream = new FileOutputStream(resultPath + "(modified)" + fileName);
        fileOutputStream.write(sb.toString().getBytes());

    }

    public void sort() {

        String[] textArray = text.split("\\s+");

        for (int n = 0; n < filters.length; n++) {
            String filter = filters[n];
            for (int i = 0; i < textArray.length; i++) {
                textArray[i] = textArray[i].trim();
                if (textArray[i].endsWith(filter)) {
                    textArray[i] = textArray[i].substring(0, textArray[i].length() - filter.length());
                } else if (textArray[i].startsWith(filter)) {
                    textArray[i] = textArray[i].substring(filter.length());
                }
            }
        }

        for (int i = 0; i < textArray.length; i++) {
            char[] chars = textArray[i].toCharArray();
            if (textArray[i].length() > 1 && vowels.contains(Character.toString(textArray[i].charAt(0))) && !textArray[i].contains(".")) {
                for (char ch2 : chars) {
                    if (consonants.contains(Character.toString(ch2).toLowerCase(Locale.ROOT))) {
                        words.add(textArray[i]);
                        break;
                    }
                }
            }
        }

        words.sort(Comparator.comparing(this::getFirstConsonants));
        for(String s: words) {
            System.out.println(s);
        }
    }

    private String getFirstConsonants(String word) {
        word = word.toLowerCase(Locale.ROOT);
        int index = word.length() - 1;
        for (int i = 0; i < consonantsArr.length; i++) {
            if (word.contains(consonantsArr[i]))
                index = Math.min(word.indexOf(consonantsArr[i]), index);
        }

        String letter = Character.toString(word.charAt(index));
        return letter;
    }
}
