package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;

    @Autowired
    private List<ValidadorDeFichas> validadorDeFichas;

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAll();
    }

    public List<Medico> listarMedicosPorEspecialidad(Long especialidadId) {
        Especialidad especialidad = verificarExistenciaEspecialidad(especialidadId);
        List<MedicoEspecialidad> medicoEspecialidades = medicoEspecialidadRepository.findByEspecialidad(especialidad);
        return medicoEspecialidades.stream().map(MedicoEspecialidad::getMedico).collect(Collectors.toList());
    }

    public List<MedicoHorario> listarHorariosPorMedico(Long medicoId) {
        Medico medico = verificarExistenciaMedico(medicoId);
        return medicoHorarioRepository.findByMedico(medico);
    }

    public List<IntervalosHorario> listarIntervalosPorHorario(Long horarioId) {
        MedicoHorario horario = verificarExistenciaHorario(horarioId);
        return intervaloHorarioRepository.findByMedicoHorario(horario);
    }

    public Optional<Paciente> listarPacientes(Long pacienteId) {
        Paciente paciente = verificarExistenciaPaciente(pacienteId);
        return pacienteRepository.findById(paciente.getId());
    }


    @Override
    @Transactional
    public void asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                        Long intervaloId, LocalDateTime fecha) throws ValidacionException {

        if (pacienteId == null || especialidadId == null || medicoId == null ||
                horarioId == null || intervaloId == null) {
            throw new ValidacionException("Todos los identificadores deben ser no nulos");
        }

        // Validación de existencia del paciente
        Optional<Paciente> paciente = listarPacientes(pacienteId);
        if (paciente.isEmpty()) {
            throw new ValidacionException("Paciente no encontrado");
        }

        // Validación de existencia de la especialidad, médico, etc.
        Optional<Especialidad> especialidad = listarEspecialidades().stream()
                .filter(e -> e.getId().equals(especialidadId))
                .findFirst();
        if (especialidad.isEmpty()) {
            throw new ValidacionException("Especialidad no encontrada");
        }

        Optional<Medico> medico = listarMedicosPorEspecialidad(especialidadId).stream()
                .filter(m -> m.getId().equals(medicoId))
                .findFirst();
        if (medico.isEmpty()) {
            throw new ValidacionException("Médico no encontrado");
        }

        Optional<MedicoHorario> horario = listarHorariosPorMedico(medicoId).stream()
                .filter(h -> h.getId().equals(horarioId))
                .findFirst();
        if (horario.isEmpty()) {
            throw new ValidacionException("Horario no encontrado");
        }

        Optional<IntervalosHorario> intervalo = listarIntervalosPorHorario(horarioId).stream()
                .filter(i -> i.getId().equals(intervaloId) && i.getEstado().equals(EstadoIntervalo.LIBRE))
                .findFirst();
        if (intervalo.isEmpty()) {
            throw new ValidacionException("Intervalo no disponible");
        }

        // Crear y guardar la ficha
        Ficha ficha = new Ficha();
        ficha.setPaciente(paciente.get());
        ficha.setEspecialidad(especialidad.get());
        ficha.setMedico(medico.get());
        ficha.setMedicoHorario(horario.get());
        ficha.setIntervaloHorario(intervalo.get());
        ficha.setFechaConsulta(fecha);
        ficha.setCantDisponibles(horario.get().getCantDisponibles() - 1);
        fichaRepository.save(ficha);

        // Actualizar el estado del intervalo
        intervalo.get().setEstado(EstadoIntervalo.RESERVADO);
        intervaloHorarioRepository.save(intervalo.get());

        // Crear el registro de enfermería
        Enfermeria enfermeria = new Enfermeria();
        enfermeria.setFicha(ficha);
        enfermeriaRepository.save(enfermeria);
    }




//    @Override
//    @Transactional
//    public ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
//                                     Long intervaloId, LocalDateTime fecha) {
//
//        // Verificar existencia de especialidad
//        Especialidad especialidad = verificarExistenciaEspecialidad(especialidadId);
//
//        // Verificar existencia de médico
//        Medico medico = verificarExistenciaMedico(medicoId);
//
//
//        MedicoEspecialidad medicoEspecialidad = verificarExistenciaEspecialidadYMedico(medico, especialidad);
//
//        // Verificar existencia de horario
//        MedicoHorario horario = verificarExistenciaHorario(horarioId);
//
//
//        // Verificar existencia de intervalo de horario
//        IntervalosHorario intervaloHorario = verificarExistenciaIntervaloHorario(intervaloId);
//
//        // Verificar existencia de paciente
//        Paciente paciente = verificarExistenciaPaciente(pacienteId);
//
//        // Verificar disponibilidad del intervalo de horario
//        if (!intervaloHorario.getEstado().equals(EstadoIntervalo.LIBRE)) {
//            throw new ValidacionException("El intervalo de horario ya está ocupado");
//        }
//
//
//        // Crear y guardar la ficha
//        Ficha ficha = new Ficha();
//        ficha.setMedico(medico);  // Solo una vez, usando el objeto 'medico' recuperado
//        ficha.setPaciente(paciente);
//        ficha.setMedicoHorario(horario);
//        ficha.setEspecialidad(especialidad);
//        ficha.setIntervaloHorario(intervaloHorario);
//        ficha.setFechaConsulta(fecha);
//        ficha.setCantDisponibles(horario.getCantDisponibles()-1);
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

    private MedicoEspecialidad verificarExistenciaEspecialidadYMedico(Medico medico, Especialidad especialidad) {
        if (medicoEspecialidadRepository.existsByMedicoAndEspecialidad(medico, especialidad)) {
            return medicoEspecialidadRepository.findByMedicoAndEspecialidad(medico, especialidad);
        } else {
            throw new ValidacionException("El médico no tiene la especialidad requerida");
        }
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
