package tr.edu.ozyegin.cs101.wordlesolver;

import javax.swing.*;
import java.awt.*;

public class ResizablePopup {
    public static void showDefinition(String word, String definition) {
        // Create a text area for the definition
        JTextArea textArea = new JTextArea(definition, 10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        // Put it in a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Create a dialog and make it resizable
        JDialog dialog = new JDialog();
        dialog.setTitle("Definition of: " + word);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(scrollPane);
        dialog.setSize(400, 200);
        dialog.setResizable(true); // Enable resizing
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        showDefinition("example", "A representative form or pattern.");
    }
}
