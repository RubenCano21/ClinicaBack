package uagrm.bo.workflow.dto.validaciones;

import org.springframework.stereotype.Component;
import uagrm.bo.workflow.dto.DatosReservaFicha;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarFichaConAnticipacion implements ValidadorDeFichas {

    public void validar(DatosReservaFicha datos) {
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

        if (diferenciaEnMinutos < 30) {
            throw new RuntimeException("La ficha debe ser reservada con al menos 30 minutos de anticipacion");
        }

    }
}
