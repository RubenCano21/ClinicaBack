package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.EspecialidadDTO;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.service.EspecialidadService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;


    @GetMapping("/listar")
    public ResponseEntity<List<EspecialidadDTO>> listar() {
        return new ResponseEntity<>(especialidadService.listar(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> crearEspecialidad(@Valid @RequestBody Especialidad especialidad, BindingResult result) {
        if (result.hasFieldErrors()) {
            return  validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(especialidadService.guardar(especialidad));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Especialidad especialidad, BindingResult result) {
        return crearEspecialidad(especialidad, result);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEspecialidad(@Valid @RequestBody Especialidad especialidad, @PathVariable Long id) {
        Especialidad especialidadExistente = especialidadService.buscar(id);
        if (especialidadExistente == null) {
            return ResponseEntity.notFound().build(); // Devuelve un 404 si la especialidad no existe
        }
        especialidad.setId(id); // aseguramos que se actualice la especialidad correcta
        return ResponseEntity.status(HttpStatus.OK).body(especialidadService.actualizar(especialidad));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEspecialidad(@PathVariable Long id) {
        Especialidad especialidad = especialidadService.buscar(id);
        if (especialidad == null) {
            return ResponseEntity.notFound().build();
        }else {
            especialidadService.eliminar(id);
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
