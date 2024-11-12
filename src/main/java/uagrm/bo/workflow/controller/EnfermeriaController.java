package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.EnfermeriaDTO;
import uagrm.bo.workflow.model.Enfermeria;
import uagrm.bo.workflow.service.EnfermeriaService;

import java.util.List;

@RestController
@RequestMapping("/api/enfermeria")
public class EnfermeriaController {

    @Autowired
    private EnfermeriaService enfermeriaService;

    @PostMapping("/guardar")
    public Enfermeria guardar(@RequestBody Enfermeria enfermeria){
        return enfermeriaService.guardar(enfermeria);
    }

    @GetMapping("/{id}")
    public Enfermeria buscarPorId(@PathVariable Long id){
        return enfermeriaService.buscarPorId(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EnfermeriaDTO>> listarTodos(){
        return new ResponseEntity<>(enfermeriaService.listar(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Enfermeria actualizar(@PathVariable Long id, @RequestBody Enfermeria enfermeria){
        return enfermeriaService.actualizar(id, enfermeria);
    }
}
