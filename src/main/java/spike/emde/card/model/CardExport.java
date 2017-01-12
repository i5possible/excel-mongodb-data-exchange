package spike.emde.card.model;

import spike.emde.model.AbstractExportable;
import spike.emde.model.Column;
import spike.emde.model.Title;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Title(name = "cards")
public class CardExport extends AbstractExportable {

    @Column(name = "id", index = 1)
    private String id;
    @Column(name = "brief", index = 2)
    private String brief;
    @Column(name = "content", index = 3)
    private String content;
    @Column(name = "assigned_to", index = 4)
    private List<String> assignedTo;
    @Column(name = "due_date", index = 5)
    private LocalDate dueDate;
    @Column(name = "size", index = 6)
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

/*    @Override
    public List<String> toList() {
        List<String> arrayList = new ArrayList();
        arrayList.add(getId());
        arrayList.add(getBrief());
        arrayList.add(getContent());
        arrayList.add(getAssignedTo());
        arrayList.add(getDueDate());
        arrayList.add(getSize());
        return arrayList;
    }*/
}
