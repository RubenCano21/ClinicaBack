package uagrm.bo.workflow.dto.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uagrm.bo.workflow.dto.DatosReservaFicha;
import uagrm.bo.workflow.repository.FichaRepository;

@Component
public class ValidarMedicoConOtraFichaEnElMismoHorario implements ValidadorDeFichas {

    @Autowired
    private FichaRepository fichaRepository;

    public void validar(DatosReservaFicha datos) {
        var medicoTieneOtraFichaEnElMismoHorario = fichaRepository.existsByMedicoIdAndFechaConsulta(datos.getMedicoId(), datos.getFechaConsulta());
        if (medicoTieneOtraFichaEnElMismoHorario) {
            throw new RuntimeException("El medico ya tiene una ficha en el mismo horario");
        }
    }
}
