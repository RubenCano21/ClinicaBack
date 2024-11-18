package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uagrm.bo.workflow.dto.DatosMedicoEspecialidad;
import uagrm.bo.workflow.dto.MedicoEspecialidadDTO;
import uagrm.bo.workflow.dto.PageResponseDTO;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;
import uagrm.bo.workflow.repository.EspecialidadRepository;
import uagrm.bo.workflow.repository.MedicoRepository;
import uagrm.bo.workflow.service.MedicoEspecialidadService;

import java.util.List;

@RestController
@RequestMapping("/api/medico-especialidad")
public class MedicoEspecialidadController {


    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;


    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoEspecialidad>> listar() {
        return new ResponseEntity<>(medicoEspecialidadService.listarMedicosYEspecialidades(), HttpStatus.OK);
    }

    @GetMapping("/listar-pagina")
    public PageResponseDTO<MedicoEspecialidadDTO> listarPaginaMedicoEspecialidad(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size,
                                                                                 @RequestParam(required = false) String nombreFiltro) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MedicoEspecialidad> medicoEspecialidads = medicoEspecialidadService.listarPaginaMedicoEspecialidad(pageable, nombreFiltro);
        return new PageResponseDTO<>(
                medicoEspecialidads.getContent().stream().map(this::convertirADTO).toList(),
                medicoEspecialidads.getNumber(),
                medicoEspecialidads.getSize(),
                medicoEspecialidads.getTotalElements()
        );
    }

    private MedicoEspecialidadDTO convertirADTO(MedicoEspecialidad medicoEspecialidad) {
        return new MedicoEspecialidadDTO(medicoEspecialidad);
    }


    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<?> obtenerEspecialidadesPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(medicoEspecialidadService.obtenerEspecialidadesPorMedico(medicoId));
    }

    @GetMapping("/especialidad/{especialidadId}")
    public ResponseEntity<?> obtenerMedicosPorEspecialidad(@PathVariable Long especialidadId) {
        return ResponseEntity.ok(medicoEspecialidadService.obtenerMedicosPorEspecialidad(especialidadId));
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarEspecialidadAMedico(@RequestBody DatosMedicoEspecialidad request) {

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
