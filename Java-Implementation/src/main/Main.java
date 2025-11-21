package main;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java_cup.runtime.Symbol;
import lexer.ProManLexer;
import parser.ProManParser;
import ast.ProjectNode;
import transpiler.HtmlGenerator;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java main.Main <archivo.proj> [salida.html]");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = "acta_proyecto.html";
        if (args.length >= 2) {
            outputFile = args[1];
        }

        try {
            // Abrimos el .proj en UTF-8 y eliminamos BOM si existe
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);

            reader.mark(1);
            int firstChar = reader.read();
            if (firstChar != 0xFEFF) { // si no es BOM, devolvemos el carácter
                reader.reset();
            }

            ProManLexer lexer = new ProManLexer(reader);
            ProManParser parser = new ProManParser(lexer);

            // Parseamos el DSL y obtenemos el AST
            Symbol root = parser.parse();
            ProjectNode project = (ProjectNode) root.value;

            // Generamos el HTML
            String html = HtmlGenerator.generate(project);

            // Escribimos el HTML en UTF-8
            try (OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(outputFile),
                    StandardCharsets.UTF_8)) {

                writer.write(html);
            }

            System.out.println("Acta generada correctamente en: " + outputFile);

        } catch (Exception e) {
            System.err.println("Error al procesar el archivo DSL:");
            e.printStackTrace();
        }
    }
}
