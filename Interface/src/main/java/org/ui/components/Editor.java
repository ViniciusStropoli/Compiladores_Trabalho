package org.ui.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Editor extends JPanel {

    private final JTextArea editorArea;
    private final JTextArea lineNumberArea;

    public Editor() {
        setLayout(new BorderLayout());

        editorArea = new JTextArea();
        lineNumberArea = new JTextArea();

        configureEditor();
        configureLineNumbers();
        setupDocumentListener();

        JScrollPane scrollPane = new JScrollPane(editorArea);

        scrollPane.setRowHeaderView(lineNumberArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void configureEditor() {

        editorArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        editorArea.setLineWrap(false);
        editorArea.setWrapStyleWord(false);
        editorArea.setTabSize(4);
    }

    private void configureLineNumbers() {

        lineNumberArea.setEditable(false);
        lineNumberArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        lineNumberArea.setBackground(
                Color.LIGHT_GRAY
        );

        lineNumberArea.setForeground(
                Color.DARK_GRAY
        );

        lineNumberArea.setText("1");
    }

    private void setupDocumentListener() {

        editorArea.getDocument().addDocumentListener(
                new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateLineNumbers();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateLineNumbers();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateLineNumbers();
                    }
                }
        );
    }

    private void updateLineNumbers() {

        int lineCount =
                editorArea.getLineCount();

        StringBuilder numbers =
                new StringBuilder();

        for (int i = 1; i <= lineCount; i++) {
            numbers.append(i).append("\n");
        }

        lineNumberArea.setText(
                numbers.toString()
        );
    }

    public JTextArea getTextArea() {
        return editorArea;
    }
}