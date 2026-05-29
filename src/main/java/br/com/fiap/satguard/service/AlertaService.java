package br.com.fiap.satguard.service;
import br.com.fiap.satguard.model.Alerta;
import br.com.fiap.satguard.dto.AlertaDTO;
import br.com.fiap.satguard.repository.AlertaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {
    @Autowired
    private AlertaRepository repository;

    public Page<Alerta> findAll(Pageable pageable) { return repository.findAll(pageable); }
    
    public Alerta findById(Integer id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alerta nao encontrado")); }
    
    public Alerta save(AlertaDTO dto) { 
        Alerta entity = new Alerta();
        BeanUtils.copyProperties(dto, entity);
        return repository.save(entity); 
    }
    
    public Alerta update(Integer id, AlertaDTO dto) {
        Alerta entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
