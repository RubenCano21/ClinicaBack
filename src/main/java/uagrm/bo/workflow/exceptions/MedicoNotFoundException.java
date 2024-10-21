package uagrm.bo.workflow.exceptions;

import uagrm.bo.workflow.model.Medico;

public class MedicoNotFoundException extends RuntimeException {

    public MedicoNotFoundException(String message){
        super(message);
    }

    // Constructor que permite pasar tanto el mensaje como la causa
    public MedicoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
