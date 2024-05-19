package com.devwebback.devweb.util;

import java.util.Random;

public class GeradorMatricula {
    public static int gerarMatricula(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
