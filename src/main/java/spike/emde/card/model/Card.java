package spike.emde.card.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.validation.constraints.NotNull;

/*
  @Data
    needs the lombok plugin and enable the annotation processor.
 */
@Log4j
@Data
public class Card {
    private String id;
    @NotNull
    private String description;
    @NotNull
    private String content;
}
