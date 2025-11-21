package ast;

public class ApproverNode {

    private String role;
    private String name;

    public ApproverNode(String role, String name) {
        this.role = role;
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
