package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.Orbita;
import br.com.fiap.satguard.dto.OrbitaDTO;
import br.com.fiap.satguard.repository.OrbitaRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrbitaService {
    
    private final OrbitaRepository repository;

    public Page<Orbita> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Orbita findById(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Orbita nao encontrado")); }
    
    public Orbita save(OrbitaDTO dto) { 
        Orbita entity = new Orbita();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public Orbita update(Integer id, OrbitaDTO dto) {
        Orbita entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
