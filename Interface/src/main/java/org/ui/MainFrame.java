package org.ui;

import org.ui.actions.*;
import org.ui.components.Editor;
import org.ui.components.SideBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Editor editor;

    private JTextArea messageArea;
    private JLabel statusBarLabel;

    private JScrollPane messageScrollPane;
    private JPanel statusBar;

    // Actions
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

        editor = new Editor();

        createMessageArea();
        createStatusBar();

        createActions();
        createGUI();
        setupKeyBindings();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                editor,
                messageScrollPane
        );

        splitPane.setResizeWeight(1.0);
        splitPane.setDividerLocation(520);
        splitPane.setBorder(null);

        mainPanel.add(
                splitPane,
                BorderLayout.CENTER
        );

        add(
                new SideBar(
                        newAction,
                        openAction,
                        saveAction,
                        copyAction,
                        pasteAction,
                        cutAction,
                        compileAction,
                        aboutAction
                ),
                BorderLayout.WEST
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        add(
                statusBar,
                BorderLayout.SOUTH
        );
    }

    private void createActions() {

        newAction = new NewAction(
                editor,
                messageArea,
                statusBarLabel
        );

        openAction = new OpenAction(
                editor,
                messageArea,
                statusBarLabel
        );

        saveAction = new SaveAction(
                editor,
                messageArea,
                statusBarLabel
        );

        copyAction = new CopyAction(editor);

        pasteAction = new PasteAction(editor);

        cutAction = new CutAction(editor);

        compileAction = new CompileAction(
                editor,
                messageArea
        );

        aboutAction = new AboutAction(
                messageArea
        );
    }

    private void setupKeyBindings() {

        InputMap inputMap =
                getRootPane().getInputMap(
                        JComponent.WHEN_IN_FOCUSED_WINDOW
                );

        ActionMap actionMap =
                getRootPane().getActionMap();

        inputMap.put(
                KeyStroke.getKeyStroke("control N"),
                "new"
        );
        actionMap.put(
                "new",
                newAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("control O"),
                "open"
        );
        actionMap.put(
                "open",
                openAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("control S"),
                "save"
        );
        actionMap.put(
                "save",
                saveAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("control C"),
                "copy"
        );
        actionMap.put(
                "copy",
                copyAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("control V"),
                "paste"
        );
        actionMap.put(
                "paste",
                pasteAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("control X"),
                "cut"
        );
        actionMap.put(
                "cut",
                cutAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("F7"),
                "compile"
        );
        actionMap.put(
                "compile",
                compileAction
        );

        inputMap.put(
                KeyStroke.getKeyStroke("F1"),
                "about"
        );
        actionMap.put(
                "about",
                aboutAction
        );
    }

    private void createMessageArea() {

        messageArea = new JTextArea();

        messageArea.setEditable(false);
        messageArea.setBackground(Color.LIGHT_GRAY);
        messageArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        12
                )
        );

        messageArea.setLineWrap(false);
        messageArea.setRows(18);
        messageArea.setColumns(220);

        messageArea.setText(
                ""
        );

        messageScrollPane = new JScrollPane(
                messageArea
        );

        messageScrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        messageScrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
        );

        messageScrollPane.setPreferredSize(
                new Dimension(1350, 200)
        );
    }

    private void createStatusBar() {

        statusBar = new JPanel(
                new BorderLayout()
        );

        statusBar.setPreferredSize(
                new Dimension(1500, 25)
        );

        statusBar.setBackground(
                Color.LIGHT_GRAY
        );

        statusBar.setBorder(
                BorderFactory.createLineBorder(
                        Color.GRAY,
                        1
                )
        );

        statusBarLabel = new JLabel(
                "Nenhum arquivo aberto"
        );

        statusBarLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        statusBarLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        3,
                        5,
                        3,
                        5
                )
        );

        statusBar.add(
                statusBarLabel,
                BorderLayout.WEST
        );
    }
}