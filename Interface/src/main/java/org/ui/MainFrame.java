package org.ui;

import org.ui.actions.*;
import org.ui.components.SideBar;
import org.ui.keybindings.EditorKeyBindings;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextArea messageArea;
    private JLabel statusBarLabel;

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

        JPanel editorPlaceholder = new JPanel();
        editorPlaceholder.setBackground(Color.WHITE);

        mainPanel.add(editorPlaceholder, BorderLayout.CENTER);
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

    // createMessageArea()
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