package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.EspecialidadDTO;
import uagrm.bo.workflow.exceptions.EspecialidadNotFoundException;
import uagrm.bo.workflow.exceptions.MedicoNotFoundException;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.repository.EspecialidadRepository;
import uagrm.bo.workflow.repository.MedicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;


    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadDTO> listar() {
        List<Especialidad> especialidadList = especialidadRepository.findAll();

        return especialidadList.stream().map(this::convertirAEspecialidadDTO).collect(Collectors.toList());
    }

    private EspecialidadDTO convertirAEspecialidadDTO(Especialidad especialidad) {
        return new EspecialidadDTO(especialidad);
    }

    @Override
    public Especialidad buscar(Long id) {
        return especialidadRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Especialidad guardar(Especialidad especialidad) {
        if (especialidadRepository.existsEspecialidadByNombre(especialidad.getNombre())) {
            throw new IllegalArgumentException("La especialidad ya existe");
        }
        return especialidadRepository.save(especialidad);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        especialidadRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Especialidad actualizar(Especialidad especialidad) {
        if (!especialidadRepository.existsById(especialidad.getId())) {
            throw new MedicoNotFoundException("Especialidad no encontrado con id: " + especialidad.getId());
        }
        return especialidadRepository.save(especialidad);
    }

//    @Override
//    public List<Especialidad> findMedicoByEspecialidad(Long especialidadId) {
//        Especialidad especialidad = especialidadRepository.findById(especialidadId)
//                .orElseThrow(() -> new EspecialidadNotFoundException(especialidadId));
//        return especialidad.getMedicos();
//    }
}
