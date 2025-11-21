package ast;

import java.util.ArrayList;

public class ScheduleNode {

    private ArrayList<PhaseNode> phases;

    public ScheduleNode(ArrayList<PhaseNode> phases) {
        this.phases = phases;
    }

    public ArrayList<PhaseNode> getPhases() {
        return phases;
    }
}
