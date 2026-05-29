package br.com.fiap.satguard.service;
import br.com.fiap.satguard.model.DetritoEspacial;
import br.com.fiap.satguard.repository.DetritoEspacialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetritoEspacialService {
    @Autowired
    private DetritoEspacialRepository repository;
    public Page<DetritoEspacial> findAll(Pageable pageable) { return repository.findAll(pageable); }
    public DetritoEspacial findById(Integer id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("DetritoEspacial nao encontrado")); }
    public DetritoEspacial save(DetritoEspacial entity) { return repository.save(entity); }
    public void delete(Integer id) { repository.deleteById(id); }
}
