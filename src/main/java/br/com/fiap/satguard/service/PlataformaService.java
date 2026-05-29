package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.Plataforma;
import br.com.fiap.satguard.dto.PlataformaDTO;
import br.com.fiap.satguard.repository.PlataformaRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlataformaService {
    
    private final PlataformaRepository repository;

    public Page<Plataforma> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Plataforma findById(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plataforma nao encontrado")); }
    
    public Plataforma save(PlataformaDTO dto) { 
        Plataforma entity = new Plataforma();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public Plataforma update(Integer id, PlataformaDTO dto) {
        Plataforma entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
