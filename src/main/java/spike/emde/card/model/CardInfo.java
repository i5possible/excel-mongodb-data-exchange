package spike.emde.card.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.mongodb.core.mapping.Document;
import spike.emde.utils.MyConstant;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/*
  @Data
    needs the lombok plugin and enable the annotation processor.
 */
@Document(collection = "card")
@JsonInclude(value = NON_EMPTY)
public class CardInfo {
    private String id;
    @NotNull
    private String brief;
    @NotNull
    private String content;

    private List<String> assignedTo;

    //    private String priority;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dueDate;

//    private double estimateHours;
//
//    private String classOfServices;

    private String size;

    // This member is to test the JsonInclude.
    //private String unused;

    public CardInfo() {

    }

    public CardInfo(Map<String, String> cardMap) throws ParseException {
        this.id = cardMap.get("id");
        this.brief = cardMap.get("brief");
        this.content = cardMap.get("content");
        this.assignedTo = Arrays.asList(cardMap.get("assignedTo"));
//        this.dueDate = LocalDate.parse(cardMap.get("dueDate"));
        this.size = cardMap.get("size");
    }

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

//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedToString() {
        return assignedTo == null ? MyConstant.empty :
                String.join(", ", assignedTo.toArray(new String[assignedTo.size()]));
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
//
//    public double getEstimateHours() {
//        return estimateHours;
//    }
//
//    public void setEstimateHours(double estimateHours) {
//        this.estimateHours = estimateHours;
//    }
//
//    public String getClassOfServices() {
//        return classOfServices;
//    }
//
//    public void setClassOfServices(String classOfServices) {
//        this.classOfServices = classOfServices;
//    }
//

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDateString() {
        return dueDate == null ? MyConstant.empty : dueDate.toString();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String[] toStringArray() {
        List<String> list = new ArrayList<>();
        list.add(id);
        list.add(brief);
        list.add(content);
        list.add(getAssignedToString());
//        list.add(priority);
//        list.add(getDueDateString());
//        list.add("" + estimateHours);
//        list.add(classOfServices);
        list.add(size);

        return list.toArray(new String[list.size()]);
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("brief", brief);
        map.put("content", content);
        map.put("assignedTo", getAssignedToString());
//        map.put("priority", priority);
//        map.put("dueDate", getDueDateString());
//        map.put("estimateHours", "" + estimateHours);
//        map.put("classOfServices", classOfServices);
        map.put("size", size);

        return map;
    }
}
