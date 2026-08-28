package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CutAction extends AbstractAction {

    public CutAction() {
        super("Cut");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Cut acionado");
    }
}