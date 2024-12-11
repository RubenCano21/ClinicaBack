package uagrm.bo.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uagrm.bo.workflow.dto.IntervaloHorarioDTO;
import uagrm.bo.workflow.dto.PageResponseDTO;
import uagrm.bo.workflow.model.IntervalosHorario;
import uagrm.bo.workflow.service.IntervaloHorarioService;

import java.util.List;

@RestController
@RequestMapping("/api/intervalo-horarios")
public class IntervaloHorarioController {

    @Autowired
    private IntervaloHorarioService intervaloHorarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<IntervaloHorarioDTO>> listar() {
        return new ResponseEntity<>(intervaloHorarioService.listarIntervalosHorarios(), HttpStatus.OK);
    }
}
