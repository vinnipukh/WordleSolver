package tr.edu.ozyegin.cs101.wordlesolver;

public class Word {
    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public Feedback generateFeedbackWithActualWord(Word actualWord) {
        int[] feedback = new int[WordleSolver.WORD_SIZE];
        boolean[] usedInActual = new boolean[WordleSolver.WORD_SIZE];
        boolean[] usedInGuess = new boolean[WordleSolver.WORD_SIZE];

        // First pass: mark greens
        for (int i = 0; i < WordleSolver.WORD_SIZE; i++) {
            if (this.word.charAt(i) == actualWord.word.charAt(i)) {
                feedback[i] = Feedback.GREEN;
                usedInActual[i] = true;
                usedInGuess[i] = true;
            } else {
                feedback[i] = Feedback.BLACK;
            }
        }

        // Second pass: mark yellows
        for (int i = 0; i < WordleSolver.WORD_SIZE; i++) {
            if (feedback[i] == Feedback.GREEN) continue;

            for (int j = 0; j < WordleSolver.WORD_SIZE; j++) {
                if (!usedInActual[j] && this.word.charAt(i) == actualWord.word.charAt(j)) {
                    feedback[i] = Feedback.YELLOW;
                    usedInActual[j] = true;
                    break;
                }
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