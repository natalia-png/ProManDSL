package ast;

public class RiskNode {

    private String description;
    private String level;
    private String mitigation;

    public RiskNode(String description, String level, String mitigation) {
        this.description = description;
        this.level = level;
        this.mitigation = mitigation;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public String getMitigation() {
        return mitigation;
    }
}
