package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.exceptions.*;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class FichaServiceImpl implements FichaService{

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    @Override
    @Transactional
    public Ficha asignarFicha(Long pacienteId, Long especialidadId, Long medicoId,
                              Long horarioId) {

        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException(horarioId));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));

        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicosNotFoundException(medicoId));

        // contar cuantas fichas ya estan asignadas a este horario
        int fichasAsignadas = fichaRepository.countByMedicoIdAndHorarioId(medicoId, horarioId);

        // verificar si hay espacio disponible
        if (fichasAsignadas >= horario.getCapacidad()) {
            throw new IllegalArgumentException("No hay fichas disponibles para este horario");
        }

        Ficha ficha = new Ficha();

        ficha.setPaciente(paciente);
        ficha.setEspecialidad(especialidad);
        ficha.setMedico (medico);
        ficha.setHorario(horario);
        ficha.setCantDisponibles(horario.getCapacidad() - fichasAsignadas);
        ficha.setFechaConsulta(LocalDate.now());

        return fichaRepository.save(ficha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ficha> listarFichas() {
        return fichaRepository.findAll();
    }
}
