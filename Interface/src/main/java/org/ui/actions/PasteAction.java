package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PasteAction extends AbstractAction {

    public PasteAction() {
        super("Paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Paste acionado");
    }
}