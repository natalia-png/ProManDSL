package ast;

import java.util.ArrayList;

public class RisksNode {

    private ArrayList<RiskNode> risks;

    public RisksNode(ArrayList<RiskNode> risks) {
        this.risks = risks;
    }

    public ArrayList<RiskNode> getRisks() {
        return risks;
    }
}
