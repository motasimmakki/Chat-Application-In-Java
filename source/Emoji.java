package source;

import source.gagawa.trunk.gagawa.src.com.hp.gagawa.java.elements.*;

import java.io.*;

public class Emoji {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("F:/HNBGU Academics/VI Semester/Minor Project/Source Code/chat_logs/emoji.html");
        file.getParentFile().mkdirs();

        String HTMl_START = "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport'content='width=device-width, initial-scale=1.0'><meta http-equiv='X-UA-Compatible' content='ie=edge'><title>Emoji Test</title></head><body>",
        HTML_END = "</body></html>";


        PrintWriter out = new PrintWriter(file);
        Div div = new Div();

        div.appendText("Hey There");

        out.println(HTMl_START + div + HTML_END);
        // System.out.println("Hey Emoji");

        out.flush();  
        out.close();  
    }
}
