package org.compiler;

import org.sintatic.LexicalError;
import org.sintatic.Lexico;
import org.sintatic.Token;

public class Compiler {

    public Compiler() {}

    public String compile(String sourceCode) {
        Lexico lexico = new Lexico();
        lexico.setInput (sourceCode);

        try {
            Token t = null;
            StringBuilder result = new StringBuilder();

            while ((t = lexico.nextToken()) != null) {
                result.append(t.getLexeme()).append("\n");

                // só escreve o lexema, necessário escrever t.getId, t.getPosition()

                // t.getId () - retorna o identificador da classe (ver Constants.java)
                // necessário adaptar, pois deve ser apresentada a classe por extenso

                // t.getPosition () - retorna a posição inicial do lexema no editor
                // necessário adaptar para mostrar a linha

                // esse código apresenta os tokens enquanto não ocorrer erro
                // no entanto, os tokens devem ser apresentados SÓ se não ocorrer erro,
                // necessário adaptar para atender o que foi solicitado
            }

            return result.toString();

        } catch( LexicalError e ) {  // tratamento de erros
            String errorMessage = e.getMessage() + " em " + e.getPosition();
            return errorMessage;

            // e.getMessage() - retorna a mensagem de erro de SCANNER_ERRO (ver ScannerConstants.java)
            // necessário adaptar conforme o enunciado da parte 2

            // e.getPosition() - retorna a posição inicial do erro
            // necessário adaptar para mostrar a linha
        }
    }

}
