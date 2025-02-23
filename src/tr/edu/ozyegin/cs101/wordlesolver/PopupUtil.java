package tr.edu.ozyegin.cs101.wordlesolver;

import javax.swing.*;
import java.awt.*;

public class PopupUtil {

    /**
     * Shows a resizable popup with the given title and message.
     * @param title The window title.
     * @param message The message to display.
     * @param width The initial width.
     * @param height The initial height.
     */
    public static void showResizablePopup(String title, String message, int width, int height) {
        // Create a non-editable text area with word wrap
        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Place it in a scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(width, height));

        // Create a dialog, add the scroll pane, and enable resizing
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(true);
        dialog.setVisible(true);
    }

    /**
     * Sets up a simple dark mode for popups using UIManager.
     * You can call this early in your application (for example, in main()).
     * @param darkMode true to enable dark mode; false to disable.
     */
    public static void setDarkMode(boolean darkMode) {
        if (darkMode) {
            UIManager.put("control", new Color(50, 50, 50));
            UIManager.put("info", new Color(50, 50, 50));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusGreen", new Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
            UIManager.put("text", new Color(230, 230, 230));
        } else {
            // Reset values (for a real application, you might store defaults)
            UIManager.put("control", null);
            UIManager.put("info", null);
            UIManager.put("nimbusBase", null);
            UIManager.put("nimbusAlertYellow", null);
            UIManager.put("nimbusDisabledText", null);
            UIManager.put("nimbusFocus", null);
            UIManager.put("nimbusGreen", null);
            UIManager.put("nimbusInfoBlue", null);
            UIManager.put("nimbusLightBackground", null);
            UIManager.put("nimbusOrange", null);
            UIManager.put("nimbusRed", null);
            UIManager.put("nimbusSelectedText", null);
            UIManager.put("nimbusSelectionBackground", null);
            UIManager.put("text", null);
        }
    }
}
