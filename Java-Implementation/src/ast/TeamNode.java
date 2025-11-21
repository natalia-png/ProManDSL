package ast;

import java.util.ArrayList;

public class TeamNode {

    private ArrayList<RoleNode> roles;

    public TeamNode(ArrayList<RoleNode> roles) {
        this.roles = roles;
    }

    public ArrayList<RoleNode> getRoles() {
        return roles;
    }
}
