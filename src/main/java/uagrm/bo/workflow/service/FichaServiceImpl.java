package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.DatosFichas;
import uagrm.bo.workflow.dto.FichaDTO;
import uagrm.bo.workflow.dto.validaciones.ValidadorDeFichas;
import uagrm.bo.workflow.exceptions.*;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FichaServiceImpl implements FichaService{

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private IntervaloHorarioRepository intervaloHorarioRepository;


    @Autowired
    private EnfermeriaRepository enfermeriaRepository;




//    @Override
//    @Transactional
//    public ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
//                                     Long intervaloId, LocalDateTime fecha) {
//
//        // Verificar existencia de especialidad
//        Especialidad especialidad = especialidadRepository.findById(especialidadId)
//                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));
//        //especialidadRepository.save(especialidad);
//
//        // Verificar existencia de médico
//        Medico medico = medicoRepository.findById(medicoId)
//                .orElseThrow(() -> new MedicosNotFoundException(medicoId));
//
//        // Verificar existencia de horario
//        MedicoHorario horario = medicoHorarioRepository.findById(horarioId)
//                .orElseThrow(() -> new HorarioNotFoundException(horarioId));
//
//        // Verificar existencia de intervalo de horario
//        IntervalosHorario intervaloHorario = intervaloHorarioRepository.findById(intervaloId)
//                .orElseThrow(() -> new ValidacionException("No existe el intervalo de horario con id: " + intervaloId));
//
//        // Verificar existencia de paciente
//        Paciente paciente = pacienteRepository.findById(pacienteId)
//                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));
//
//
//        // Verificar disponibilidad del intervalo de horario
//        if (!intervaloHorario.getEstado().equals(EstadoIntervalo.LIBRE)) {
//            throw new ValidacionException("El intervalo de horario ya está ocupado");
//        }
//
//        if (horario.getCantDisponibles() <= 0) {
//            throw new ValidacionException("No hay fichas disponibles para el horario seleccionado");
//        }
//
//        horario.setCantDisponibles(horario.getCantDisponibles() - 1);
//        medicoHorarioRepository.save(horario);
//
//        // Crear y guardar la ficha
//        Ficha ficha = new Ficha();
//        ficha.setMedico(medico);  // Solo una vez, usando el objeto 'medico' recuperado
//        ficha.setPaciente(paciente);
//        ficha.setMedicoHorario(horario);
//        ficha.setEspecialidad(especialidad);
//        ficha.setIntervaloHorario(intervaloHorario);
//        ficha.setFechaConsulta(fecha);
//
//        fichaRepository.save(ficha);
//
//        // Actualizar estado del intervalo de horario
//        intervaloHorario.setEstado(EstadoIntervalo.RESERVADO);
//        intervaloHorarioRepository.save(intervaloHorario);
//
//        // crear registro de enfermeria
//        Enfermeria enfermeria = new Enfermeria();
//        enfermeria.setFicha(ficha);
//        enfermeriaRepository.save(enfermeria);
//
//        // Devolver detalles de la ficha creada en la respuesta
//        return ResponseEntity.ok("Ficha agendada correctamente para el paciente " + paciente.getNombre());
//    }

    @Override
    @Transactional
    public ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                                     Long intervaloId, LocalDateTime fecha) {

        // Verificar existencia de especialidad
        Especialidad especialidad = verificarExistenciaEspecialidad(especialidadId);

        // Verificar existencia de médico
        Medico medico = verificarExistenciaMedico(medicoId);

        // Verificar existencia de horario
        MedicoHorario horario = verificarExistenciaHorario(horarioId);

        // Verificar existencia de intervalo de horario
        IntervalosHorario intervaloHorario = verificarExistenciaIntervaloHorario(intervaloId);

        // Verificar existencia de paciente
        Paciente paciente = verificarExistenciaPaciente(pacienteId);

        // Verificar disponibilidad del intervalo de horario
        if (!intervaloHorario.getEstado().equals(EstadoIntervalo.LIBRE)) {
            throw new ValidacionException("El intervalo de horario ya está ocupado");
        }


        // Crear y guardar la ficha
        Ficha ficha = new Ficha();
        ficha.setMedico(medico);  // Solo una vez, usando el objeto 'medico' recuperado
        ficha.setPaciente(paciente);
        ficha.setMedicoHorario(horario);
        ficha.setEspecialidad(especialidad);
        ficha.setIntervaloHorario(intervaloHorario);
        ficha.setFechaConsulta(fecha);
        ficha.setCantDisponibles(horario.getCantDisponibles()-1);

        fichaRepository.save(ficha);

        // Actualizar estado del intervalo de horario
        intervaloHorario.setEstado(EstadoIntervalo.RESERVADO);
        intervaloHorarioRepository.save(intervaloHorario);

        // crear registro de enfermeria
        Enfermeria enfermeria = new Enfermeria();
        enfermeria.setFicha(ficha);
        enfermeriaRepository.save(enfermeria);

        // Devolver detalles de la ficha creada en la respuesta
        return ResponseEntity.ok("Ficha agendada correctamente para el paciente " + paciente.getNombre());
    }

    private Paciente verificarExistenciaPaciente(Long pacienteId) {
        return pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));
    }

    private IntervalosHorario verificarExistenciaIntervaloHorario(Long intervaloId) {
        return intervaloHorarioRepository.findById(intervaloId)
                .orElseThrow(() -> new ValidacionException("No existe el intervalo de horario con id: " + intervaloId));
    }

    private MedicoHorario verificarExistenciaHorario(Long horarioId) {
        return medicoHorarioRepository.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException(horarioId));
    }

    private Medico verificarExistenciaMedico(Long medicoId) {
        return medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicosNotFoundException(medicoId));
    }

    private Especialidad verificarExistenciaEspecialidad(Long especialidadId) {
        return especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));
    }


    @Override
    @Transactional(readOnly = true)
    public List<FichaDTO> listarFichas() {
        List<Ficha> fichas =  fichaRepository.findAll();

       return fichas.stream().map(this::convertirFichaADTO).collect(Collectors.toList());
    }

    private FichaDTO convertirFichaADTO(Ficha newFicha) {
        return new FichaDTO(newFicha);
    }
}
