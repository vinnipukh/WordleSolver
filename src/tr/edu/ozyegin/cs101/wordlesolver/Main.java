package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.List;
public class Main {
    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList();
        wordList.loadWords("C:/Users/arhan/IdeaProjects/WordleSolver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        int n = 0, sum = 0;

        for (String secretWordString : wordList.getWords()) {
            Word secretWord = new Word(secretWordString);
            Wordle wordle = new Wordle(secretWord);
            int guesses = playWordle(wordle);
            n++;
            sum += guesses;
            JOptionPane.showMessageDialog(null, "Played " + n + " times used " + guesses + " guesses, average: " + (double) sum / n);
            System.exit(0);
        }
    }


    public static int playWordle(Wordle wordle) throws IOException {
        WordList wordList = new WordList();
        wordList.loadWords("C:/Users/arhan/IdeaProjects/WordleSolver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        int guessCount = 0;

        while (!finished) {
            int index;

            if (guessCount > 0) {
                List<String> words = wordList.getWords();
                if (words.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "This word is not in the word list which is provided, I give up...");
                    finished = true;
                    break;
                }
                if (words.size() == 1) {
                    JOptionPane.showMessageDialog(null, "The last possibility is: " + words.get(0));
                    finished = true;
                    break;
                }
                index = wordList.generateNextGuessIndex(wordList);
            } else {
                index = 9463; // chosen word "salet" as it gives the most information
            }

            guessCount++;
            if (wordList.getWords().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The word is not in the word list. Try again!");
                finished = true;
                break;
            }

            String guess = wordList.getWords().get(index);
            JOptionPane.showMessageDialog(null, "My guess is: " + guess);

            double probability = calculateProbability(wordList, new Word(guess));
            JOptionPane.showMessageDialog(null, "Probability of this being the correct word: " + probability * 100 + "%");

            Feedback actualFeedback;
            String feedback;
            boolean isValid;

            do {
                feedback = JOptionPane.showInputDialog("Enter feedback:");
                isValid = isValidFeedback(feedback);

                if (!isValid) {
                    JOptionPane.showMessageDialog(null, "Invalid feedback. Try again.");
                }
            } while (!isValid);

            JOptionPane.showMessageDialog(null, "Feedback was: " + feedback);
            actualFeedback = new Feedback(feedback);

            wordList.reduce(new Word(guess), actualFeedback);
            if (actualFeedback.isAllGreen()) {
                JOptionPane.showMessageDialog(null, "We have guessed the word correctly!");
                finished = true;
                break;

            }
        }
        return guessCount;
    }
    public static double calculateProbability(WordList wordList, Word guessWord) {
        int totalWords = wordList.getWords().size();
        int possibleWords = wordList.calculateMatchingWords(guessWord);

        return (double) possibleWords / totalWords;
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