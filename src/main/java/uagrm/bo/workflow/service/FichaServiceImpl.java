package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uagrm.bo.workflow.exceptions.*;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.*;

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
    public Ficha asignarFicha(Long pacienteId, Long especialidadId, Long medicoId,
                              Long horarioId) {

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));

        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicosNotFoundException(medicoId));

        Horario horario = horarioRepository.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException(horarioId));

        Ficha ficha = new Ficha();

        ficha.setPaciente(paciente);
        ficha.setEspecialidad(especialidad);
        ficha.setMedico(medico);
        ficha.setHorario(horario);
        ficha.setCantDisponibles(horario.getCapacidad());

        return fichaRepository.save(ficha);
    }

    @Override
    public List<Ficha> listarFichas() {
        return fichaRepository.findAll();
    }
}
