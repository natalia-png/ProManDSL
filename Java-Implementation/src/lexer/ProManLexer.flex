/* ============================================
   ProManDSL - Lexer (JFlex)
   Versión simplificada: STRING = palabra sin espacios ni llaves
   ============================================ */

package lexer;

import java_cup.runtime.Symbol;
import parser.sym;

%%

%public
%class ProManLexer
%cup
%unicode
%line
%column

%{

  private Symbol symbol(int type) {
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }

%}

/* ========= Macros ========= */

/* BOM al inicio del archivo (por si aparece) */
BOM          = \uFEFF
WhiteSpace   = [ \t\r\n]+
Number       = [0-9]+
LineComment  = "//".*
/* Cualquier secuencia que no sea espacio, llaves ni salto de línea */
Identifier   = [^ \t\r\n\{\}]+

%%

/* ========= Ignorar BOM, espacios y comentarios ========= */

{BOM}         { /* ignorar BOM */ }
{WhiteSpace}  { /* ignorar espacios y saltos de línea */ }
{LineComment} { /* ignorar comentario de línea */ }

/* ========= Palabras reservadas del DSL ========= */

/* Bloque raíz */
"project"       { return symbol(sym.PROJECT); }
"metadata"      { return symbol(sym.METADATA); }
"client"        { return symbol(sym.CLIENT); }
"context"       { return symbol(sym.CONTEXT); }
"objectives"    { return symbol(sym.OBJECTIVES); }
"scope"         { return symbol(sym.SCOPE); }
"schedule"      { return symbol(sym.SCHEDULE); }
"team"          { return symbol(sym.TEAM); }
"risks"         { return symbol(sym.RISKS); }
"budget"        { return symbol(sym.BUDGET); }
"status"        { return symbol(sym.STATUS); }
"approvals"     { return symbol(sym.APPROVALS); }

/* Metadata */
"code"          { return symbol(sym.CODE); }
"version"       { return symbol(sym.VERSION); }
"created_on"    { return symbol(sym.CREATED_ON); }
"prepared_by"   { return symbol(sym.PREPARED_BY); }

/* Client */
"name"          { return symbol(sym.NAME); }
"contact"       { return symbol(sym.CONTACT); }
"email"         { return symbol(sym.EMAIL); }

/* Context */
"summary"       { return symbol(sym.SUMMARY); }

/* Objectives */
"general"       { return symbol(sym.GENERAL); }
"specific"      { return symbol(sym.SPECIFIC); }

/* Scope */
"include"       { return symbol(sym.INCLUDE); }
"exclude"       { return symbol(sym.EXCLUDE); }

/* Schedule */
"phase"         { return symbol(sym.PHASE); }
"start"         { return symbol(sym.START); }
"end"           { return symbol(sym.END); }

/* Team */
"role"          { return symbol(sym.ROLE); }

/* Risks */
"risk"          { return symbol(sym.RISK); }
"level"         { return symbol(sym.LEVEL); }
"high"          { return symbol(sym.HIGH); }
"medium"        { return symbol(sym.MEDIUM); }
"low"           { return symbol(sym.LOW); }
"mitigation"    { return symbol(sym.MITIGATION); }

/* Budget */
"currency"      { return symbol(sym.CURRENCY); }
"item"          { return symbol(sym.ITEM); }
"cost"          { return symbol(sym.COST); }

/* Status */
"overall"       { return symbol(sym.OVERALL); }
"health"        { return symbol(sym.HEALTH); }
"comments"      { return symbol(sym.COMMENTS); }

/* Approvals */
"required_by"   { return symbol(sym.REQUIRED_BY); }
"approver"      { return symbol(sym.APPROVER); }

/* ========= Números ========= */

{Number} {
    return symbol(sym.NUMBER, Integer.parseInt(yytext()));
}

/* ========= Llaves ========= */

"{"             { return symbol(sym.LBRACE); }
"}"             { return symbol(sym.RBRACE); }

/* ========= Identificadores / textos sin comillas ========= */

{Identifier} {
    return symbol(sym.STRING, yytext());
}

/* ========= Cualquier otro carácter → ERROR ========= */

. {
    throw new RuntimeException(
        "Caracter inesperado '" + yytext() +
        "' en línea " + (yyline + 1) +
        ", columna " + (yycolumn + 1)
    );
}
