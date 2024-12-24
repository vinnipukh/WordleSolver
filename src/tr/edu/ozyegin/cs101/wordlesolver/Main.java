package tr.edu.ozyegin.cs101.wordlesolver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Setup the GUI
        JFrame frame = new JFrame("Wordle Solver Output");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Read-only text area

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Redirect console output to the JTextArea
        PrintStream printStream = new PrintStream(new OutputStreamToTextArea(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        frame.setVisible(true);

        // Start the Wordle solver
        playWordle(null);

        WordList wordList = new WordList();
        wordList.loadWords("/home/tachyonos/IdeaProjects/WordleSolver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        int n = 0, sum = 0;

        /*
        for (String secretWordString : wordList.getWords()) {
            Word secretWord = new Word(secretWordString);
            Wordle wordle = new Wordle(secretWord);
            int guesses = playWordle(wordle);
            n++;
            sum += guesses;
            System.out.println("Played " + n + " times used " + guesses + " guesses, average: " + (double) sum / n);
        }
        */
    }

    public static int playWordle(Wordle wordle) throws IOException {
        WordList wordList = new WordList();
        wordList.loadWords("/home/tachyonos/IdeaProjects/WordleSolver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        int guessCount = 0;

        while (!finished) {
            int index;

            if (guessCount > 0) {
                if (wordList.getWords().isEmpty()) {
                    System.out.println("This word is not in the word list which is provided, I give up...");
                    finished = true;
                }
                index = wordList.generateNextGuessIndex(wordList);

            } else {
                index = 9463; // Pre-chosen "salet" as the starting word
            }

            System.out.println(wordList.getWords());
            guessCount++;
            if (wordList.getWords().isEmpty()) {
                System.out.println("The word is not in the word list. Try again!");
                finished = true;
            }

            String guess = wordList.getWords().get(index);
            System.out.println("My guess is: " + guess);

            Feedback actualFeedback;
            System.out.println("Enter feedback:");

            String feedback;
            boolean isValid;

            do {
                if (wordle == null) {
                    feedback = scanner.nextLine();

                    isValid = isValidFeedback(feedback);

                } else {
                    // There is a Wordle given. Play with that.
                    feedback = wordle.toString();
                    isValid = true;
                }

                if (!isValid) {
                    System.out.println("Invalid feedback. Try again.");
                }

            } while (!isValid);

            System.out.println("Feedback was: " + feedback);

            actualFeedback = wordle.guessAndGetFeedbackForWord(new Word(guess));

            wordList.reduce(new Word(guess), actualFeedback);
            if (actualFeedback.isAllGreen()) {
                System.out.println("We have guessed the word correctly!");
                finished = true;
            }
        }
        return guessCount;
    }

    public static boolean isValidFeedback(String input) {
        if (input.length() != WordleSolver.WORD_SIZE) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case 'b', 'B', 'y', 'Y', 'g', 'G':
                    break;
                default:
                    return false;
            }

        }
        return true;
    }
}

// Helper class to redirec
