package org.ui.actions;

import org.compiler.Compiler;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompileAction extends AbstractAction {

    private final JTextArea messageArea;

    public CompileAction(JTextArea messageArea) {
        super("Compile");
        this.messageArea = messageArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Compiler compiler = new Compiler();
        String code = messageArea.getText();
        messageArea.setText(compiler.compile(code));
    }
}