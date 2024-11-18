package uagrm.bo.workflow.dto.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uagrm.bo.workflow.dto.DatosReservaFicha;
import uagrm.bo.workflow.repository.FichaRepository;

@Component
public class ValidarPacienteSinOtraConsultaEnELMismoDia implements ValidadorDeFichas {


    @Autowired
    private FichaRepository fichaRepository;

    public void validar(DatosReservaFicha datos){

        var primerHorario = datos.getFechaConsulta().withHour(8);
        var ultimoHorario = datos.getFechaConsulta().withHour(18);
        var pacienteTieneOtraConsulta = fichaRepository.existsByPacienteIdAndFechaConsultaBetween(datos.getPacienteId(), primerHorario, ultimoHorario);
        if (pacienteTieneOtraConsulta){
            throw new RuntimeException("El paciente ya tiene una consulta en el mismo dia");
        }
    }
}
