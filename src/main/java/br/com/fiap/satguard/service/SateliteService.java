package br.com.fiap.satguard.service;
import br.com.fiap.satguard.model.Satelite;
import br.com.fiap.satguard.dto.SateliteDTO;
import br.com.fiap.satguard.repository.SateliteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SateliteService {
    @Autowired
    private SateliteRepository repository;

    public Page<Satelite> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Satelite findById(Integer id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Satelite nao encontrado")); }
    
    public Satelite save(SateliteDTO dto) { 
        Satelite entity = new Satelite();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public Satelite update(Integer id, SateliteDTO dto) {
        Satelite entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
