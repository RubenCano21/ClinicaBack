package uagrm.bo.workflow.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.DatosMedicoHorario;
import uagrm.bo.workflow.dto.MedicoHorarioDTO;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.ConsultorioRepository;
import uagrm.bo.workflow.repository.HorarioRepository;
import uagrm.bo.workflow.repository.MedicoRepository;
import uagrm.bo.workflow.service.MedicoHorarioService;

import java.util.List;

@RestController
@RequestMapping("/api/medico-horarios")
public class MedicoHorarioController {

    @Autowired
    private MedicoHorarioService medicoHorarioService;

    @Autowired
    private MedicoRepository medicoRepository;


    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoHorario>> listar() {
        return new ResponseEntity<>(medicoHorarioService.listarHorariosMedico(), HttpStatus.OK);
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarHorario(@Valid @RequestBody DatosMedicoHorario datos) {

        // Asignar el horario al medico
        try {
            Medico medico = medicoRepository.findById(datos.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("No se encontro el medico con id: " + datos.getMedicoId()));

            Consultorio consultorio = consultorioRepository.findById(datos.getConsultorioId())
                    .orElseThrow(() -> new RuntimeException("No se encontro el consultorio con id: " + datos.getConsultorioId()));
            Horario horario = horarioRepository.findById(datos.getHorarioId())
                    .orElseThrow(() -> new RuntimeException("No se encontro el horario con id: " + datos.getHorarioId()));

            //generarIntervalos(datos);
            medicoHorarioService.asignarHorario(medico, consultorio, horario, datos.getCantDisponibles());
            return ResponseEntity.ok("Horario asignado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @PostMapping("/generar-intervalos")
    public ResponseEntity<?> generarIntervalos(@RequestBody MedicoHorarioDTO datos) {
        try {
            MedicoHorario medicoHorario = medicoHorarioService.obtenerMedicoHorario(datos.getMedico().getId(),
                    datos.getMedico().getId(), datos.getHorario().getId());

            medicoHorarioService.generarIntervalos(medicoHorario, 15);
            return ResponseEntity.ok("Intervalos generados correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

























}
