package ast;

public class BudgetItemNode {

    private String description;
    private int amount;

    public BudgetItemNode(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters originales
    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    // ✅ Getter nuevo para HtmlGenerator
    public int getCost() {
        return amount;
    }
}
