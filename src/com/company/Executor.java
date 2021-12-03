package com.company;

import java.io.IOException;
import java.util.HashMap;

public class Executor {
    final static String IN="input";
    final static String OUT="output";
    final static String BUFFER="bufferSize";
    final static String SHIFT="shiftSize";
    final static String SPLIT_CHAR=":=";
    static byte[] rotate(final HashMap<String, String> CONFIG, byte[] data) throws IOException {
        int len = Integer.parseInt(CONFIG.get(BUFFER)) * 8;
        int shift = Integer.parseInt(CONFIG.get(SHIFT)) % len;

        System.out.println();
        byte[] result = new byte[Integer.parseInt(CONFIG.get(BUFFER))];
        shift = shift + len;
        for (int i = 0; i < len; i++) {
            int num = i / 8;
            int bit = 7 - (i) % 8;
            int newBit = (i + shift) % len;
            int newNum = newBit / 8;
            newBit = 7 - newBit % 8;
            if (get(data[num], bit)) {
                result[newNum] |= 1 << newBit;
            }
        }
//        for (byte b : result) {
//            System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//        }
        return result;
    }

    static boolean get(byte b, int pos) {
        return (b & (1 << pos)) != 0;
    }
}
