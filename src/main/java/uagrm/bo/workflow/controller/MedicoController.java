package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.Paciente;
import uagrm.bo.workflow.service.MedicoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;


    @GetMapping
    public List<Medico> listar() {
        return medicoService.listar();
    }

    @PostMapping
    public ResponseEntity<?> crearPaciente(@Valid @RequestBody Medico medico, BindingResult result) {
        if (result.hasFieldErrors()) {
            return  validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.guardar(medico));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Medico medico, BindingResult result) {
        return crearPaciente(medico, result);
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
