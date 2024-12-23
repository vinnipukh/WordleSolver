package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        WordList wordList = new WordList();

        wordList.loadWords("/home/tachyonos/IdeaProjects/WordleSolver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        Random random = new Random();

        boolean finished = false;

        Scanner scanner = new Scanner(System.in);

        int guessCount = 0;



        while (!finished) {
            int index;

            if(guessCount > 0){
                if (wordList.getWords().isEmpty()) {
                    System.out.println("This word is not in the word list which is provided. Try again!");
                    finished = true;
                    break;
                }
                index = wordList.generateNextGuessIndex(wordList);

            }else {
                index = 9463;
            }
            System.out.println(wordList.getWords());
            guessCount++;
            if(wordList.getWords().isEmpty()){
                System.out.println("The word is not in the word list. Try again!");
                finished = true;
                break;

            }



            String guess = wordList.getWords().get(index);



            System.out.println("My guess is: " + guess);

            System.out.println("Enter feedback:");

            String feedback;

            boolean isValid;

            do {
                feedback = scanner.nextLine();

                isValid = isValidFeedback(feedback);

                if (!isValid) {
                    System.out.println("Invalid feedback. Try again.");
                }

            } while(!isValid);
            if(feedback.equalsIgnoreCase("ggggg")){
                System.out.println("We have guessed the word correctly!");
                finished = true;
            }


            else{
                System.out.println("Feedback waas: " + feedback);


                Feedback actualFeedback = new Feedback(feedback);

                wordList.reduce(new Word(guess),actualFeedback);

            }
        }
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
