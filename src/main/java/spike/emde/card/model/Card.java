package spike.emde.card.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/*
  @Data
    needs the lombok plugin and enable the annotation processor.
 */
@Log4j
@Data
public class Card {
    private String id;
    @NotNull
    private String brief;
    @NotNull
    private String content;

    private List<String> assignedTo;

    private String priority;

    private Date dueDate;

    private double estimateHours;

    private  String classOfServices;

    private String size;
}
