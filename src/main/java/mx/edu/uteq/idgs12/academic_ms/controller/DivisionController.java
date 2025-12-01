package mx.edu.uteq.idgs12.academic_ms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs12.academic_ms.dto.DivisionDTO;
import mx.edu.uteq.idgs12.academic_ms.service.DivisionService;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping
    public List<DivisionDTO> getAll() {
        return divisionService.getAll();
    }

    @GetMapping("/active")
    public List<DivisionDTO> getAllActive() {
        return divisionService.getAllActive();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisionDTO> getById(@PathVariable Integer id) {
        Optional<DivisionDTO> division = divisionService.getById(id);
        return division.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/university/{idUniversity}")
    public List<DivisionDTO> getByUniversity(@PathVariable Integer idUniversity) {
        return divisionService.getByUniversity(idUniversity);
    }
}
