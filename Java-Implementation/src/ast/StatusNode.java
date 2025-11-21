package ast;

public class StatusNode {

    private String overall;
    private String health;
    private String comments;

    public StatusNode(String overall, String health, String comments) {
        this.overall = overall;
        this.health = health;
        this.comments = comments;
    }

    public String getOverall() {
        return overall;
    }

    public String getHealth() {
        return health;
    }

    public String getComments() {
        return comments;
    }
}
