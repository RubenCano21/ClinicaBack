package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.dto.IntervaloHorarioDTO;
import uagrm.bo.workflow.model.IntervalosHorario;
import uagrm.bo.workflow.model.MedicoHorario;
import uagrm.bo.workflow.repository.IntervaloHorarioRepository;
import uagrm.bo.workflow.repository.MedicoHorarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IntervaloHorarioServiceImpl implements IntervaloHorarioService{

    @Autowired
    private IntervaloHorarioRepository intervaloHorarioRepository;


    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;


    @Override
    @Transactional(readOnly = true)
    public List<IntervaloHorarioDTO> listarIntervalosHorarios() {
        List<IntervalosHorario> intervalosHorarios = intervaloHorarioRepository.findAll();
        return intervalosHorarios.stream().map(this::convertirAIntervaloHorarioDTO).collect(Collectors.toList());
    }

    private IntervaloHorarioDTO convertirAIntervaloHorarioDTO(IntervalosHorario intervalosHorario) {
        return new IntervaloHorarioDTO(intervalosHorario);
    }

    @Override
    public Optional<IntervalosHorario> obtenerIntervaloHorarioPorId(Long id) {

        // para obtener un intervalo horario, primero buscamos si existe un medico asignado a un consultorio en un horario
        // si existe, retornamos el intervalo horario
        // si no existe, retornamos un Optional.empty()
        MedicoHorario medicoHorario = new MedicoHorario();

        boolean existe = medicoHorarioRepository.existsByConsultorioAndHorario(medicoHorario.getConsultorio(), medicoHorario.getHorario());

        return Optional.empty();
    }

    @Override
    public IntervalosHorario guardarIntervaloHorario(IntervalosHorario intervaloHorario) {
        return null;
    }

    @Override
    public void eliminarIntervaloHorario(Long id) {

    }
}
