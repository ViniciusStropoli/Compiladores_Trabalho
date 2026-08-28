package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OpenAction extends AbstractAction {

    public OpenAction() {
        super("Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Open acionado");
    }
}