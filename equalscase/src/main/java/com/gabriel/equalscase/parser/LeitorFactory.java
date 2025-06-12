package com.gabriel.equalscase.parser;

public class LeitorFactory {

   public static LeitorVendas getLeitor(String bandeira) {
        return switch (bandeira.toLowerCase()) {
            case "mastervisa" -> new LeitorMasterVisa();
            default           -> throw new IllegalArgumentException("Bandeira não suportada: " + bandeira);
        };
    }

}
