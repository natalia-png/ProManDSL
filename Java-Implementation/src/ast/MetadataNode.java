package ast;

public class MetadataNode {

    private String code;
    private String version;
    private String createdOn;
    private String preparedBy;

    public MetadataNode(String code, String version, String createdOn, String preparedBy) {
        this.code = code;
        this.version = version;
        this.createdOn = createdOn;
        this.preparedBy = preparedBy;
    }

    public String getCode() {
        return code;
    }

    public String getVersion() {
        return version;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getPreparedBy() {
        return preparedBy;
    }
}
