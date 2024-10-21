package uagrm.bo.workflow.exceptions;

public class HorarioNotFoundException extends RuntimeException {


    public HorarioNotFoundException(Long id) {
        super("Horario no encontrado: " + id);
    }
}
