package spike.emde.card.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/*
  @Data
    needs the lombok plugin and enable the annotation processor.
 */
@Log4j
@Data
@JsonInclude(value = NON_EMPTY)
public class Card {
    private String id;
    @NotNull
    private String brief;
    @NotNull
    private String content;

    private List<String> assignedTo;

    private String priority;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dueDate;

    private double estimateHours;

    private  String classOfServices;

    private String size;

    private String unused;
}
