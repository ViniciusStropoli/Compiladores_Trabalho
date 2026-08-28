package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction {

    public CopyAction() {
        super("Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Copy acionado");
    }
}