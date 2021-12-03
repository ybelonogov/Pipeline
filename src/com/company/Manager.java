package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class Manager {


    static void run(String configFile) {
        config CONFIG = new config(configFile);
        System.out.println(CONFIG);
    }

}

class config {
    int exeCount;
    String in;
    String out;
    String reader;
    String writer;
    String[] exe;
    final static String IN = "input file";
    final static String OUT = "output file";
    final static String READER = "reader";
    final static String WRITER = "writer";
    final static String COUNT = "executors count";
    final static String EXE = "executor number ";
    final static String SPLIT_CHAR = ":=";

    public config(String configFile) {
        HashMap<String, String> result = new HashMap<String, String>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configFile);
        } catch (FileNotFoundException e) {
            System.out.println("manager config not found");
            System.exit(1);
        }
        BufferedReader bReader = new BufferedReader(fileReader);
        String line = null;
        try {
            line = bReader.readLine();
        } catch (IOException e) {
            System.out.println("manager config file error");
            System.exit(1);

        }
        while (line != null) {
            line = line.trim();
            String[] words = line.split(SPLIT_CHAR);
            if (words.length == 2)
                result.put(words[0], words[1]);
            try {
                line = bReader.readLine();
            } catch (IOException e) {
                System.out.println("manager config file error");
                System.exit(1);
            }
        }
        try {
            bReader.close();
        } catch (IOException e) {
            System.out.println("manager config file error");
            System.exit(1);
        }
        in=result.get(IN);
        out=result.get(OUT);

        try {
            exeCount = Integer.parseInt(result.get(COUNT));
        }catch (NumberFormatException e) {
            System.out.println("Wrong executors count format, rule of config: executors count:='NUMBER of executors' ");
            System.exit(1);
        }
        exe= new String[exeCount];
        for (int i =0;i<exeCount;i++){
            exe[i]=result.get(EXE+Integer.toString(i+1));

        }
        reader=result.get(READER);
        writer=result.get(WRITER);

    }

    @Override
    public String toString() {
        return "config{" +
                ", exeCount=" + exeCount +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                ", reader='" + reader + '\'' +
                ", writer='" + writer + '\'' +
                ", exe=" + Arrays.toString(exe) +
                '}';
    }
}