package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordList {

    private ArrayList<String> words;

    public WordList() {

    }

    public void loadWords(String path) throws IOException {
        this.words = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.words.add(line);
            }
        }
    }

    public ArrayList<String> getWords() {
        return this.words;
    }

    public void reduce(Word guess, Feedback feedback) {
        ArrayList<String> newWords = new ArrayList<>();

        for (String wordString : this.words) {
            Word word = new Word(wordString);
            Feedback feedbackForWord = guess.generateFeedbackWithActualWord(word);
            if (feedbackForWord.equals(feedback)) {
                newWords.add(wordString);
            }
        }

        System.out.println("Words remaining after reduction: " + newWords);
        this.words = newWords;
    }

    public int generateNextGuessIndex(WordList wordList) {
        int nextGuessIndex = 0;
        int minOfMax = Integer.MAX_VALUE;

        for (int i = 0; i < wordList.getWords().size(); i++) {
            String guess = wordList.getWords().get(i);
            Word guessWord = new Word(guess);
            int maxCount = maximumFeedbackSetCountForWord(guessWord);

            if (maxCount <= minOfMax) {
                minOfMax = maxCount;
                System.out.println("Best Word so far " + guess + " : " + maxCount + " at index " + i);
                nextGuessIndex = i;
            }
        }
        return nextGuessIndex;
    }

    public int maximumFeedbackSetCountForWord(Word word) {
        HashMap<String, Integer> feedbackToCountMap = new HashMap<>();

        for (String actualWordString : this.words) {
            Word actualWord = new Word(actualWordString);
            Feedback feedback = FeedbackGenerator.generateFeedback(word, actualWord);

            if (!feedbackToCountMap.containsKey(feedback.toString())) {
                feedbackToCountMap.put(feedback.toString(), 0);
            }

            feedbackToCountMap.replace(feedback.toString(), feedbackToCountMap.get(feedback.toString()) + 1);
        }

        int max = 0;
        for (int count : feedbackToCountMap.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public int calculateMatchingWords(Word guessWord) {
        int count = 0;
        for (String wordStr : words) {
            Word word = new Word(wordStr);
            Feedback feedback = FeedbackGenerator.generateFeedback(guessWord, word);

            if (feedback.isAllGreen()) {
                count++;
            }
        }
        return count;
    }
}
