package uagrm.bo.workflow.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uagrm.bo.workflow.service.PacienteService;

@Component
public class ExistsByEmailValidation implements ConstraintValidator<ExistsByEmail, String> {

    @Autowired
    private PacienteService pacienteService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
       return email != null && pacienteService.existsByEmail(email);
    }
    
    
}
