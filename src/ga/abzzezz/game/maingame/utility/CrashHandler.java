/*
 * Copyright (c) 2019. Abzzezz
 * All code belongs to Abzzezz. Used Code/APIs are mentioned
 */

package ga.abzzezz.game.maingame.utility;

import ga.abzzezz.game.core.utils.Logger;

import java.util.Random;

public class CrashHandler {

    /*
    Every game needs easter eggs ^^
     */
    public static void customLine() {
        String[] out = {"6f6868206d616e21", "77687920646f20796f7520646f20746869733f", "6f757463682c207468617420687572747321",
        "776879797979797979797979797979", "637265657065722c206177206d616e21", "6865686520666f756e64206d6521", "7669737469742068747470733a2f2f747769747465722e636f6d2f41627a7a657a7a3120666f7220667265652063616e64792e2e2e2e2e0a48656865", "01110011 01101111 01101101 01100101 00100000 01101110 01100101 01110111 00100000 01011110 01011110", "01100101 01100001 01110011 01110100 01100101 01110010 00100000 01100101 01100111 01100111 01100111 01100111 01100111 01100111 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 00001010 01100110 01101111 01110101 01101110 01100100 00100000 01101101 01100101 00100000 01100110 01101001 01101110 01100001 01101100 01101100 01111001 00100001"};
        Random r = new Random();
        Logger.log(out[r.nextInt(out.length - 1)], Logger.LogType.INFO);
    }
}