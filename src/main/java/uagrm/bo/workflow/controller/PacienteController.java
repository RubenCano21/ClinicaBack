package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.exceptions.PacientesNotFoundException;
import uagrm.bo.workflow.model.Paciente;
import uagrm.bo.workflow.service.PacienteService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listar() {
        return new ResponseEntity<>(pacienteService.listar(), HttpStatus.OK);
    }

    // metodo para buscar un paciente por el id
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.findPacienteById(id)
                .orElseThrow(() -> new PacientesNotFoundException(id));

        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearPaciente(@Valid @RequestBody Paciente paciente, BindingResult result) {
        if (result.hasFieldErrors()) {
            return  validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardar(paciente));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Paciente paciente, BindingResult result) {
        return crearPaciente(paciente, result);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPaciente(@Valid @RequestBody Paciente paciente, @PathVariable Long id) {
        Paciente pacienteExistente = pacienteService.buscar(id);
        if (pacienteExistente == null) {
            return ResponseEntity.notFound().build(); // Devuelve un 404 si el pacioente no existe
        }
        paciente.setId(id); // aseguramos que se actualice el paciente correcto
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.actualizar(paciente));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscar(id);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }else {
            pacienteService.eliminar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
