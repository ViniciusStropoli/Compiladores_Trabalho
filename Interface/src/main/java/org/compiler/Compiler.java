package org.compiler;

import org.sintatic.*;

public class Compiler {

    public Compiler() {}

    public String compile(String sourceCode) {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();

        lexico.setInput(sourceCode);

        try {
            sintatico.parse(lexico, semantico);
            return "programa compilado com sucesso";
        } catch (LexicalError e) {
            return formatLexicalError(sourceCode, e);
        } catch (SyntaticError e) {
            return formatSyntacticError(sourceCode, e);
        } catch (SemanticError e) {
            return formatSemanticError(sourceCode, e);
        }
    }

    private String formatLexicalError(String sourceCode, LexicalError e) {
        int pos = e.getPosition();
        int line = computeLine(sourceCode, pos);
        return "linha " + line + ": erro léxico";
    }

    private String formatSyntacticError(String sourceCode, SyntaticError e) {
        int pos = e.getPosition();
        int line = computeLine(sourceCode, pos);

        String found = extractErrorToken(sourceCode, pos);
        if (found == null || found.isEmpty()) {
            found = "EOF";
        }

        String expected = e.getMessage();
        if (expected == null || expected.trim().isEmpty()) {
            expected = "símbolo";
        }

        return "linha " + line + ": encontrado " + found + " esperado " + expected;
    }

    private String formatSemanticError(String sourceCode, SemanticError e) {
        int pos = e.getPosition();
        int line = computeLine(sourceCode, pos);
        return "linha " + line + ": erro semântico";
    }

    private int computeLine(String source, int position) {
        if (source == null || source.isEmpty()) {
            return 1;
        }

        int pos = Math.max(0, Math.min(position, source.length()));
        int line = 1;
        for (int i = 0; i < pos; i++) {
            if (source.charAt(i) == '\n') {
                line++;
            }
        }
        return line;
    }

    private String extractErrorToken(String source, int position) {
        if (source == null || source.isEmpty() || position < 0) {
            return "EOF";
        }

        if (position >= source.length()) {
            return "EOF";
        }

        char c = source.charAt(position);
        if (Character.isWhitespace(c)) {
            for (int i = position; i < source.length(); i++) {
                if (!Character.isWhitespace(source.charAt(i))) {
                    return extractErrorToken(source, i);
                }
            }
            return "EOF";
        }

        if (",[](){}+-*/<>=$".indexOf(c) >= 0) {
            return String.valueOf(c);
        }

        int start = position;
        while (start > 0 && (Character.isLetterOrDigit(source.charAt(start - 1)) || source.charAt(start - 1) == '_')) {
            start--;
        }

        int end = position;
        while (end < source.length() && (Character.isLetterOrDigit(source.charAt(end)) || source.charAt(end) == '_')) {
            end++;
        }

        return source.substring(start, end);
    }

}
