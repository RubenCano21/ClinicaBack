package uagrm.bo.workflow.exceptions;

public class EspecialidadNotFoundException extends RuntimeException {

    public EspecialidadNotFoundException(Long id) {
        super("Especialidad con " + id + " no fue encontrada");
    }
}
