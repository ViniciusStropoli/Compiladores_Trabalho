package org.compiler;

import org.sintatic.LexicalError;
import org.sintatic.Lexico;
import org.sintatic.Token;

import java.util.ArrayList;
import java.util.List;

public class Compiler {

    public Compiler() {}

    public String compile(String sourceCode) {
        Lexico lexico = new Lexico();
        lexico.setInput(sourceCode);

        List<Token> tokens = new ArrayList<>();

        try {
            Token t;

            while ((t = lexico.nextToken()) != null) {
                tokens.add(t);
            }

            // build tokens output: line - classe por extenso - lexema
            StringBuilder result = new StringBuilder();

            for (Token tok : tokens) {
                int line = computeLine(sourceCode, tok.getPosition());
                result.append(line)
                        .append(" - ")
                        .append(tokenClassName(tok.getId()))
                        .append(" - ")
                        .append(tok.getLexeme())
                        .append("\n");
            }

            result.append("\nprograma compilado com sucesso");

            return result.toString();

        } catch (LexicalError e) {  // tratamento de erros
            int pos = e.getPosition();
            int line = computeLine(sourceCode, pos);
            String msg = e.getMessage();

            StringBuilder error = new StringBuilder();
            error.append("programa apresenta erro\n");

            if (msg != null) {
                switch (msg) {
                    case "simbolo invalido":
                        char bad = '?';
                        if (pos >= 0 && pos < sourceCode.length())
                            bad = sourceCode.charAt(pos);
                        error.append("símbolo inválido, linha ").append(line).append(", símbolo: ").append(bad);
                        break;
                    case "palavra reservada inválida":
                        String word = extractWord(sourceCode, pos);
                        error.append("palavra reservada inválida, linha ").append(line).append(", palavra: ").append(word);
                        break;
                    case "identificador inválido":
                        error.append("identificador inválido, linha ").append(line);
                        break;
                    case "constante_string inválida":
                        error.append("constante_string inválida, linha ").append(line);
                        break;
                    case "comentário inválido ou não finalizado":
                        error.append("comentário inválido ou não finalizado, linha ").append(line);
                        break;
                    default:
                        error.append(msg).append(" - linha ").append(line);
                }
            } else {
                error.append("Erro léxico na linha ").append(line);
            }

            return error.toString();
        }
    }

    private int computeLine(String source, int position) {
        if (source == null || source.isEmpty())
            return 1;

        int pos = Math.max(0, Math.min(position, source.length()));
        int line = 1;
        for (int i = 0; i < pos; i++) {
            if (source.charAt(i) == '\n')
                line++;
        }
        return line;
    }

    private String extractWord(String source, int position) {
        if (source == null || source.isEmpty() || position < 0 || position >= source.length())
            return "";

        int start = position;
        int len = source.length();

        // move start back to the beginning of the word if needed
        while (start > 0) {
            char c = source.charAt(start);
            if (!Character.isLetterOrDigit(c) && c != '_') break;
            start--;
        }
        // adjust start forward
        if (start < 0) start = 0;
        while (start < len && !Character.isLetterOrDigit(source.charAt(start)) && source.charAt(start) != '_') start++;

        int end = start;
        while (end < len && (Character.isLetterOrDigit(source.charAt(end)) || source.charAt(end) == '_')) end++;

        return source.substring(start, end);
    }

    private String tokenClassName(int id) {
        // Map token id to a human readable class name
        switch (id) {
            case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14:
                return "palavra reservada";
            case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24:
            case 25: case 26: case 27: case 28: case 29: case 30: case 31: case 32:
                return "símbolo";
            case 33: case 34: case 35: case 36:
                return "identificador";
            case 37:
                return "constante_int";
            case 38:
                return "constante_float";
            case 39:
                return "constante_string";
            case 2:
                return "RE";
            default:
                return "classe_desconhecida";
        }
    }

}
