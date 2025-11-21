package ast;

public class RoleNode {

    private String roleName;
    private String personName;

    public RoleNode(String roleName, String personName) {
        this.roleName = roleName;
        this.personName = personName;
    }

    // Getters originales
    public String getRoleName() {
        return roleName;
    }

    public String getPersonName() {
        return personName;
    }

    // ✅ Getters nuevos para HtmlGenerator
    public String getRole() {
        return roleName;
    }

    public String getPerson() {
        return personName;
    }
}
