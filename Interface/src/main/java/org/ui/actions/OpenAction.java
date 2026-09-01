package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class OpenAction extends AbstractAction {

    private final Editor editor;
    private final JTextArea messageArea;
    private final JLabel statusBarLabel;

    public OpenAction(
            Editor editor,
            JTextArea messageArea,
            JLabel statusBarLabel
    ) {
        super("Open");

        this.editor = editor;
        this.messageArea = messageArea;
        this.statusBarLabel = statusBarLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter(
                        "Arquivos de texto (*.txt)",
                        "txt"
                )
        );

        int result = fileChooser.showOpenDialog(editor);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        try {

            String content =
                    Files.readString(file.toPath());

            editor.getTextArea().setText(content);
            editor.setCurrentFile(file);

            messageArea.setText("");

            statusBarLabel.setText(
                    file.getParent() + " - " + file.getName()
            );

            editor.getTextArea().setCaretPosition(0);
            editor.getTextArea().requestFocusInWindow();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    editor,
                    "Não foi possível abrir o arquivo.\n\n"
                            + ex.getMessage(),
                    "Erro ao abrir",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}