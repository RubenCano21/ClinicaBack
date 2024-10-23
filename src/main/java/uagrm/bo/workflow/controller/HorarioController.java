package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Horario> registrarHorario(@RequestBody Horario horario) {

        // obtener la lista de medicos
        List<Medico> medicos = horario.getMedicos().stream()
                .map(m -> new Medico(m.getId()))
                .collect(Collectors.toList());
        horario.setMedicos(medicos);

        Horario nuevoHorario = horarioService.crearHorario(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Horario> eliminarHorario(@PathVariable Long id) {
        horarioService.eliminarHorario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}