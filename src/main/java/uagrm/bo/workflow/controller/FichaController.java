package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.DatosFichas;
import uagrm.bo.workflow.dto.FichaDTO;
import uagrm.bo.workflow.exceptions.ValidacionException;
import uagrm.bo.workflow.model.Ficha;
import uagrm.bo.workflow.service.FichaService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/fichas")
public class FichaController {

    @Autowired
    private FichaService fichaService;


    @GetMapping("/listar")
    public ResponseEntity<List<FichaDTO>> listarFichas() {
        return new ResponseEntity<>(fichaService.listarFichas(), HttpStatus.OK);
    }


//    @PostMapping("/agendar")
//    public ResponseEntity<?> agendarFicha(@RequestBody FichaDTO request) {
//
//        try {
//            fichaService.agendarFicha(request.getPaciente().getId(), request.getMedicoHorario().getMedico().getId(), request.getFechaConsulta());
//            return ResponseEntity.ok("Ficha agendada correctamente");
//        } catch ( ValidacionException e ) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }

    @PostMapping("/agendarFicha")
    public ResponseEntity<?> agendar(@RequestBody FichaDTO request) {
        try {

            fichaService.asignar(request.getPaciente().getId(), request.getEspecialidad().getId(), request.getMedico().getId(),
                request.getMedicoHorario().getHorario().getId(), request.getIntervaloHorario().getId(), request.getFechaConsulta());
            return ResponseEntity.ok("Ficha agendada correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
