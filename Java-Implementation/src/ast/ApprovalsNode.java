package ast;

import java.util.ArrayList;

public class ApprovalsNode {

    private String requiredBy;
    private ArrayList<ApproverNode> approvers;

    public ApprovalsNode(String requiredBy, ArrayList<ApproverNode> approvers) {
        this.requiredBy = requiredBy;
        this.approvers = approvers;
    }

    public String getRequiredBy() {
        return requiredBy;
    }

    public ArrayList<ApproverNode> getApprovers() {
        return approvers;
    }
}
