package org.UI;

public class MainFrame {
    public static void main(String[] args) {
        // Create and display the main frame
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        // Create the main frame
        javax.swing.JFrame frame = new javax.swing.JFrame("Main Frame");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add components to the frame
        javax.swing.JLabel label = new javax.swing.JLabel("Hello, World!");
        frame.getContentPane().add(label);

        // Display the frame
        frame.setVisible(true);
    }
}
