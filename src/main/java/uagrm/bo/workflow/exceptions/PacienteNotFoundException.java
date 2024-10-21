package uagrm.bo.workflow.exceptions;

public class PacienteNotFoundException extends RuntimeException{

    public PacienteNotFoundException(String message){
        super(message);
    }

    // Constructor que permite pasar tanto el mensaje como la causa
    public PacienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
