package mx.edu.uteq.idgs12.academic_ms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs12.academic_ms.dto.DivisionDTO;
import mx.edu.uteq.idgs12.academic_ms.entity.Division;
import mx.edu.uteq.idgs12.academic_ms.repository.DivisionRepository;
import mx.edu.uteq.idgs12.academic_ms.repository.UniversityRepository;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public List<DivisionDTO> getAll() {
        return divisionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

        public List<DivisionDTO> getAllActive() {
        return divisionRepository.findByStatusTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<DivisionDTO> getById(Integer id) {
        return divisionRepository.findById(id)
                .map(this::toDTO);
    }

    private DivisionDTO toDTO(Division division) {
        DivisionDTO dto = new DivisionDTO();
        BeanUtils.copyProperties(division, dto);
        dto.setIdUniversity(division.getUniversity().getIdUniversity());
        return dto;
    }

}
