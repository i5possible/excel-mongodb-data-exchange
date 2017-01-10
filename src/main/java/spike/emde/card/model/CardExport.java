package spike.emde.card.model;

import spike.emde.model.Exportable;
import spike.emde.model.ToExportClass;
import spike.emde.model.ToExportField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToExportClass(exportName = "cards")
public class CardExport implements Exportable {

    @ToExportField(exportName = "id", exportIndex = 1)
    private String id;
    @ToExportField(exportName = "brief", exportIndex = 2)
    private String brief;
    @ToExportField(exportName = "content", exportIndex = 3)
    private String content;
    @ToExportField(exportName = "assigned_to", exportIndex = 4)
    private List<String> assignedTo;
    @ToExportField(exportName = "due_date", exportIndex = 5)
    private LocalDate dueDate;
    @ToExportField(exportName = "size", exportIndex = 6)
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

    public String getAssignedTo() {
        return assignedTo.stream().collect(Collectors.joining(", "));
    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDueDate() {
        return dueDate.toString();
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
        arrayList.add(getId());
        arrayList.add(getBrief());
        arrayList.add(getContent());
        arrayList.add(getAssignedTo());
        arrayList.add(getDueDate());
        arrayList.add(getSize());
        return arrayList;
    }
}
