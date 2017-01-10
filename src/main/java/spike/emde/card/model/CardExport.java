package spike.emde.card.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardExport implements Exportable {
    private static final List<String> SCHEMA = Arrays.asList(
            "id", "brief", "content", "assignedTo", "dueDate", "size"
    );

    @ToExportField
    private String id;
    @ToExportField
    private String brief;
    @ToExportField
    private String content;
    @ToExportField
    private List<String> assignedTo;
    @ToExportField
    private LocalDate dueDate;
    @ToExportField
    private String size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public List<String> toList() {
        List<String> arrayList = new ArrayList();
        arrayList.add(id);
        arrayList.add(brief);
        arrayList.add(content);
        arrayList.add(assignedTo.stream().collect(Collectors.joining(", ")));
        arrayList.add(dueDate.toString());
        arrayList.add(size);
        return arrayList;
    }

    public static List<String> getSchema() {
        return SCHEMA;
    }
}
