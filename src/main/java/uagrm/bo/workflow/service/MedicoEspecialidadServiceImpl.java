package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.MedicoEspecialidadDTO;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.MedicoEspecialidad;
import uagrm.bo.workflow.repository.MedicoEspecialidadRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoEspecialidadServiceImpl implements MedicoEspecialidadService{


    @Autowired
    private MedicoEspecialidadRepository medicoEspecialidadRepository;

    @Override
    public List<MedicoEspecialidadDTO> listar() {
        List<MedicoEspecialidad> medicoEspecialidadList = medicoEspecialidadRepository.findAll();

        return medicoEspecialidadList.stream().map(this::convertirAMedicoEspecialidadDTO).collect(Collectors.toList());
    }

    private MedicoEspecialidadDTO convertirAMedicoEspecialidadDTO(MedicoEspecialidad medicoEspecialidad) {
        return new MedicoEspecialidadDTO(medicoEspecialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoEspecialidad> obtenerEspecialidadesPorMedico(Long medicoId) {
        return medicoEspecialidadRepository.findByMedicoId(medicoId);
    }

    @Override
    @Transactional
    public MedicoEspecialidad asignarEspecialidadAMedico(Medico medico, Especialidad especialidad) {

        //verificamos si existe una especialidad asignada a un medico
        boolean existe = medicoEspecialidadRepository.existsByMedicoAndEspecialidad(medico, especialidad);
        if (existe){
            throw new RuntimeException("Ya existe una especialidad asignada a ese medico");
        }

        MedicoEspecialidad medicoEspecialidad = new MedicoEspecialidad();

        medicoEspecialidad.setMedico(medico);
        medicoEspecialidad.setEspecialidad(especialidad);

        return medicoEspecialidadRepository.save(medicoEspecialidad);
    }
}
