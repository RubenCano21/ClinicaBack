package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    private MedicoEspecialidadDTO convertirAMedicoEspecialidadDTO(MedicoEspecialidad medicoEspecialidad) {
        return new MedicoEspecialidadDTO(medicoEspecialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoEspecialidadDTO> obtenerEspecialidadesPorMedico(Long medicoId) {
        List<MedicoEspecialidad> medicoEspecialidadList = medicoEspecialidadRepository.findByMedicoId(medicoId);
        return medicoEspecialidadList.stream().map(this::convertirAMedicoEspecialidadDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoEspecialidadDTO> obtenerMedicosPorEspecialidad(Long especialidadId) {
        List<MedicoEspecialidad> medicoEspecialidadList = medicoEspecialidadRepository.findByEspecialidadId(especialidadId);
        return medicoEspecialidadList.stream().map(this::convertirAMedicoEspecialidadDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void asignarEspecialidadAMedico(Medico medico, Especialidad especialidad) {

        //verificamos si existe una especialidad asignada a un medico
        boolean existe = medicoEspecialidadRepository.existsByMedicoAndEspecialidad(medico, especialidad);
        if (existe){
            throw new RuntimeException("Ya existe una especialidad asignada a ese medico");
        }

        MedicoEspecialidad medicoEspecialidad = new MedicoEspecialidad();

        medicoEspecialidad.setMedico(medico);
        medicoEspecialidad.setEspecialidad(especialidad);

        medicoEspecialidadRepository.save(medicoEspecialidad);
    }

    @Override
    public Page<MedicoEspecialidad> listarPaginaMedicoEspecialidad(Pageable pageable, String nombreFiltro) {
        if (nombreFiltro != null && !nombreFiltro.isEmpty()) {
            return medicoEspecialidadRepository.findByMedicoNombreContaining(nombreFiltro, pageable);
        } else {
            return medicoEspecialidadRepository.findAll(pageable);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicoEspecialidad> listarMedicosYEspecialidades() {
        return medicoEspecialidadRepository.findAll();
        //return medicoEspecialidadList.stream().map(this::convertirAMedicoEspecialidadDTO).collect(Collectors.toList());
    }
}
