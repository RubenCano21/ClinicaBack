package uagrm.bo.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "enfermeria", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ficha_id"})
})
public class Enfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ficha_id")
    private Ficha ficha;

    private Double peso;
    private Double altura;
    private Double temperatura;
    private Double presion;
    private Integer frecuenciaCardiaca;

    @ElementCollection
    private List<Sintoma> sintomas;

    @ElementCollection
    private List<Patologia> patologias;

}
