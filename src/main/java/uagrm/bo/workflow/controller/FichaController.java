package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.FichaDTO;
import uagrm.bo.workflow.exceptions.ValidacionException;
import uagrm.bo.workflow.model.Ficha;
import uagrm.bo.workflow.repository.IntervaloHorarioRepository;
import uagrm.bo.workflow.service.FichaService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @Autowired
    private IntervaloHorarioRepository intervaloHorarioRepository;

    @GetMapping
    public ResponseEntity<List<Ficha>> listarFichas() {
        return new  ResponseEntity<>(fichaService.listarFichas(), HttpStatus.OK);
    }


    @PostMapping("/agendar")
    public ResponseEntity<?> agendarFicha(@RequestBody FichaDTO request) {

        try {
            fichaService.agendarFicha(request.getPacienteId(), request.getIntervaloId(), request.getFecha());
            return ResponseEntity.ok("Ficha agendada correctamente");
        } catch ( ValidacionException e ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/agendarFicha")
    public ResponseEntity<?> agendar(@RequestBody FichaDTO request) {
        try {
            fichaService.asignar(request.getPacienteId(), request.getEspecialidadId(), request.getMedicoId(),
                request.getHorarioId(), request.getIntervaloId(), request.getFecha());
            return ResponseEntity.ok("Ficha agendada correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
