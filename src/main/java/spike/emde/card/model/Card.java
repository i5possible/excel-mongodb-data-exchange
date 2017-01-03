package spike.emde.card.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import spike.emde.utils.MyConstant;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/*
  @Data
    needs the lombok plugin and enable the annotation processor.
 */
@Log4j
@JsonInclude(value = NON_EMPTY)
public class Card {
    private String id;
    @NotNull
    private String brief;
    @NotNull
    private String content;

    private List<String> assignedTo;

//    private String priority;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dueDate;

//    private double estimateHours;
//
//    private String classOfServices;
//
//    private String size;

    // This member is to test the JsonInclude.
    //private String unused;


    public static Logger getLog() {
        return log;
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

    public String getAssignedToString() {
        return assignedTo == null ? MyConstant.empty : assignedTo.toArray().toString();
    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        return dueDate == null ? MyConstant.empty : new SimpleDateFormat("yyyy-mm-dd").format(dueDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

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
//    public String getSize() {
//        return size;
//    }
//
//    public void setSize(String size) {
//        this.size = size;
//    }

    public String[] toStringArray() {
        List<String> list = new ArrayList<>();
        list.add(id);
        list.add(brief);
        list.add(content);
        list.add(getAssignedToString());
//        list.add(priority);
        list.add(getDueDateString());
//        list.add("" + estimateHours);
//        list.add(classOfServices);
//        list.add(size);

        return list.toArray(new String[list.size()]);
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("brief", brief);
        map.put("content", content);
        map.put("assignedTo", getAssignedToString());
//        map.put("priority", priority);
        map.put("dueDate", getDueDateString());
//        map.put("estimateHours", "" + estimateHours);
//        map.put("classOfServices", classOfServices);
//        map.put("size", size);

        return map;
    }
}
