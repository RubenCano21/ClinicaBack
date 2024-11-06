package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.service.MedicoService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;


    @GetMapping("/listar")
    public List<Medico> listar() {
        return medicoService.listar();
    }

//    @GetMapping("/especialidad({especialidadId}")
//    public ResponseEntity<List<Medico>> especialidad(@PathVariable Long especialidadId) {
//        return new ResponseEntity<>(medicoService.findMedicoByEspecialidad(especialidadId), HttpStatus.OK);
//    }

    @GetMapping({"/{id}/especialidades"})
    public ResponseEntity<?> obtenerEspecialidadesPorMedico(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.obtenerEspecialidadesPorMedico(id));
    }

    @PostMapping
    public ResponseEntity<?> crearMedico(@Valid @RequestBody Medico medico, BindingResult result) {
        if (result.hasFieldErrors()) {
            return  validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.guardar(medico));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Medico medico, BindingResult result) {
        return crearMedico(medico, result);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarMedico(@Valid @RequestBody Medico medico, @PathVariable Long id) {
        Medico medicoExistente = medicoService.buscar(id);
        if (medicoExistente == null) {
            return ResponseEntity.notFound().build(); // Devuelve un 404 si el pacioente no existe
        }
        medico.setId(id); // aseguramos que se actualice el paciente correcto
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.actualizar(medico));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoService.buscar(id);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }else {
            medicoService.eliminar(id);
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
