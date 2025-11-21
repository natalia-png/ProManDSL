package ast;

import java.util.ArrayList;

public class ScopeNode {

    private ArrayList<String> includes;
    private ArrayList<String> excludes;

    public ScopeNode(ArrayList<String> includes, ArrayList<String> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    public ArrayList<String> getIncludes() {
        return includes;
    }

    public ArrayList<String> getExcludes() {
        return excludes;
    }
}
