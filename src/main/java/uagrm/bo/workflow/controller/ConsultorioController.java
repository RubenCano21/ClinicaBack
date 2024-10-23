package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.service.ConsultorioService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {


    @Autowired
    private ConsultorioService consultorioService;

    @GetMapping
    public ResponseEntity<List<Consultorio>> listarConsultorios() {
        List<Consultorio> consultorios = consultorioService.listarConsultorios();
        return ResponseEntity.ok(consultorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> obtenerConsultorioPorId(@PathVariable Long id) {
        Consultorio consultorio = consultorioService.findConsultorioById(id);
        return ResponseEntity.ok(consultorio);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> crearConsultorio(@RequestBody Consultorio consultorio) {

        try {
            Consultorio nuevoConsultorio = consultorioService.crearConsultorio(consultorio);
            return ResponseEntity.ok(nuevoConsultorio);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Collections.singletonMap("error",e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> actualizarConsultorio(@PathVariable Long id, @RequestBody Consultorio consultorioActualizado) {
        Consultorio consultorio = consultorioService.actualizarConsultorio(id, consultorioActualizado);
        return ResponseEntity.ok(consultorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsultorio(@PathVariable Long id) {
        consultorioService.eliminarConsultorio(id);
        return ResponseEntity.noContent().build();
    }
}
