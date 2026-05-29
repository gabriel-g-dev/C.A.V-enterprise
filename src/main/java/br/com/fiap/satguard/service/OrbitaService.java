package br.com.fiap.satguard.service;
import br.com.fiap.satguard.model.Orbita;
import br.com.fiap.satguard.repository.OrbitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrbitaService {
    @Autowired
    private OrbitaRepository repository;
    public Page<Orbita> findAll(Pageable pageable) { return repository.findAll(pageable); }
    public Orbita findById(Integer id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Orbita nao encontrado")); }
    public Orbita save(Orbita entity) { return repository.save(entity); }
    public void delete(Integer id) { repository.deleteById(id); }
}
