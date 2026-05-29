package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.DetritoEspacial;
import br.com.fiap.satguard.dto.DetritoEspacialDTO;
import br.com.fiap.satguard.repository.DetritoEspacialRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetritoEspacialService {
    
    private final DetritoEspacialRepository repository;

    public Page<DetritoEspacial> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public DetritoEspacial findById(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("DetritoEspacial nao encontrado")); }
    
    public DetritoEspacial save(DetritoEspacialDTO dto) { 
        DetritoEspacial entity = new DetritoEspacial();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public DetritoEspacial update(Integer id, DetritoEspacialDTO dto) {
        DetritoEspacial entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
