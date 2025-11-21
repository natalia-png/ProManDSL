package transpiler;

import ast.*;
import java.util.List;

public class HtmlGenerator {

    public static String generate(ProjectNode project) {
        MetadataNode m  = project.getMetadata();
        ClientNode c    = project.getClient();
        ContextNode ctx = project.getContext();
        ObjectivesNode o = project.getObjectives();
        ScopeNode s     = project.getScope();
        ScheduleNode sch = project.getSchedule();
        TeamNode t      = project.getTeam();
        RisksNode r     = project.getRisks();
        BudgetNode b    = project.getBudget();
        StatusNode st   = project.getStatus();
        ApprovalsNode ap = project.getApprovals();

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"es\">\n");
        sb.append("<head>\n");
        sb.append("  <meta charset=\"UTF-8\">\n");
        sb.append("  <title>Acta de Proyecto - ").append(escape(project.getName())).append("</title>\n");
        sb.append("  <style>\n");
        sb.append("    * { box-sizing: border-box; }\n");
        sb.append("    body {\n");
        sb.append("      margin: 0;\n");
        sb.append("      padding: 0;\n");
        sb.append("      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;\n");
        sb.append("      background: #f3f4f6;\n");
        sb.append("      color: #111827;\n");
        sb.append("    }\n");
        sb.append("    .page {\n");
        sb.append("      max-width: 1000px;\n");
        sb.append("      margin: 40px auto;\n");
        sb.append("      background: #ffffff;\n");
        sb.append("      border-radius: 12px;\n");
        sb.append("      box-shadow: 0 18px 45px rgba(15,23,42,0.12);\n");
        sb.append("      padding: 32px 40px 40px 40px;\n");
        sb.append("    }\n");
        sb.append("    h1 {\n");
        sb.append("      font-size: 28px;\n");
        sb.append("      margin: 0 0 4px 0;\n");
        sb.append("    }\n");
        sb.append("    .subtitle {\n");
        sb.append("      font-size: 15px;\n");
        sb.append("      color: #6b7280;\n");
        sb.append("      margin-bottom: 24px;\n");
        sb.append("    }\n");
        sb.append("    .pill {\n");
        sb.append("      display: inline-block;\n");
        sb.append("      padding: 4px 10px;\n");
        sb.append("      font-size: 11px;\n");
        sb.append("      text-transform: uppercase;\n");
        sb.append("      letter-spacing: .08em;\n");
        sb.append("      border-radius: 999px;\n");
        sb.append("      background: #eef2ff;\n");
        sb.append("      color: #4f46e5;\n");
        sb.append("      margin-bottom: 8px;\n");
        sb.append("    }\n");
        sb.append("    h2 {\n");
        sb.append("      font-size: 18px;\n");
        sb.append("      margin: 28px 0 10px 0;\n");
        sb.append("      border-left: 4px solid #4f46e5;\n");
        sb.append("      padding-left: 8px;\n");
        sb.append("    }\n");
        sb.append("    table {\n");
        sb.append("      width: 100%;\n");
        sb.append("      border-collapse: collapse;\n");
        sb.append("      font-size: 14px;\n");
        sb.append("      margin-bottom: 12px;\n");
        sb.append("    }\n");
        sb.append("    th, td {\n");
        sb.append("      padding: 8px 10px;\n");
        sb.append("      border-bottom: 1px solid #e5e7eb;\n");
        sb.append("      vertical-align: top;\n");
        sb.append("    }\n");
        sb.append("    th {\n");
        sb.append("      width: 180px;\n");
        sb.append("      background: #f9fafb;\n");
        sb.append("      text-align: left;\n");
        sb.append("      font-weight: 600;\n");
        sb.append("      color: #374151;\n");
        sb.append("    }\n");
        sb.append("    td {\n");
        sb.append("      color: #111827;\n");
        sb.append("    }\n");
        sb.append("    .badge {\n");
        sb.append("      display: inline-flex;\n");
        sb.append("      align-items: center;\n");
        sb.append("      padding: 2px 8px;\n");
        sb.append("      border-radius: 999px;\n");
        sb.append("      font-size: 11px;\n");
        sb.append("      font-weight: 500;\n");
        sb.append("    }\n");
        sb.append("    .badge-high { background:#fee2e2; color:#b91c1c; }\n");
        sb.append("    .badge-medium { background:#fef3c7; color:#92400e; }\n");
        sb.append("    .badge-low { background:#dcfce7; color:#166534; }\n");
        sb.append("    ul { margin: 0 0 6px 18px; padding: 0; }\n");
        sb.append("    li { margin-bottom: 3px; }\n");
        sb.append("    .status-chip {\n");
        sb.append("      display:inline-block;\n");
        sb.append("      padding:4px 10px;\n");
        sb.append("      border-radius:999px;\n");
        sb.append("      background:#e0f2fe;\n");
        sb.append("      color:#075985;\n");
        sb.append("      font-size:12px;\n");
        sb.append("    }\n");
        sb.append("    footer {\n");
        sb.append("      margin-top: 32px;\n");
        sb.append("      font-size: 12px;\n");
        sb.append("      color: #9ca3af;\n");
        sb.append("      text-align: right;\n");
        sb.append("    }\n");
        sb.append("  </style>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<div class=\"page\">\n");

        sb.append("  <div class=\"pill\">Acta de Proyecto</div>\n");
        sb.append("  <h1>").append(escape(project.getName())).append("</h1>\n");
        sb.append("  <div class=\"subtitle\">Documento generado automáticamente desde ProManDSL.</div>\n");

        // 1. Datos generales
        sb.append("  <h2>1. Datos generales</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Código</th><td>").append(escape(m.getCode())).append("</td></tr>\n");
        sb.append("    <tr><th>Versión</th><td>").append(escape(m.getVersion())).append("</td></tr>\n");
        sb.append("    <tr><th>Fecha de creación</th><td>").append(escape(m.getCreatedOn())).append("</td></tr>\n");
        sb.append("    <tr><th>Elaboró</th><td>").append(escape(m.getPreparedBy())).append("</td></tr>\n");
        sb.append("  </table>\n");

        // 2. Cliente
        sb.append("  <h2>2. Cliente</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Nombre</th><td>").append(escape(c.getName())).append("</td></tr>\n");
        sb.append("    <tr><th>Contacto</th><td>").append(escape(c.getContact())).append("</td></tr>\n");
        sb.append("    <tr><th>Correo</th><td>").append(escape(c.getEmail())).append("</td></tr>\n");
        sb.append("  </table>\n");

        // 3. Contexto
        sb.append("  <h2>3. Contexto del proyecto</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Descripción</th><td>").append(escape(ctx.getSummary())).append("</td></tr>\n");
        sb.append("  </table>\n");

        // 4. Objetivos
        sb.append("  <h2>4. Objetivos</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Objetivo general</th><td>").append(escape(o.getGeneralObjective())).append("</td></tr>\n");
        sb.append("    <tr><th>Objetivos específicos</th><td>\n");
        appendStringList(sb, o.getSpecificObjectives());
        sb.append("    </td></tr>\n");
        sb.append("  </table>\n");

        // 5. Alcance
        sb.append("  <h2>5. Alcance</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Incluye</th><td>\n");
        appendStringList(sb, s.getIncludes());
        sb.append("    </td></tr>\n");
        sb.append("    <tr><th>No incluye</th><td>\n");
        appendStringList(sb, s.getExcludes());
        sb.append("    </td></tr>\n");
        sb.append("  </table>\n");

        // 6. Cronograma
        sb.append("  <h2>6. Cronograma</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Fase</th><th>Inicio</th><th>Fin</th></tr>\n");
        for (PhaseNode p : sch.getPhases()) {
            sb.append("    <tr>")
              .append("<td>").append(escape(p.getName())).append("</td>")
              .append("<td>").append(escape(p.getStart())).append("</td>")
              .append("<td>").append(escape(p.getEnd())).append("</td>")
              .append("</tr>\n");
        }
        sb.append("  </table>\n");

        // 7. Equipo
        sb.append("  <h2>7. Equipo del proyecto</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Rol</th><th>Responsable</th></tr>\n");
        for (RoleNode role : t.getRoles()) {
            sb.append("    <tr>")
              .append("<td>").append(escape(role.getRole())).append("</td>")
              .append("<td>").append(escape(role.getPerson())).append("</td>")
              .append("</tr>\n");
        }
        sb.append("  </table>\n");

        // 8. Riesgos
        sb.append("  <h2>8. Riesgos</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Descripción</th><th>Nivel</th><th>Mitigación</th></tr>\n");
        for (RiskNode rn : r.getRisks()) {
            sb.append("    <tr>")
              .append("<td>").append(escape(rn.getDescription())).append("</td>")
              .append("<td>").append(renderRiskLevel(rn.getLevel())).append("</td>")
              .append("<td>").append(escape(rn.getMitigation())).append("</td>")
              .append("</tr>\n");
        }
        sb.append("  </table>\n");

        // 9. Presupuesto
        sb.append("  <h2>9. Presupuesto</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Moneda</th><td>")
          .append(escape(b.getCurrency()))
          .append("</td></tr>\n");
        sb.append("  </table>\n");

        sb.append("  <table>\n");
        sb.append("    <tr><th>Item</th><th>Valor</th></tr>\n");
        for (BudgetItemNode bi : b.getItems()) {
            sb.append("    <tr>")
              .append("<td>").append(escape(bi.getDescription())).append("</td>")
              .append("<td>").append(bi.getCost()).append("</td>")
              .append("</tr>\n");
        }
        sb.append("  </table>\n");

        // 10. Estado
        sb.append("  <h2>10. Estado del proyecto</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Estado general</th><td><span class=\"status-chip\">")
          .append(escape(st.getOverall()))
          .append("</span></td></tr>\n");
        sb.append("    <tr><th>Salud</th><td>")
          .append(escape(st.getHealth()))
          .append("</td></tr>\n");
        sb.append("    <tr><th>Comentarios</th><td>")
          .append(escape(st.getComments()))
          .append("</td></tr>\n");
        sb.append("  </table>\n");

        // 11. Aprobaciones
        sb.append("  <h2>11. Aprobaciones</h2>\n");
        sb.append("  <table>\n");
        sb.append("    <tr><th>Se requiere aprobación antes de</th><td>")
          .append(escape(ap.getRequiredBy()))
          .append("</td></tr>\n");
        sb.append("  </table>\n");

        sb.append("  <table>\n");
        sb.append("    <tr><th>Nombre</th><th>Cargo</th></tr>\n");
        for (ApproverNode an : ap.getApprovers()) {
            sb.append("    <tr>")
              .append("<td>").append(escape(an.getName())).append("</td>")
              .append("<td>").append(escape(an.getRole())).append("</td>")
              .append("</tr>\n");
        }
        sb.append("  </table>\n");

        sb.append("  <footer>Generado automáticamente por ProManDSL.</footer>\n");
        sb.append("</div>\n");
        sb.append("</body>\n</html>\n");

        return sb.toString();
    }

    private static void appendStringList(StringBuilder sb, List<String> items) {
        if (items == null || items.isEmpty()) {
            sb.append("<em>Sin elementos registrados</em>");
            return;
        }
        sb.append("<ul>\n");
        for (String s : items) {
            sb.append("  <li>").append(escape(s)).append("</li>\n");
        }
        sb.append("</ul>\n");
    }

    private static String renderRiskLevel(String level) {
        if (level == null) return "";
        String l = level.toLowerCase();
        if (l.equals("high")) {
            return "<span class=\"badge badge-high\">Alto</span>";
        } else if (l.equals("medium")) {
            return "<span class=\"badge badge-medium\">Medio</span>";
        } else if (l.equals("low")) {
            return "<span class=\"badge badge-low\">Bajo</span>";
        }
        return escape(level);
    }

    private static String escape(String text) {
        if (text == null) return "";
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
