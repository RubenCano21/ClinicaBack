package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.exceptions.HorarioDuplicadoException;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.service.HorarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Horario>> listar() {
        return new ResponseEntity<>(horarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Horario>> obtenerHorarioPorMedico(@PathVariable Long medicoId) {
        List<Horario> horarios = horarioService.obtenerHorariosPorMedico(medicoId);
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarHorario(@RequestBody Horario horario) {

        try {
            Horario nuevoHorario = horarioService.crearHorario(horario);
            return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
        } catch (HorarioDuplicadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Horario> eliminarHorario(@PathVariable Long id) {
        horarioService.eliminarHorario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
