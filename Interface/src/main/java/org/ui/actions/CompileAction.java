package org.ui.actions;

import org.compiler.Compiler;
import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompileAction extends AbstractAction {

    private final Editor editor;
    private final JTextArea messageArea;

    public CompileAction(Editor editor, JTextArea messageArea) {
        super("Compile");
        this.editor = editor;
        this.messageArea = messageArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // clear message area as required
        messageArea.setText("");

        Compiler compiler = new Compiler();
        String code = editor.getTextArea().getText();
        String output = compiler.compile(code);
        messageArea.setText(output);
    }
}