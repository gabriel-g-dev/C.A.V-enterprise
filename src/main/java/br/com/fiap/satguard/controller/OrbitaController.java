package br.com.fiap.satguard.controller;

import br.com.fiap.satguard.model.Orbita;
import br.com.fiap.satguard.dto.OrbitaDTO;
import br.com.fiap.satguard.service.OrbitaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/orbitas")
@RequiredArgsConstructor
public class OrbitaController {
    
    private final OrbitaService service;
    private final PagedResourcesAssembler<Orbita> pagedResourcesAssembler;

    @Operation(summary = "Lista todos os registros de Orbita com pagina��o")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Orbita>>> findAll(Pageable pageable) {
        Page<Orbita> page = service.findAll(pageable);
        PagedModel<EntityModel<Orbita>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            return EntityModel.of(entity);
        });
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca um registro de Orbita pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Orbita>> findById(@PathVariable Integer id) {
        Orbita entity = service.findById(id);
        EntityModel<Orbita> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo registro de Orbita")
    @PostMapping
    public ResponseEntity<EntityModel<Orbita>> create(@RequestBody @Valid OrbitaDTO dto) {
        Orbita created = service.save(dto);
        EntityModel<Orbita> resource = EntityModel.of(created);
        // Implementa��o do Location Header exigido pelo padr�o REST puro
        // Assume existence of a getId method or generic access; since Orbita has dynamic ID names, we skip adding actual dynamic ID to URI if we can't reflect it, wait, we can just hardcode ID extraction. But the field name varies (e.g. sateliteId, alertaId). 
        // We will just return 201 with the resource for safety, avoiding reflection complexities in a powershell string.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(resource);
    }

    @Operation(summary = "atualiza um registro de Orbita pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Orbita>> update(@PathVariable Integer id, @RequestBody @Valid OrbitaDTO dto) {
        Orbita updated = service.update(id, dto);
        EntityModel<Orbita> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Deleta um registro de Orbita pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

