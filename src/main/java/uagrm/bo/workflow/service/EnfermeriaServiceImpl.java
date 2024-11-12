package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.EnfermeriaDTO;
import uagrm.bo.workflow.dto.IntervaloHorarioDTO;
import uagrm.bo.workflow.model.Enfermeria;
import uagrm.bo.workflow.repository.EnfermeriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnfermeriaServiceImpl implements EnfermeriaService{

    @Autowired
    private EnfermeriaRepository enfermeriaRepository;


    @Override
    public Enfermeria guardar(Enfermeria enfermeria) {
        return enfermeriaRepository.save(enfermeria);
    }

    @Override
    public Enfermeria buscarPorId(Long id) {
        return enfermeriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Datos de enfermeria no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnfermeriaDTO> listar() {
        List<Enfermeria> enfermerias = enfermeriaRepository.findAll();
        return enfermerias.stream().map(this::convertirAEnfermeriaDTO).collect(Collectors.toList());
    }

    private EnfermeriaDTO convertirAEnfermeriaDTO(Enfermeria enfermeria) {
        return new EnfermeriaDTO(enfermeria);
    }

    @Override
    public Enfermeria actualizar(Long id, Enfermeria enfermeria) {
        Enfermeria actualizarEnfermeria = buscarPorId(id);
        actualizarEnfermeria.setFicha(enfermeria.getFicha());
        actualizarEnfermeria.setSintomas(enfermeria.getSintomas());
        return enfermeriaRepository.save(actualizarEnfermeria);
    }
}
