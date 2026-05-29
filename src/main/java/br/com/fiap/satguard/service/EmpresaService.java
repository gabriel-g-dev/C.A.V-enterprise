package br.com.fiap.satguard.service;
import br.com.fiap.satguard.model.Empresa;
import br.com.fiap.satguard.dto.EmpresaDTO;
import br.com.fiap.satguard.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public Page<Empresa> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Empresa findById(Integer id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empresa nao encontrado")); }
    
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
