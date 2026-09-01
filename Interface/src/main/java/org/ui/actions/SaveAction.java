package org.ui.actions;

import org.ui.components.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SaveAction extends AbstractAction {

    private final Editor editor;
    private final JTextArea messageArea;
    private final JLabel statusBarLabel;

    public SaveAction(
            Editor editor,
            JTextArea messageArea,
            JLabel statusBarLabel
    ) {
        super("Save");

        this.editor = editor;
        this.messageArea = messageArea;
        this.statusBarLabel = statusBarLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        File file = editor.getCurrentFile();

        // Arquivo novo
        if (file == null) {

            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter(
                            "Arquivos de texto (*.txt)",
                            "txt"
                    )
            );

            int result =
                    fileChooser.showSaveDialog(editor);

            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            file = fileChooser.getSelectedFile();

            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(
                        file.getAbsolutePath() + ".txt"
                );
            }

            editor.setCurrentFile(file);
        }

        try {

            Files.writeString(
                    file.toPath(),
                    editor.getTextArea().getText()
            );

            messageArea.setText("");

            statusBarLabel.setText(
                    file.getParent() + " - " + file.getName()
            );

            editor.getTextArea().requestFocusInWindow();

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(
                    editor,
                    "Não foi possível salvar o arquivo.\n\n"
                            + ex.getMessage(),
                    "Erro ao salvar",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}