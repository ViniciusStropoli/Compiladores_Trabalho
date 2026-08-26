package org.ui;

import javax.swing.*;

import java.awt.*;

import javax.swing.border.Border;

public class MainFrame {
      private JTextArea messageArea;  
      private JLabel statusBarLabel;
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

        frame.getContentPane().setLayout(new BorderLayout());

        JPanel sidePanel = createSideBar();

       // Main panel - agora com BorderLayout pra organizar melhor
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout()); /*(aqui com o BorderLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));/* */

        //Adiciona os componentes ao mainPanel
        JPanel editorPlaceholder = new JPanel();
        editorPlaceholder.setBackground(Color.WHITE);
        mainPanel.add(editorPlaceholder, BorderLayout.CENTER);

        //Message area vai no SOUTH (parte de cima)
        mainPanel.add(createMessageArea(), BorderLayout.SOUTH);

        //Status bar vai na base(SOUTH do frame)
        frame.add(createStatusBar(), BorderLayout.SOUTH);



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
        sidePanel.add(createJButton("New [ctrl + n]", "/icons/new.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Open [ctrl + o]", "/icons/open.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Save [ctrl + s]", "/icons/save.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Copy [ctrl + c]", "/icons/copy.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Paste [ctrl + v]", "/icons/paste.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Cut [ctrl + x]", "/icons/cut.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("Compile [F7]", "/icons/compile.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("About [F1]", "/icons/about.png"));

        return sidePanel;
    }

    public JButton createJButton(String text, String iconPath) {
        JButton button = new JButton(text);

        java.net.URL url = getClass().getResource(iconPath);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);

            int whidht = 23;
            int height = 23;

            Image imgOriginal = icon.getImage();
            Image imgRedimensionada = imgOriginal.getScaledInstance(whidht, height, Image.SCALE_SMOOTH);
            ImageIcon rescaledIcon = new ImageIcon(imgRedimensionada);

            button.setIcon(rescaledIcon);

        } else {
            System.err.println("Imagem não encontrada em: " + iconPath);
        }

        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setVerticalTextPosition(SwingConstants.CENTER);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(120, 90);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);

        return button;
    }
    //create MessageArea (non-editable with scrollbars always visible)

    private JScrollPane createMessageArea() {
        //Create JTextArea not editable
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(Color.LIGHT_GRAY);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        messageArea.setLineWrap(false); 


        //create JScrollPane with scrollbars aways visible
        JScrollPane scrollPane = new JScrollPane(messageArea);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //set preferred size 
        scrollPane.setPreferredSize(new Dimension(1350,200));

        return scrollPane;        

    }

    //create StatusBar (non-editable with scrollbars always visible)
    private JPanel createStatusBar() {
        //Create JPanel for status bar with width 1500 and height 25
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(1500, 25));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        //Create JLabel for status bar 
        statusBarLabel = new JLabel("nenhum arquivo aberto");
        statusBarLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusBarLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        statusBar.add(statusBarLabel, BorderLayout.WEST);

        return statusBar;
    }





}
