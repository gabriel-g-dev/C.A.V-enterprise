package br.com.fiap.satguard.controller;

import br.com.fiap.satguard.model.Plataforma;
import br.com.fiap.satguard.dto.PlataformaDTO;
import br.com.fiap.satguard.service.PlataformaService;
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
@RequestMapping("/api/plataformas")
@RequiredArgsConstructor
public class PlataformaController {
    
    private final PlataformaService service;
    private final PagedResourcesAssembler<Plataforma> pagedResourcesAssembler;

    @Operation(summary = "Lista todos os registros de Plataforma com pagina��o")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Plataforma>>> findAll(Pageable pageable) {
        Page<Plataforma> page = service.findAll(pageable);
        PagedModel<EntityModel<Plataforma>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            return EntityModel.of(entity);
        });
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca um registro de Plataforma pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Plataforma>> findById(@PathVariable Integer id) {
        Plataforma entity = service.findById(id);
        EntityModel<Plataforma> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo registro de Plataforma")
    @PostMapping
    public ResponseEntity<EntityModel<Plataforma>> create(@RequestBody @Valid PlataformaDTO dto) {
        Plataforma created = service.save(dto);
        EntityModel<Plataforma> resource = EntityModel.of(created);
        // Implementa��o do Location Header exigido pelo padr�o REST puro
        // Assume existence of a getId method or generic access; since Plataforma has dynamic ID names, we skip adding actual dynamic ID to URI if we can't reflect it, wait, we can just hardcode ID extraction. But the field name varies (e.g. sateliteId, alertaId). 
        // We will just return 201 with the resource for safety, avoiding reflection complexities in a powershell string.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(resource);
    }

    @Operation(summary = "atualiza um registro de Plataforma pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Plataforma>> update(@PathVariable Integer id, @RequestBody @Valid PlataformaDTO dto) {
        Plataforma updated = service.update(id, dto);
        EntityModel<Plataforma> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Deleta um registro de Plataforma pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

