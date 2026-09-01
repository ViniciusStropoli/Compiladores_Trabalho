package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutAction extends AbstractAction {

    private final JTextArea messageArea;

    public AboutAction(JTextArea messageArea) {
        super("Equipe");
        this.messageArea = messageArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        messageArea.setText(
                "Equipe:\n"
                        + "Pedro Renan Luna Tavares\n"
                        + "Lucas Shimazaki Battisti\n"
                        + "Vinícius Stropoli de Paula"
        );
    }
}