package semantic.consoleio;

import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class ScannerStringExample {
    static void main() {
        String story = """
                "When I use a word," Humpty Dumpty said,
                in rather a scornful tone, "it means just what I
                choose it to mean - neither more nor less."
                "The question is," said Alice, "whether you
                can make words mean so many different things."
                "The question is," said Humpty Dumpty,
                "which is to be master - that's all."
                """;
        List<String> words = new Scanner(story)
                .useDelimiter("[- \\.\n\",]+") // pattern for the delimiters
                .tokens()
                .collect(Collectors.toList());

        List<String> words2 = new Scanner(story)
                .findAll("[A-Za-z']+") // pattern for the text you're interested in
                .map(MatchResult::group)
                .collect(Collectors.toList());
        System.out.println(words);
        System.out.println(words2);
    }
}
