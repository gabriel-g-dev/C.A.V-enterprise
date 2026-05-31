package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.Empresa;
import br.com.fiap.satguard.dto.EmpresaDTO;
import br.com.fiap.satguard.repository.EmpresaRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import br.com.fiap.satguard.model.Endereco;
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
        
        Endereco endereco = new Endereco();
        endereco.setEnderecoRua(dto.enderecoRua());
        endereco.setEnderecoCidade(dto.enderecoCidade());
        endereco.setEnderecoEstado(dto.enderecoEstado());
        endereco.setEnderecoCep(dto.enderecoCep());
        entity.setEndereco(endereco);

        return repository.save(entity); 
    }
    
    public Empresa update(Integer id, EmpresaDTO dto) {
        Empresa entity = findById(id);
        BeanUtils.copyProperties(dto, entity, "id");
        
        if(entity.getEndereco() == null) {
            entity.setEndereco(new Endereco());
        }
        entity.getEndereco().setEnderecoRua(dto.enderecoRua());
        entity.getEndereco().setEnderecoCidade(dto.enderecoCidade());
        entity.getEndereco().setEnderecoEstado(dto.enderecoEstado());
        entity.getEndereco().setEnderecoCep(dto.enderecoCep());

        return repository.save(entity);
    }
    
    public void delete(Integer id) { repository.deleteById(id); }
}
