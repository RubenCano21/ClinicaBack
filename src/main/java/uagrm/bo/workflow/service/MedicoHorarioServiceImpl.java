package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoHorario;
import uagrm.bo.workflow.repository.ConsultorioRepository;
import uagrm.bo.workflow.repository.HorarioRepository;
import uagrm.bo.workflow.repository.MedicoHorarioRepository;
import uagrm.bo.workflow.repository.MedicoRepository;

import java.util.List;

@Service
public class MedicoHorarioServiceImpl implements MedicoHorarioService{

    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    @Transactional
    public void asignarHorario(Medico medico, Consultorio consultorio, Horario horario) {



        // verificamos si existe un medico asignado a un consultorio en un horario
        boolean existe = medicoHorarioRepository.existsByConsultorioAndHorario(consultorio, horario);
        if (existe){
            throw new RuntimeException("Ya existe un medico asignado a ese consultorio en ese horario");
        }
        // creamos la asignacion
        MedicoHorario medicoHorario = new MedicoHorario();
        medicoHorario.setMedico(medico);
        medicoHorario.setConsultorio(consultorio);
        medicoHorario.setHorario(horario);
        medicoHorarioRepository.save(medicoHorario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoHorario> listarHorariosMedico() {
        return medicoHorarioRepository.findAll();
    }
}
