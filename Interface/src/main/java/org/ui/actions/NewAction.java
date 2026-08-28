package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractAction {

    public NewAction() {
        super("New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("New acionado");
    }
}