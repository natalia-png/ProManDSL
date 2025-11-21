// src/htmlGenerator.js

export function generateHtmlFromProject(project) {
  const { name, body } = project;

  return `
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Acta (Ohm) - ${escapeHtml(name)}</title>
  <style>
    * { box-sizing: border-box; }
    body {
      margin: 0;
      padding: 0;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
      background: #f3f4f6;
      color: #111827;
    }
    .page {
      max-width: 1000px;
      margin: 40px auto;
      background: #ffffff;
      border-radius: 12px;
      box-shadow: 0 18px 45px rgba(15,23,42,0.12);
      padding: 32px 40px 40px 40px;
    }
    .pill {
      display: inline-block;
      padding: 4px 10px;
      font-size: 11px;
      text-transform: uppercase;
      letter-spacing: .08em;
      border-radius: 999px;
      background: #eef2ff;
      color: #4f46e5;
      margin-bottom: 8px;
    }
    h1 {
      font-size: 26px;
      margin: 0 0 4px 0;
    }
    .subtitle {
      font-size: 14px;
      color: #6b7280;
      margin-bottom: 20px;
    }
    .code-block {
      margin-top: 20px;
      background: #111827;
      color: #e5e7eb;
      border-radius: 10px;
      padding: 16px 18px;
      font-family: "JetBrains Mono", ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
      font-size: 13px;
      overflow-x: auto;
      line-height: 1.4;
      white-space: pre;
    }
    footer {
      margin-top: 28px;
      font-size: 12px;
      color: #9ca3af;
      text-align: right;
    }
  </style>
</head>
<body>
  <div class="page">
    <div class="pill">Acta (implementación Ohm)</div>
    <h1>${escapeHtml(name)}</h1>
    <div class="subtitle">
      Documento generado automáticamente desde el DSL usando <strong>ohm-js</strong>.
      En esta vista se muestra el contenido original del acta.
    </div>

    <h2 style="font-size:16px;margin-top:18px;">Código DSL</h2>
    <div class="code-block">${escapeHtml(body)}</div>

    <footer>Implementación de referencia con Ohm-JS para la rúbrica del proyecto.</footer>
  </div>
</body>
</html>
`;
}

function escapeHtml(text) {
  if (!text) return "";
  return text
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;");
}
