package org.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CompileAction extends AbstractAction {

    public CompileAction() {
        super("Compile");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Compile acionado");
    }
}