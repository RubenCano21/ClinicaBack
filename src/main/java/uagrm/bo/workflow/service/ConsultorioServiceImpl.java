package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.model.Consultorio;
import uagrm.bo.workflow.repository.ConsultorioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ConsultorioServiceImpl implements ConsultorioService{


    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    @Transactional
    public List<Consultorio> listarConsultorios() {
        return consultorioRepository.findAll();
    }

    @Override
    @Transactional
    public Consultorio crearConsultorio(Consultorio consultorio) {
        Optional<Consultorio> consultorioExistente =
                consultorioRepository.findByNombre(consultorio.getNombre());
        if (consultorioExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un consultorio con el nombre: " + consultorio.getNombre());
        }
        return consultorioRepository.save(consultorio);
    }

    @Override
    @Transactional
    public Consultorio findConsultorioById(Long id) {
        Optional<Consultorio> consultorio = consultorioRepository.findById(id);
        if (consultorio.isPresent()) {
            return consultorio.get();
        }else {
            throw new IllegalArgumentException("No se encontro el id de la Consultorio");
        }
    }

    @Override
    @Transactional
    public Consultorio actualizarConsultorio(Long id, Consultorio consultorio) {

        Consultorio consultorioExistente = findConsultorioById(id);
        consultorioExistente.setNombre(consultorio.getNombre());
        return consultorioRepository.save(consultorioExistente);
    }

    @Override
    @Transactional
    public void eliminarConsultorio(Long id) {
        Consultorio consultorio = findConsultorioById(id);
        consultorioRepository.delete(consultorio);
    }
}
