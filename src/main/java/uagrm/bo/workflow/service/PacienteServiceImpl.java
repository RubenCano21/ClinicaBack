package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.exceptions.PacienteNotFoundException;
import uagrm.bo.workflow.model.Paciente;
import uagrm.bo.workflow.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscar(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Paciente actualizar(Paciente paciente) {
        if (!pacienteRepository.existsPacienteById(paciente.getId())) {
            throw new PacienteNotFoundException("Paciente no encontrado con id: " + paciente.getId());
        }
        return pacienteRepository.save(paciente);
    }
}
