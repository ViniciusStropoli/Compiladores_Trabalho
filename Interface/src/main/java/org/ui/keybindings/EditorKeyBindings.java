package org.ui.keybindings;

import javax.swing.*;

import java.awt.event.ActionEvent;

public class EditorKeyBindings {

    private EditorKeyBindings() {
        // Utility class
    }

    public static void register(
            JRootPane rootPane,
            Action newAction,
            Action openAction,
            Action saveAction,
            Action copyAction,
            Action pasteAction,
            Action cutAction,
            Action compileAction,
            Action aboutAction
    ) {

        InputMap inputMap =
                rootPane.getInputMap(
                        JComponent.WHEN_IN_FOCUSED_WINDOW
                );

        ActionMap actionMap =
                rootPane.getActionMap();

        register(
                inputMap,
                actionMap,
                "control N",
                "new",
                newAction
        );

        register(
                inputMap,
                actionMap,
                "control O",
                "open",
                openAction
        );

        register(
                inputMap,
                actionMap,
                "control S",
                "save",
                saveAction
        );

        register(
                inputMap,
                actionMap,
                "control C",
                "copy",
                copyAction
        );

        register(
                inputMap,
                actionMap,
                "control V",
                "paste",
                pasteAction
        );

        register(
                inputMap,
                actionMap,
                "control X",
                "cut",
                cutAction
        );

        register(
                inputMap,
                actionMap,
                "F7",
                "compile",
                compileAction
        );

        register(
                inputMap,
                actionMap,
                "F1",
                "about",
                aboutAction
        );
    }

    private static void register(
            InputMap inputMap,
            ActionMap actionMap,
            String keyStroke,
            String actionName,
            Action action
    ) {

        inputMap.put(
                KeyStroke.getKeyStroke(keyStroke),
                actionName
        );

        actionMap.put(
                actionName,
                action
        );
    }
}