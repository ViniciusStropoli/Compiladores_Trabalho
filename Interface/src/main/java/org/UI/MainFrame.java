package org.UI;

import javax.swing.*;
import java.awt.*;

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
        frame.setSize(1500, 800);
        frame.setResizable(false);

        JPanel sidePanel = createSideBar();

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add panels to the frame
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    public JPanel createSideBar() {
        // Create the panel to hold the buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.LIGHT_GRAY);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, 800));

        // Side bar buttons with icons
        sidePanel.add(createJButton("New", "src/main/resources/icons/new.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Open", "src/main/resources/icons/open.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Save", "src/main/resources/icons/save.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Copy", "src/main/resources/icons/copy.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Paste", "src/main/resources/icons/paste.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Cut", "src/main/resources/icons/cut.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Compile", "src/main/resources/icons/compile.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("About", "src/main/resources/icons/about.png"));

        return sidePanel;
    }

    public JButton createJButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(120, 90);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);

        return button;
    }
}
