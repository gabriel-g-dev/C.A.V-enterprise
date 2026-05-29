package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.Empresa;
import br.com.fiap.satguard.dto.EmpresaDTO;
import br.com.fiap.satguard.repository.EmpresaRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    
    private final EmpresaRepository repository;

    public Page<Empresa> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Empresa findById(Integer id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa nao encontrado")); }
    
    public Empresa save(EmpresaDTO dto) { 
        Empresa entity = new Empresa();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public Empresa update(Integer id, EmpresaDTO dto) {
        Empresa entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
