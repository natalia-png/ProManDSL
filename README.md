# ProManDSL — Lenguaje Específico de Dominio para Actas de Proyecto  
---

ProManDSL es un **Lenguaje Específico de Dominio (DSL)** diseñado para estructurar y formalizar **Actas de Proyecto** de forma clara, validable y procesable por compiladores.  

El proyecto incluye **dos implementaciones funcionales**:

- **Implementación 1 — Java (JFlex + CUP)**  
  Compila el DSL, construye el AST y genera un **HTML formal** con la estructura completa del acta.

- **Implementación 2 — Ohm-JS (Node + Ohm)**  
  Lee el mismo DSL, muestra el **código fuente coloreado** y genera un segundo HTML estético con la representación visual del acta.

---

# ■ 1. Definición del DSL — *ProManDSL*

La estructura general del lenguaje es:

project <Ident> {
metadata { ... }
client { ... }
context { ... }
objectives { ... }
scope { ... }
schedule { ... }
team { ... }
risks { ... }
budget { ... }
status { ... }
approvals { ... }
}



Cada bloque corresponde a una sección clásica de un acta de inicio de proyecto.

---

# ■ 2. Axioma, Terminales y No Terminales

## ◎ Axioma del lenguaje
**Axioma:** `Project`  
Es el punto de entrada del DSL y representa el documento completo.

Todo archivo válido inicia con:

project <NombreProyecto> {
...
}


---

## ◎ Terminales del ProManDSL

Los terminales son símbolos irreducibles producidos por el analizador léxico.

### ● A. Palabras reservadas (estructura del acta)
project
metadata
client
context
objectives
specific
scope
include
exclude
schedule
phase
start
end
team
role
risks
risk
level
mitigation
budget
item
cost
currency
status
overall
health
comments
approvals
required_by
approver

markdown
Copiar código

### ● B. Identificadores (`Ident`)
Valores definidos por el usuario **sin comillas**, usados para:

- nombres de proyecto  
- nombres de participantes  
- fechas formateadas con `_`  
- roles  
- estados  
- descripciones cortas  

Ejemplos reales del proyecto:  
`HE_Arquitectos`, `2025_11_25`, `En_planificacion_inicial`.

### ● C. Números (`Number`)
Usados principalmente en el presupuesto para representar valores enteros positivos.

---

## ◎ No Terminales del DSL

Representan componentes sintácticos mayores:

- `Project` — Documento completo  
- `Metadata` — Código, versión, fecha de creación, autor  
- `Client` — Nombre, contacto y correo  
- `Context` — Justificación y descripción  
- `Objectives` — Objetivo general + específicos  
- `Specific` — Objetivo específico  
- `Scope` — Elementos incluidos y excluidos  
  - `Includes`, `Include`  
  - `Excludes`, `Exclude`  
- `Schedule` — Cronograma general  
  - `Phase` — Nombre, inicio y fin  
- `Team` — Miembros del equipo y roles  
- `Role` — Rol y responsable  
- `Risks` — Conjunto de riesgos  
- `Risk` — Descripción, nivel y mitigación  
- `RiskLevel` — `high | medium | low`  
- `Budget` — Sección de presupuesto  
- `BudgetItem` — Descripción + valor numérico  
- `Status` — Estado general del proyecto  
- `Approvals` — Registro de aprobaciones  
- `Approver` — Nombre + rol  
- `Ident` — Identificadores  
- `Number` — Números enteros  
- `_` — espacios en blanco opcionales

---

# ■ 3. Gramática del DSL (Ohm-JS)

La gramática del lenguaje define cómo se combinan los terminales y no terminales para formar estructuras válidas.

Ejemplo abreviado de la producción principal:

Project =
_ "project" _ name:Ident _ "{" _
Metadata
Client
Context
Objectives
Scope
Schedule
Team
Risks
Budget
Status
Approvals
"}" _

java
Copiar código

Producción interna (ejemplo):

Metadata =
"metadata" _ "{" _
"code" _ code:Ident _
"version" _ version:Ident _
"created_on" _ createdOn:Ident _
"prepared_by" _ preparedBy:Ident _
"}" _

markdown
Copiar código

---

# ■ 4. Análisis Léxico — JFlex (Java)

El lexer identifica:

- palabras clave (terminales estructurales)  
- identificadores (`Ident`)  
- números (`Number`)  
- manejo de espacios y saltos de línea  
- validación de caracteres inesperados  

Incluye soporte para:
- palabras reservadas  
- reconocimiento de secuencias tipo `HE_Arquitectos`  
- números enteros para costos  
- errores léxicos detallados

---

# ■ 5. Parser — CUP (Java)

El parser define:

- reglas gramaticales  
- precedencia  
- construcción del AST  
- validación de estructura global  
- manejo de listas (objetivos, fases, roles…)  

Ejemplo conceptual (NO código literal):

- `Project` produce un nodo raíz con todas las secciones del acta.  
- Cada bloque (`metadata`, `client`, etc.) se convierte en un nodo especializado.  
- Las listas (objetivos, fases, ítems del presupuesto) se construyen como colecciones.

---

# ■ 6. AST — Árbol Sintáctico

Cada sección del DSL se convierte en un **nodo del AST**, permitiendo:

- representación interna uniforme  
- validación sintáctica  
- generación final del HTML

Ejemplo de nodos del AST:  

- `ProjectNode`  
- `MetadataNode`  
- `ClientNode`  
- `ObjectivesNode`  
- `ScopeNode`  
- `PhaseNode`  
- `RoleNode`  
- `RiskNode`  
- `BudgetItemNode`  
- etc.

Ejemplo visual del AST:

![AST](https://github.com/user-attachments/assets/bcd82f32-45d4-4145-9aa7-ac120028b087)

---

# ■ 7. Generación de HTML — Transpiladores

### ✔ Implementación en Java
Genera un HTML formal ubicado en:

Java-Implementation/output/ActaProyecto.html



Incluye:

- Tablas  
- Encabezados  
- Secciones formales  
- Codificación UTF-8 con tildes correctamente

---

### ✔ Implementación en Ohm-JS
Genera un HTML ubicado en:

Ohm-Implementation/output/acta_proyecto_ohm.html



Incluye:

- Código DSL coloreado  
- Render minimalista  
- Preparado para estilización futura

---

# ■ 8. Ejecución del proyecto

### ✔ Ohm-JS
cd Ohm-Implementation
node src/main.js ../Java-Implementation/src/proyecto1.proj

shell
Copiar código

### ✔ Java (JFlex + CUP)
cd Java-Implementation
javac -d bin src/*.java
java -cp bin Main ../src/proyecto1.proj



---

# ■ 9. Ejemplo real del DSL utilizado

![DSL1](https://github.com/user-attachments/assets/6db46864-ff03-40b0-acd8-2e9f0be5e818)
![DSL2](https://github.com/user-attachments/assets/96c9b437-b4ed-41d9-b3ce-2cd9d9eb2394)

---

# ■ 10. IDE web / Compilador interactivo (Python + Streamlit + Java)

Además de las dos implementaciones “clásicas” (Java y Ohm-JS), el proyecto incluye un IDE web para ProManDSL desarrollado en Python + Streamlit, que funciona como una capa gráfica sobre el compilador de Java.

Este IDE permite:

Escribir o editar el acta en ProManDSL desde el navegador.

Cargar archivos .proj desde el equipo.

Enviar el código al compilador Java (JFlex + CUP).

Visualizar el HTML generado embebido.

Descargar el resultado como archivo .html.

Ver mensajes de error cuando el DSL tiene problemas sintácticos o léxicos.

<img width="1366" height="596" alt="image" src="https://github.com/user-attachments/assets/2db02142-7f7a-4859-8c53-438502dc83bd" />

<img width="1355" height="598" alt="image" src="https://github.com/user-attachments/assets/74347c35-5a4f-4b8c-96bf-d6189399862c" />

---

# ■ 11. Créditos del proyecto

Proyecto desarrollado por:  
**Natalia Rubio**  
Compiladores – Universidad El Bosque
2025  
