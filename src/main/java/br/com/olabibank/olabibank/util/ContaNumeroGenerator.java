package br.com.olabibank.olabibank.util;

import java.util.Random;

public class ContaNumeroGenerator {

    private static final Random RANDOM = new Random();

    public static String gerarNumeroConta() {
        int parte1 = RANDOM.nextInt(90000) + 10000;
        int parte2 = RANDOM.nextInt(10);
        return String.format("%06d-%01d", parte1, parte2);
    }
}
