package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PasteAction extends AbstractAction {

    public final Editor editor;

    public PasteAction(Editor editor) {
        super("Paste");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getTextArea().paste();
    }
}