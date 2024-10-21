package uagrm.bo.workflow.exceptions;

public class MedicosNotFoundException extends RuntimeException {

    public MedicosNotFoundException(Long id) {
        super("Medico con " + id + " no encontrado");
    }
}
