package ast;

import java.util.ArrayList;

public class BudgetNode {

    private String currency;
    private ArrayList<BudgetItemNode> items;

    public BudgetNode(String currency, ArrayList<BudgetItemNode> items) {
        this.currency = currency;
        this.items = items;
    }

    public String getCurrency() {
        return currency;
    }

    public ArrayList<BudgetItemNode> getItems() {
        return items;
    }
}
