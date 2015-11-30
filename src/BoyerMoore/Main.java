package BoyerMoore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("seneca.txt")), StandardCharsets.UTF_8);
        String pattern = "omne";

        Search search = new Search();

        List<Integer> indices = search.search(input.toCharArray(), pattern.toCharArray());

        indices.forEach(System.out::println);
        System.out.println("Total: " + indices.size());
    }
}
