package main;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java_cup.runtime.Symbol;
import lexer.ProManLexer;
import parser.sym;

public class DebugTokens {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java main.DebugTokens <archivo.proj>");
            System.exit(1);
        }

        String inputFile = args[0];

        try (FileInputStream fis = new FileInputStream(inputFile);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            // 🧹 Saltar BOM si existe
            reader.mark(1);
            int first = reader.read();
            if (first != 0xFEFF && first != -1) {
                reader.reset();
            }

            ProManLexer lexer = new ProManLexer(reader);
            Symbol tok;
            int i = 0;

            while ((tok = lexer.next_token()) != null && tok.sym != sym.EOF) {
                System.out.println(
                    (++i) + " -> sym=" + tok.sym +
                    " (" + symName(tok.sym) + ")" +
                    ", value=" + tok.value
                );
            }

            System.out.println("EOF alcanzado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String symName(int id) {
    try {
        // Intentamos leer los nombres de los tokens que CUP genera
        java.lang.reflect.Field f = parser.sym.class.getField("terminalNames");
        String[] names = (String[]) f.get(null);

        if (id >= 0 && id < names.length) {
            return names[id];
        }
    } catch (Exception ignored) {
        // Si algo falla, devolvemos un nombre genérico
    }

    return "UNKNOWN(" + id + ")";
    }

}
