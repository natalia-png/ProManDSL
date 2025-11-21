package ast;

public class PhaseNode {

    private String name;
    private String startDate;
    private String endDate;

    public PhaseNode(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters originales
    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // ✅ Getters nuevos para HtmlGenerator
    public String getStart() {
        return startDate;
    }

    public String getEnd() {
        return endDate;
    }
}
