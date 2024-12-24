package tr.edu.ozyegin.cs101.wordlesolver;

import javax.swing.*;
import java.io.OutputStream;

public class OutputStreamToTextArea extends OutputStream {
    private final JTextArea textArea;

    public OutputStreamToTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength()); // Auto-scroll
        });
    }
}
