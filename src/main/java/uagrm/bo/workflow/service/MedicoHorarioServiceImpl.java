package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.model.*;
import uagrm.bo.workflow.repository.IntervaloHorarioRepository;
import uagrm.bo.workflow.repository.MedicoHorarioRepository;

import java.time.LocalTime;
import java.util.List;

@Service
public class MedicoHorarioServiceImpl implements MedicoHorarioService{

    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;


    @Autowired
    private IntervaloHorarioRepository intervaloHorarioRepository;

    @Override
    @Transactional
    public void asignarHorario(Medico medico, Consultorio consultorio, Horario horario, Integer cantDisponibles) {

        // verificamos si existe un medico asignado a un consultorio en un horario
        boolean existe = medicoHorarioRepository.existsByConsultorioAndHorario(consultorio, horario);
        if (existe){
            throw new RuntimeException("Ya existe un medico asignado a ese consultorio en ese horario");
        }
        // si no existe el medico asignado a un consultorio en un horario
        // generamos los intervalos de horario
        //generarIntervalos(new MedicoHorario(medico, consultorio, horario, cantDisponibles), 30);


        // creamos la asignacion
        MedicoHorario medicoHorario = new MedicoHorario();


        medicoHorario.setMedico(medico);
        medicoHorario.setConsultorio(consultorio);
        medicoHorario.setHorario(horario);
        medicoHorario.setCantDisponibles(cantDisponibles);

        medicoHorarioRepository.save(medicoHorario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoHorario> listarHorariosMedico() {
        return medicoHorarioRepository.findAll();
    }

    @Override
    @Transactional
    public void generarIntervalos(MedicoHorario horario, int duracionMin) {
        LocalTime inicio = horario.getHorario().getHoraInicio();
        LocalTime fin = horario.getHorario().getHoraFin();

        while (inicio.isBefore(fin)){
            LocalTime timeFin = inicio.plusMinutes(duracionMin);

            if (timeFin.isAfter(fin)){
                break;
            }

            IntervalosHorario intervalo = new IntervalosHorario();

            intervalo.setHoraInicio(inicio);
            intervalo.setHoraFin(timeFin);
            intervalo.setEstado(EstadoIntervalo.LIBRE);
            intervalo.setMedicoHorario(horario);

            intervaloHorarioRepository.save(intervalo);

            inicio = timeFin;
        }
    }

    @Override
    public MedicoHorario obtenerMedicoHorario(Long medicoId, Long consultorioId, Long horarioId) {

        return medicoHorarioRepository.findByMedicoIdAndConsultorioIdAndHorarioId(medicoId, consultorioId, horarioId)
                .orElseThrow(() -> new RuntimeException("No se encontro el medicoHorario"));
    }
}
