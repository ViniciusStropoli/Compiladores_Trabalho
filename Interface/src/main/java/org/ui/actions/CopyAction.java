package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction {

    public final Editor editor;

    public CopyAction(Editor editor) {
        super("Copy");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getTextArea().copy();
    }
}