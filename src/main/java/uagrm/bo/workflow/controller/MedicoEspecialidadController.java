package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.MedicoEspecialidadDTO;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.repository.EspecialidadRepository;
import uagrm.bo.workflow.repository.MedicoHorarioRepository;
import uagrm.bo.workflow.repository.MedicoRepository;
import uagrm.bo.workflow.service.MedicoEspecialidadService;

@RestController
@RequestMapping("/api/medico-especialidad")
public class MedicoEspecialidadController {


    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;


    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(medicoEspecialidadService.listar());
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<?> obtenerEspecialidadesPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(medicoEspecialidadService.obtenerEspecialidadesPorMedico(medicoId));
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarEspecialidadAMedico(
            @RequestBody MedicoEspecialidadDTO request) {

        try {
            Medico medico = medicoRepository.findById(request.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("No se encontro el medico con id: " + request.getMedicoId()));

            Especialidad especialidad = especialidadRepository.findById(request.getEspecialidadId())
                    .orElseThrow(() -> new RuntimeException("No se encontro la especialidad con id: " + request.getEspecialidadId()));

            medicoEspecialidadService.asignarEspecialidadAMedico(medico, especialidad);
            return ResponseEntity.ok("Especialidad asignada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
