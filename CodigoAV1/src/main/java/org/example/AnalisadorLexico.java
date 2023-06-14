package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnalisadorLexico {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a Cadeia: ");
        String cadeia = scanner.nextLine();

        ArrayList<String> alfabetoNaoValido = new ArrayList<>(List.of("j", "w", "ç", "k", "y", "h", "q", "/", "(", ")", "&", "%", "$", "#", "@", "!"));
        ArrayList<Character> vogais = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u'));
        ArrayList<Character> iniciaisReservadas = new ArrayList<>(List.of('z', 'x'));


        String[] letras = cadeia.toLowerCase().split("");
        int size = cadeia.length();

        if (size > 10 ) {
            size = 10;
        }

        // Verifica se a newCadeia passada possui algum caractere invalido de acordo com a regra
        if (Arrays.stream(letras).anyMatch(alfabetoNaoValido::contains)) {
            excecao("Cadeia possui caracteres invalidos!");
        }

        // Verifica se a newCadeia começa com consoante
        if (!Character.isLetter(cadeia.charAt(0)) || isVogal(cadeia.charAt(0), vogais)) {
            excecao("A Cadeia nao pode começar com vogal!");
        }

        // Verifica se a newCadeia inicia com um caractere reservado
        if (!Character.isLetter(cadeia.charAt(0)) || iniciaisReservadas.contains(cadeia.charAt(0))) {
            excecao("Palavras iniciadas com as letras 'z' e 'x' são reservadas pelo sistema!");
        }

        // Verifica se o ultimo caracter da newCadeia é um número, caso seja, ele será ignorado por estar correto
        if (Character.isDigit(cadeia.charAt(size - 1))) {
            size -= 1;
        }


        // Verifica se a sequencia da newCadeia segue a regra de consoante seguida de vogal
        for (int i = 0; i < size - 1; i += 2) {
            if (!isConsoante(cadeia.charAt(i), vogais) || !isVogal(cadeia.charAt(i + 1), vogais)) {
                excecao("Cadeia possui sequencia invalida de caracteres!");
            }
        }

        // Caso a palavra possua um número impar de caracteres, é necessário verificar se o último caractere é uma consoante ou um número
        if (size % 2 != 0 && !isConsoante(cadeia.charAt(size - 1), vogais)) {
            excecao("Cadeia possui sequencia invalida de caracteres!");
        }

        System.out.println("Foi possivel ler");

    }

    private static boolean isVogal(char caracter, ArrayList<Character> vogais) {
        return vogais.contains(caracter);
    }

    private static boolean isConsoante(char caracter, ArrayList<Character> vogais) {
        return !isVogal(caracter, vogais) && Character.isLetter(caracter);
    }

    private static RuntimeException excecao(String ex) {
        throw new RuntimeException(ex);
    }
}
