package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractAction {

    private final Editor editor;
    private final JTextArea messageArea;
    private final JLabel statusBarLabel;

    public NewAction(
            Editor editor,
            JTextArea messageArea,
            JLabel statusBarLabel
    ) {
        super("New");

        this.editor = editor;
        this.messageArea = messageArea;
        this.statusBarLabel = statusBarLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        editor.clear();

        messageArea.setText("");

        statusBarLabel.setText("");
    }
}