package uagrm.bo.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uagrm.bo.workflow.exceptions.HorarioDuplicadoException;
import uagrm.bo.workflow.exceptions.ResourceNotFoundException;
import uagrm.bo.workflow.model.Horario;
import uagrm.bo.workflow.repository.HorarioRepository;

import java.util.List;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    @Transactional
    public Horario crearHorario(Horario horario) {
        // Opcional: Verificar que no exista un horario duplicado en el mismo dia y rango de horas
        if (horarioRepository.existsByHoraInicioAndHoraFinAndDia(
                  horario.getHoraInicio(), horario.getHoraFin(), horario.getDia())) {
            throw new HorarioDuplicadoException("Ya existe un horario para el dia "+ horario.getDia() + " en ese rango de horas");
        }

        // Guardar el nuevo horario
        return horarioRepository.save(horario);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Horario> obtenerHorariosPorMedico(Long medicoId) {
        return horarioRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarHorario(Long id) {
        try {
            horarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Horario no encontrado con el id: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Horario> listar() {
        return horarioRepository.findAll();
    }
}
