package org.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame {

    private JTextArea messageArea;
    private JLabel statusBarLabel;
    private JTextArea editorArea;
    private JTextArea lineNumberArea;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        javax.swing.JFrame frame = new javax.swing.JFrame("Main Frame");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        frame.setResizable(false);

        frame.getContentPane().setLayout(new BorderLayout());

        JPanel sidePanel = createSideBar();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        // Split between the editor (top) and the message area (bottom).
        // The divider can be dragged to resize both vertically.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createEditor(), createMessageArea());
        splitPane.setResizeWeight(1.0);
        splitPane.setDividerLocation(520);
        splitPane.setBorder(null);

        mainPanel.add(splitPane, BorderLayout.CENTER);

        // South related to the frame (lower)
        frame.add(createStatusBar(), BorderLayout.SOUTH);

        // Add panels to the frame
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public JPanel createSideBar() {
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.LIGHT_GRAY);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(150, 800));

        sidePanel.add(createJButton("<html><center>New<br><font color='#777777'>[ctrl + n]</html>", "/icons/new.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Open<br><font color='#777777'> [ctrl + o]</html>", "/icons/open.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Save<br><font color='#777777'> [ctrl + s]</html>", "/icons/save.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Copy<br><font color='#777777'>[ctrl + c]</html>", "/icons/copy.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Paste<br><font color='#777777'> [ctrl + v]</html>", "/icons/paste.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Cut<br><font color='#777777'> [ctrl + x]</html>", "/icons/cut.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>Compilee<br><font color='#777777'> [F7]</html>", "/icons/compile.png"));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 3)));
        sidePanel.add(createJButton("<html><center>About<br><font color='#777777'> [F1]</html>", "/icons/about.png"));

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
            Image rescaledIcon = imgOriginal.getScaledInstance(whidht, height, Image.SCALE_SMOOTH);

            button.setIcon(new ImageIcon(rescaledIcon));

        } else {
            System.err.println("Imagem não encontrada em: " + iconPath);
        }

        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        button.setHorizontalTextPosition(SwingConstants.CENTER);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(120, 90);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);

        return button;
    }

    // create the editor (line numbers on the left and scrollbars always visible)
    private JScrollPane createEditor() {
        editorArea = new JTextArea();
        editorArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        editorArea.setLineWrap(false);
        editorArea.setMargin(new Insets(0, 5, 0, 5));

        lineNumberArea = new JTextArea("1");
        lineNumberArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lineNumberArea.setBackground(new Color(230, 230, 230));
        lineNumberArea.setForeground(Color.GRAY);
        lineNumberArea.setEditable(false);
        lineNumberArea.setFocusable(false);
        lineNumberArea.setMargin(new Insets(0, 5, 0, 5));

        editorArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });

        JScrollPane scrollPane = new JScrollPane(editorArea);
        scrollPane.setRowHeaderView(lineNumberArea);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(1350, 520));

        return scrollPane;
    }

    // keeps the line numbers in sync with the editor content, always starting at 1
    private void updateLineNumbers() {
        int lines = editorArea.getLineCount();

        StringBuilder numbers = new StringBuilder();
        for (int i = 1; i <= lines; i++) {
            numbers.append(i);
            if (i < lines) {
                numbers.append("\n");
            }
        }

        lineNumberArea.setText(numbers.toString());
    }

    // create MessageArea (non-editable with scrollbars always visible)
    private JScrollPane createMessageArea() {
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setBackground(Color.LIGHT_GRAY);
        messageArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        messageArea.setLineWrap(false);
        messageArea.setRows(18);
        messageArea.setColumns(220);
        messageArea.setText("Mensagem aleatoria de teste para verificar a rolagem.");

        JScrollPane scrollPane = new JScrollPane(messageArea);

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.getHorizontalScrollBar().setVisible(true);
        scrollPane.getVerticalScrollBar().setVisible(true);

        scrollPane.setPreferredSize(new Dimension(1350,200));

        return scrollPane;        

    }

    // create StatusBar (non-editable with scrollbars always visible)
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(1500, 25));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        statusBarLabel = new JLabel("Nenhum arquivo aberto");
        statusBarLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusBarLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        statusBar.add(statusBarLabel, BorderLayout.WEST);

        return statusBar;
    }
}
