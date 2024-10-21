package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.model.Ficha;
import uagrm.bo.workflow.service.FichaService;

import java.util.List;

@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @GetMapping
    public ResponseEntity<List<Ficha>> listarFichas() {
        return new  ResponseEntity<>(fichaService.listarFichas(), HttpStatus.OK);
    }

    @PostMapping("/asignar")
    public ResponseEntity<Ficha> asignarFicha(@RequestParam Long pacienteId,
                                              @RequestParam Long especialidadId,
                                              @RequestParam Long medicoId,
                                              @RequestParam Long horarioId) {
        Ficha ficha = fichaService.asignarFicha(pacienteId, especialidadId, medicoId, horarioId);
        return new ResponseEntity<>(ficha, HttpStatus.CREATED);
    }
}
