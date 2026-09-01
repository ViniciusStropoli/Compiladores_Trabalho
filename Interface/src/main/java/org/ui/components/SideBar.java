package org.ui.components;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SideBar extends JPanel {

    public SideBar(
            Action newAction,
            Action openAction,
            Action saveAction,
            Action copyAction,
            Action pasteAction,
            Action cutAction,
            Action compileAction,
            Action aboutAction
    ) {

        setBackground(Color.LIGHT_GRAY);

        setLayout(
                new BoxLayout(
                        this,
                        BoxLayout.Y_AXIS
                )
        );

        setPreferredSize(
                new Dimension(150, 800)
        );

        addButton(
                createJButton(
                        "Novo",
                        "[Ctrl + N]",
                        "/icons/new.png",
                        newAction
                )
        );

        addButton(
                createJButton(
                        "Abrir",
                        "[Ctrl + O]",
                        "/icons/open.png",
                        openAction
                )
        );

        addButton(
                createJButton(
                        "Salvar",
                        "[Ctrl + S]",
                        "/icons/save.png",
                        saveAction
                )
        );

        addButton(
                createJButton(
                        "Copiar",
                        "[Ctrl + C]",
                        "/icons/copy.png",
                        copyAction
                )
        );

        addButton(
                createJButton(
                        "Colar",
                        "[Ctrl + V]",
                        "/icons/paste.png",
                        pasteAction
                )
        );

        addButton(
                createJButton(
                        "Cortar",
                        "[Ctrl + X]",
                        "/icons/cut.png",
                        cutAction
                )
        );

        addButton(
                createJButton(
                        "Compilar",
                        "[F7]",
                        "/icons/compile.png",
                        compileAction
                )
        );

        addButton(
                createJButton(
                        "Sobre",
                        "[F1]",
                        "/icons/about.png",
                        aboutAction
                )
        );
    }

    private void addButton(JButton button) {
        add(button);
        add(Box.createRigidArea(new Dimension(0, 3)));
    }

    private JButton createJButton(
            String text,
            String shortcut,
            String iconPath,
            Action action
    ) {

        JButton button = new JButton(
                "<html><center>" +
                        text +
                        "<br>" +
                        "<font color='#777777'>" +
                        shortcut +
                        "</font>" +
                        "</center></html>"
        );

        button.addActionListener(action);

        URL url = getClass().getResource(iconPath);

        if (url != null) {

            ImageIcon icon = new ImageIcon(url);

            Image image = icon.getImage()
                    .getScaledInstance(
                            23,
                            23,
                            Image.SCALE_SMOOTH
                    );

            button.setIcon(
                    new ImageIcon(image)
            );

        } else {
            System.err.println(
                    "Imagem não encontrada em: " + iconPath
            );
        }

        button.setVerticalTextPosition(
                SwingConstants.BOTTOM
        );

        button.setHorizontalTextPosition(
                SwingConstants.CENTER
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        Dimension buttonSize =
                new Dimension(120, 90);

        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);

        return button;
    }
}