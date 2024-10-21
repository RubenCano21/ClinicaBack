package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.exceptions.MedicoNotFoundException;
import uagrm.bo.workflow.exceptions.PacienteNotFoundException;
import uagrm.bo.workflow.model.Medico;
import uagrm.bo.workflow.model.Paciente;
import uagrm.bo.workflow.repository.MedicoRepository;
import uagrm.bo.workflow.repository.PacienteRepository;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico buscar(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Medico actualizar(Medico medico) {
        if (!medicoRepository.existsMedicoById(medico.getId())) {
            throw new MedicoNotFoundException("Medico no encontrado con id: " + medico.getId());
        }
        return medicoRepository.save(medico);
    }
}
