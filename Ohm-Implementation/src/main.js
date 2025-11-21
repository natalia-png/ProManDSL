// src/main.js
import fs from "fs";
import path from "path";
import * as ohm from "ohm-js";
import { fileURLToPath } from "url";
import { generateHtmlFromProject } from "./htmlGenerator.js";

// Soporte para __dirname en ES modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

function loadGrammar() {
  const grammarPath = path.join(__dirname, "ProMan.ohm");
  const grammarSource = fs.readFileSync(grammarPath, "utf8");
  return ohm.grammar(grammarSource);
}

function buildSemantics(grammar) {
  return grammar.createSemantics().addOperation("ast", {
    Project(_sp1, _kwProject, _sp2, name, _sp3, _lb, _sp4, body) {
      return {
        name: name.sourceString,   // el identificador después de 'project'
        body: body.sourceString,   // TODO el contenido del acta
      };
    }
  });
}



function main() {
  const args = process.argv.slice(2);
  if (args.length < 1) {
    console.error("Uso: node src/main.js <ruta/al/archivo.proj> [salida.html]");
    process.exit(1);
  }

  const inputPath = path.resolve(args[0]);
  const outputPath = path.resolve(args[1] || "acta_proyecto_ohm.html");

  // 1. Cargar gramática
  const grammar = loadGrammar();
  const semantics = buildSemantics(grammar);

  // 2. Leer el archivo DSL (tu proyecto1.proj)
  const dslSource = fs.readFileSync(inputPath, "utf8");

  // 3. Parsear empezando desde la regla Project
  const matchResult = grammar.match(dslSource, "Project");
  if (matchResult.failed()) {
    console.error("Error de sintaxis en el DSL:");
    console.error(matchResult.message);
    process.exit(1);
  }

  // 4. Obtener el "AST" simplificado
  const projectAst = semantics(matchResult).ast();

  // 5. Generar HTML usando nuestro generador
  const html = generateHtmlFromProject(projectAst);

  // 6. Guardar HTML
  fs.writeFileSync(outputPath, html, "utf8");
  console.log("HTML (Ohm) generado en:", outputPath);
}

main();
