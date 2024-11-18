package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.DatosReservaFicha;
import uagrm.bo.workflow.dto.FichaDTO;
import uagrm.bo.workflow.exceptions.ValidacionException;
import uagrm.bo.workflow.service.FichaService;

import java.util.List;
import java.util.stream.Collectors;


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


    @PostMapping("/agendarFicha")
    public ResponseEntity<?> agendar(@RequestBody @Valid DatosReservaFicha request, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores de validación, devolver los errores al cliente
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            fichaService.asignar(request.getPacienteId(), request.getEspecialidadId(), request.getMedicoId(),
                    request.getMedicoHorarioId(), request.getIntervaloHorarioId(), request.getFechaConsulta());
            return ResponseEntity.ok("Ficha agendada correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
