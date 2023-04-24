package com.svalero.streams;

import java.util.ArrayList;
import java.util.List;

public class App {
        
        public static void changeValue(List<String> exampleToModify){
                System.out.println("[Método Local] Antes: " + exampleToModify);
                exampleToModify.add("Elemento 2");
                System.out.println("[Método Local] Después: " + exampleToModify);
        }
        public static void main(String[] args) {
                List<String> example = new ArrayList<String>();
                example.add("Elemento 1");
                System.out.println("[Método Main] Antes: " + example);
                changeValue(example);
                System.out.println("?????[Método Main] Después: " + example);
        }
}