package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.validaciones.ValidadorDeFichas;
import uagrm.bo.workflow.exceptions.*;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    private List<ValidadorDeFichas> validadores;
    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;

    @Autowired
    private EnfermeriaRepository enfermeriaRepository;


    @Override
    @Transactional
    public Ficha asignarFicha(Long pacienteId, Long especialidadId, Long medicoId,
                              Long horarioId) {

        MedicoHorario horario = medicoHorarioRepository.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException(horarioId));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));

        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicosNotFoundException(medicoId));

        // contar cuantas fichas ya estan asignadas a este horario
//        int fichasAsignadas = fichaRepository.countByMedicoIdAndHorarioId(medicoId, horarioId);
//
//        // verificar si hay espacio disponible
//        if (fichasAsignadas >= horario.getCantDisponibles()) {
//            throw new IllegalArgumentException("No hay fichas disponibles para este medico en este horario");
//        }

        Ficha ficha = new Ficha();

        ficha.setPaciente(paciente);
       // ficha.setIntervaloHorario(horario);
        ficha.setFechaConsulta(LocalDateTime.now());

        return fichaRepository.save(ficha);
    }



    @Override
    @Transactional
    public ResponseEntity<?> agendarFicha(Long pacienteId, Long medicoHorarioId, LocalDateTime fecha){

        Optional<MedicoHorario> medicoHorario = Optional.ofNullable(medicoHorarioRepository.findById(medicoHorarioId)
                .orElseThrow(() -> new ValidacionException("No existe el medicoHorario con id: " + medicoHorarioId)));

        if (medicoHorario.isPresent()){
            MedicoHorario horario = medicoHorario.get();

            if (horario.getCantDisponibles() > 0){

                Optional<IntervalosHorario> intervalosHorario;
                intervalosHorario = intervaloHorarioRepository.findById(medicoHorarioId);

                if (intervalosHorario.isPresent()){
                    IntervalosHorario intervaloHorario = intervalosHorario.get();

                    if (intervaloHorario.getEstado().equals(EstadoIntervalo.LIBRE)){

                        Ficha ficha = new Ficha();

                        ficha.setMedico(horario.getMedico());
                        ficha.setMedico(medicoEspecialidadRepository.findById(medicoHorarioId).get().getMedico());
                        ficha.setIntervaloHorario(intervaloHorario);
                        ficha.setPaciente(pacienteRepository.findById(pacienteId).get());
                        ficha.setFechaConsulta(fecha);

                        fichaRepository.save(ficha);

                        intervaloHorario.setEstado(EstadoIntervalo.RESERVADO);
                        intervaloHorarioRepository.save(intervaloHorario);

                        return ResponseEntity.ok("Ficha agendada correctamente");
                    }else {
                        throw new ValidacionException("El intervalo de horario ya esta ocupado");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el intervalo de horario");
                }
            } else {
                // decrementar la cantidad de fichas disponibles
                horario.setCantDisponibles(horario.getCantDisponibles() - 1);
                medicoHorarioRepository.save(horario);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> asignar(Long pacienteId, Long especialidadId, Long medicoId, Long horarioId,
                                     Long intervaloId, LocalDateTime fecha) {

        // Verificar existencia de especialidad
        Especialidad especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));
        //especialidadRepository.save(especialidad);

        // Verificar existencia de médico
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new MedicosNotFoundException(medicoId));

        // Verificar existencia de horario
        MedicoHorario horario = medicoHorarioRepository.findById(horarioId)
                .orElseThrow(() -> new HorarioNotFoundException(horarioId));

        // Verificar existencia de intervalo de horario
        IntervalosHorario intervaloHorario = intervaloHorarioRepository.findById(intervaloId)
                .orElseThrow(() -> new ValidacionException("No existe el intervalo de horario con id: " + intervaloId));

        // Verificar existencia de paciente
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new PacientesNotFoundException(pacienteId));

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


    @Override
    @Transactional(readOnly = true)
    public List<Ficha> listarFichas() {
        return fichaRepository.findAll();
    }
}
