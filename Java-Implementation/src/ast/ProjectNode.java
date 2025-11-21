package ast;

public class ProjectNode {

    private String name;
    private MetadataNode metadata;
    private ClientNode client;
    private ContextNode context;
    private ObjectivesNode objectives;
    private ScopeNode scope;
    private ScheduleNode schedule;
    private TeamNode team;
    private RisksNode risks;
    private BudgetNode budget;
    private StatusNode status;
    private ApprovalsNode approvals;

    public ProjectNode(
            String name,
            MetadataNode metadata,
            ClientNode client,
            ContextNode context,
            ObjectivesNode objectives,
            ScopeNode scope,
            ScheduleNode schedule,
            TeamNode team,
            RisksNode risks,
            BudgetNode budget,
            StatusNode status,
            ApprovalsNode approvals
    ) {
        this.name = name;
        this.metadata = metadata;
        this.client = client;
        this.context = context;
        this.objectives = objectives;
        this.scope = scope;
        this.schedule = schedule;
        this.team = team;
        this.risks = risks;
        this.budget = budget;
        this.status = status;
        this.approvals = approvals;
    }

    public String getName() {
        return name;
    }

    public MetadataNode getMetadata() {
        return metadata;
    }

    public ClientNode getClient() {
        return client;
    }

    public ContextNode getContext() {
        return context;
    }

    public ObjectivesNode getObjectives() {
        return objectives;
    }

    public ScopeNode getScope() {
        return scope;
    }

    public ScheduleNode getSchedule() {
        return schedule;
    }

    public TeamNode getTeam() {
        return team;
    }

    public RisksNode getRisks() {
        return risks;
    }

    public BudgetNode getBudget() {
        return budget;
    }

    public StatusNode getStatus() {
        return status;
    }

    public ApprovalsNode getApprovals() {
        return approvals;
    }
}
