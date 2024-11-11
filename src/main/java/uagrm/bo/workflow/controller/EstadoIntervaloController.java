package uagrm.bo.workflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uagrm.bo.workflow.model.EstadoIntervalo;

@RestController
public class EstadoIntervaloController {

    @GetMapping("/api/estado-intervalo")
    public EstadoIntervalo[] getEstadosIntervalo() {
        return EstadoIntervalo.values();
    }
}
