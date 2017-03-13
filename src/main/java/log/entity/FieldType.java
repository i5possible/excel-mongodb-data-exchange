package log.entity;

public enum FieldType {
    TEXT("text"), NUMBER("number"), DATE("date"), USER("user"), BOOLEAN("boolean"), LIST("list");
    private String name;

    FieldType(String name) {
        this.name = name;
    }

    public static FieldType fromString(String type) {
        if (type != null) {
            for (FieldType fieldType : FieldType.values()) {
                if (type.equalsIgnoreCase(fieldType.name)) {
                    return fieldType;
                }
            }
        }
        return null;
    }

    public String getName() {return name;}

    public void setName() {this.name = name;}
}
