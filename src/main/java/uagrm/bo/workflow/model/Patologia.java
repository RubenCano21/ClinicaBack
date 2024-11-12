package uagrm.bo.workflow.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Patologia {

    private String nombre;
    private Boolean diabetes;
}
