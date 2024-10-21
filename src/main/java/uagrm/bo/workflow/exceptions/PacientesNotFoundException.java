package uagrm.bo.workflow.exceptions;

public class PacientesNotFoundException extends RuntimeException {

    public PacientesNotFoundException(Long id) {
        super("Paciente no encontrado: " + id);
    }
}
