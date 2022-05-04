package com.company;

import java.util.*;
import java.util.regex.*;

public class Main {

    private static Map<String, Pattern> lex = new HashMap<>();

    static {


        lex.put("DIGIT", Pattern.compile("\\s*0|([1-9][0-9]*)\\s*"));
        lex.put("ASSIGN_OP", Pattern.compile("\\s*set\\s*"));
        lex.put("KWSTART", Pattern.compile("\\s*start\\n\\s*"));
        lex.put("KWEND", Pattern.compile("\\s*end\\n\\s*"));
        lex.put("HELP", Pattern.compile("\\s*\\?\\n\\s*"));
        lex.put("ENDOFCOMMAND", Pattern.compile("\\s*\\n\\s*"));
        lex.put("INTERFACE", Pattern.compile("\\s*interface\\s*"));
        lex.put("ADDRESS", Pattern.compile("\\s*([0-9]{1,3}[\\.]){3}[0-9]{1,3}\\s*"));
        lex.put("PROTOCOL", Pattern.compile("\\s*abc\\s*"));


    }


    public static void main(String[] args) {

        String srcExample = "start\ninterface 3 set abc end\n";

        List<Token> tokens = new LinkedList<>();

        for (String lexemName: lex.keySet()) {
            Matcher m = lex.get(lexemName).matcher(srcExample);
            if (m.find()) {
                System.out.println(lexemName + " found ");
                tokens.add(new Token(lexemName, m.group().replaceAll("\\s", "")));
            }
        }

        for (Token token: tokens) {
            System.out.println(token);
        }


    }

}

class Token {

    private String type;
    private String value;

    public Token(String type, String value){
        this.type = type;
        this.value = value;
    }


    @Override
    public String toString(){
        return "TOKEN[type=\"" + this.type + "\", value=\"" + this.value + "\"]";
    }

}
