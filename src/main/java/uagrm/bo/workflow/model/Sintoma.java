package uagrm.bo.workflow.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Sintoma {

    private String nombre;
    private Boolean presente;
}
