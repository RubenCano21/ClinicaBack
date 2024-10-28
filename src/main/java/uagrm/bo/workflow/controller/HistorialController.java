package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.HistorialDTO;
import uagrm.bo.workflow.model.Historial;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.Paciente;
import uagrm.bo.workflow.model.User;
import uagrm.bo.workflow.service.HistorialService;

import java.util.List;

@RestController
@RequestMapping("/api/historiales")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    //endpoints aqui

    @GetMapping
    public ResponseEntity<List<Historial>> obtenerTodosLosHistoriales() {
        return ResponseEntity.ok(historialService.obtenerTodosLosHistoriales());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Historial> crearHistorial(@RequestBody HistorialDTO historial) {

        return new ResponseEntity<>(historialService.crearHistorial(historial), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> obtenerHistorialPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historialService.obtenerHistorialPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Historial>> obtenerHistorialesPorPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(historialService.obtenerHistorialesPorPacienteId(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Historial>> obtenerHistorialesPorMedicoId(@PathVariable Long medicoId) {
        return ResponseEntity.ok(historialService.obtenerHistorialesPorMedicoId(medicoId));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Historial> actualizarHistorial(@PathVariable Long id, @RequestBody Historial historial) {
        return ResponseEntity.ok(historialService.actualizarHistorial(id, historial));
    }

    @PutMapping("/medico/{historialId}")
    public ResponseEntity<Historial> actualizarHistorialPorMedico(@PathVariable Long historialId, @RequestParam String diagnostico) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long medicoId = ((User) authentication.getPrincipal()).getId();
        return ResponseEntity.ok(historialService.actualizarHistorialPorMedico(historialId, diagnostico, medicoId));
    }
}
