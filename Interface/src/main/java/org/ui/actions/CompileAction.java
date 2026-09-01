package org.ui.actions;

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

        messageArea.setText(
                "compilação de programas ainda não foi implementada"
        );
    }
}