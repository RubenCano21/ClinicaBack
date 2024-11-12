package uagrm.bo.workflow.dto;

import lombok.Data;
import uagrm.bo.workflow.model.Enfermeria;

@Data
public class EnfermeriaDTO {


    private Long id;
    private Long fichaId;
    private Double peso;
    private Double altura;
    private Double temperatura;
    private Double presion;
    private Integer frecuenciaCardiaca;
    private String sintomasId;
    private String patologiasId;


    public EnfermeriaDTO(Enfermeria enfermeria) {
        this.id = enfermeria.getId();
        this.fichaId = enfermeria.getFicha().getId();
        this.peso = enfermeria.getPeso();
        this.altura = enfermeria.getAltura();
        this.temperatura = enfermeria.getTemperatura();
        this.presion = enfermeria.getPresion();
        this.frecuenciaCardiaca = enfermeria.getFrecuenciaCardiaca();
        this.sintomasId = enfermeria.getSintomas().toString();
        this.patologiasId = enfermeria.getPatologias().toString();
    }
}
