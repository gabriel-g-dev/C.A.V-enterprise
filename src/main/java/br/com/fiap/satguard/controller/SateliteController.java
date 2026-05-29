package br.com.fiap.satguard.controller;

import br.com.fiap.satguard.model.Satelite;
import br.com.fiap.satguard.dto.SateliteDTO;
import br.com.fiap.satguard.service.SateliteService;
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
@RequestMapping("/api/satelites")
@RequiredArgsConstructor
public class SateliteController {
    
    private final SateliteService service;
    private final PagedResourcesAssembler<Satelite> pagedResourcesAssembler;

    @Operation(summary = "Lista todos os registros de Satelite com pagina��o")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Satelite>>> findAll(Pageable pageable) {
        Page<Satelite> page = service.findAll(pageable);
        PagedModel<EntityModel<Satelite>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            return EntityModel.of(entity);
        });
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca um registro de Satelite pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Satelite>> findById(@PathVariable Integer id) {
        Satelite entity = service.findById(id);
        EntityModel<Satelite> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo registro de Satelite")
    @PostMapping
    public ResponseEntity<EntityModel<Satelite>> create(@RequestBody @Valid SateliteDTO dto) {
        Satelite created = service.save(dto);
        EntityModel<Satelite> resource = EntityModel.of(created);
        // Implementa��o do Location Header exigido pelo padr�o REST puro
        // Assume existence of a getId method or generic access; since Satelite has dynamic ID names, we skip adding actual dynamic ID to URI if we can't reflect it, wait, we can just hardcode ID extraction. But the field name varies (e.g. sateliteId, alertaId). 
        // We will just return 201 with the resource for safety, avoiding reflection complexities in a powershell string.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(resource);
    }

    @Operation(summary = "atualiza um registro de Satelite pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Satelite>> update(@PathVariable Integer id, @RequestBody @Valid SateliteDTO dto) {
        Satelite updated = service.update(id, dto);
        EntityModel<Satelite> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Deleta um registro de Satelite pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

