package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.HistorialDTO;
import uagrm.bo.workflow.exceptions.MedicoNotFoundException;
import uagrm.bo.workflow.exceptions.PacienteNotFoundException;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    @Transactional
    public Historial crearHistorial(HistorialDTO historialDTO) {

        Historial historial = new Historial();
        // validar que el paciente exista
        Paciente paciente = pacienteRepository.findById(historialDTO.getPacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado"));
        historial.setPaciente(paciente);

        // validar que el medico exista
        Medico medico = medicoRepository.findById(historialDTO.getMedicoId())
                .orElseThrow(() -> new MedicoNotFoundException("Medico no encontrado"));
        historial.setMedico(medico);

        Consultorio consultorio = consultorioRepository.findById(historialDTO.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));
        historial.setConsultorio(consultorio);

        Ficha ficha = fichaRepository.findById(historialDTO.getFichaId())
                .orElseThrow(() -> new RuntimeException("Ficha no encontrada"));
        historial.setFicha(ficha);


        if (historialRepository.existsHistorialByPacienteId(historial.getPaciente().getId())) {
            throw new RuntimeException("El paciente ya tiene un historial");
        }

        historial.setFechaConsulta(LocalDate.parse(historialDTO.getFechaConsulta()));
        historial.setDescripcion(historialDTO.getDescripcion());
        return historialRepository.save(historial);
    }

    @Override
    @Transactional(readOnly = true)
    public Historial obtenerHistorialPorId(Long id) {
        return historialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Historial> obtenerHistorialesPorPacienteId(Long pacienteId) {
        List<Historial> obtenerHistorial = historialRepository.findByPacienteId(pacienteId);
        if (obtenerHistorial.isEmpty()) {
            throw new RuntimeException("el paciente con id: " + pacienteId + " no tiene historiales");
        }
        return historialRepository.findByPacienteId(pacienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Historial> obtenerHistorialesPorMedicoId(Long medicoId) {
        return historialRepository.findByMedicoId(medicoId);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Historial> obtenerTodosLosHistoriales() {
        return historialRepository.findAll();
    }

    @Override
    @Transactional
    public Historial actualizarHistorial(Long id, Historial historial) {
        Historial actualizarHistorial = obtenerHistorialPorId(id);
        actualizarHistorial.setFechaConsulta(historial.getFechaConsulta());
        actualizarHistorial.setDescripcion(historial.getDescripcion());
        actualizarHistorial.setPaciente(historial.getPaciente());
        actualizarHistorial.setMedico(historial.getMedico());
        actualizarHistorial.setConsultorio(historial.getConsultorio());
        actualizarHistorial.setFicha(historial.getFicha());

        return historialRepository.save(actualizarHistorial);
    }

    @Override
    @Transactional
    public Historial actualizarHistorialPorMedico(Long historialId, String diagnostico, Long medicoId) {

        Historial historial = obtenerHistorialPorId(historialId);

        // validar que el historial le pertenezca al medico
        if (!historial.getMedico().getId().equals(medicoId)) {
            throw new RuntimeException("El historial no le pertenece al medico");
        }

        // validar que el historial actual sin diagnostico
        if (historial.getFechaConsulta().isBefore(LocalDate.now()) ||
        historial.getDescripcion() != null){
            throw new RuntimeException("Solo puedes editar el diagnostico de la consulta actual");
        }

        historial.setDescripcion(diagnostico);

        return historialRepository.save(historial);
    }
}
