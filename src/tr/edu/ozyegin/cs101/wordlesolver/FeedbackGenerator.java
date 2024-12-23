package tr.edu.ozyegin.cs101.wordlesolver;

public class FeedbackGenerator {
    /**
     * Generate feedback between two words.
     *
     * @param guessWord    The guessed word.
     * @param actualWord   The actual word.
     * @return              The feedback based on the comparison.
     */
    public static Feedback generateFeedback(Word guessWord, Word actualWord) {
        char[] guessChars = guessWord.getValue().toCharArray();
        char[] actualChars = actualWord.getValue().toCharArray();

        int[] feedback = new int[guessChars.length];

        for (int i = 0; i < guessChars.length; i++) {
            if (guessChars[i] == actualChars[i]) {
                feedback[i] = Feedback.GREEN; // Correct position
            } else if (new String(actualChars).contains(String.valueOf(guessChars[i]))) {
                feedback[i] = Feedback.YELLOW; // Correct letter, wrong position
            } else {
                feedback[i] = Feedback.BLACK; // Incorrect letter
            }
        }

        // Convert feedback array to String
        StringBuilder feedbackString = new StringBuilder();
        for (int f : feedback) {
            switch (f) {
                case Feedback.GREEN:
                    feedbackString.append('G');
                    break;
                case Feedback.YELLOW:
                    feedbackString.append('Y');
                    break;
                case Feedback.BLACK:
                    feedbackString.append('B');
                    break;
            }
        }

        return new Feedback(feedbackString.toString());
    }
}