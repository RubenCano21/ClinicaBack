package uagrm.bo.workflow.dto.validaciones;

import uagrm.bo.workflow.dto.DatosReservaFicha;
import uagrm.bo.workflow.model.Especialidad;
import uagrm.bo.workflow.model.Medico;

public interface ValidadorDeFichas {

    void validar(DatosReservaFicha datos);
}
