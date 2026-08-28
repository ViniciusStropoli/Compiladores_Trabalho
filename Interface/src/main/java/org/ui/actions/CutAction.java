package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CutAction extends AbstractAction {

    public final Editor editor;

    public CutAction(Editor editor) {
        super("Cut");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getTextArea().cut();
    }
}