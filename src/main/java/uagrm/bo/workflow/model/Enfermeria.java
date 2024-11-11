package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enfermeria")
public class Enfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ficha_id")
    private Ficha ficha;

    private Double peso;
    private Double estatura;
    private Double presion;
}
