package org.ui;

import org.ui.actions.*;
import org.ui.components.SideBar;
import org.ui.keybindings.EditorKeyBindings;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextArea messageArea;
    private JLabel statusBarLabel;
    private JTextArea editorArea;
    private JTextArea lineNumberArea;

    private Action newAction;
    private Action openAction;
    private Action saveAction;
    private Action copyAction;
    private Action pasteAction;
    private Action cutAction;
    private Action compileAction;
    private Action aboutAction;

    public MainFrame() {
        super("Main Frame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setResizable(false);
        setLayout(new BorderLayout());

        createActions();
        createGUI();
        setupKeyBindings();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    private void createActions() {
        newAction = new NewAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        copyAction = new CopyAction();
        pasteAction = new PasteAction();
        cutAction = new CutAction();
        compileAction = new CompileAction();
        aboutAction = new AboutAction();
    }

    private void createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Split between the editor (top) and the message area (bottom).
        // The divider can be dragged to resize both vertically.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createEditor(), createMessageArea());
        splitPane.setResizeWeight(1.0);
        splitPane.setDividerLocation(520);
        splitPane.setBorder(null);

        mainPanel.add(splitPane, BorderLayout.CENTER);


        mainPanel.add(createMessageArea(), BorderLayout.SOUTH);


        add(new SideBar(
                newAction,
                openAction,
                saveAction,
                copyAction,
                pasteAction,
                cutAction,
                compileAction,
                aboutAction
        ), BorderLayout.WEST);

        add(mainPanel, BorderLayout.CENTER);
        add(createStatusBar(), BorderLayout.SOUTH);
    }

    private void setupKeyBindings() {
        EditorKeyBindings.register(
                getRootPane(),
                newAction,
                openAction,
                saveAction,
                copyAction,
                pasteAction,
                cutAction,
                compileAction,
                aboutAction
        );
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

        scrollPane.setPreferredSize(new Dimension(1350, 200));

        return scrollPane;
    }

    // create StatusBar (non-editable with scrollbars always visible)
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel();

        statusBar.setLayout(new BorderLayout());
        statusBar.setPreferredSize(new Dimension(1500, 25));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        statusBarLabel = new JLabel("Nenhum arquivo aberto");
        statusBarLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusBarLabel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        statusBar.add(statusBarLabel, BorderLayout.WEST);

        return statusBar;
    }
}