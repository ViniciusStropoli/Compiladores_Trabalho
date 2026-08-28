package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveAction extends AbstractAction {

    public SaveAction() {
        super("Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save acionado");
    }
}