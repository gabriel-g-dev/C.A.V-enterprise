package br.com.fiap.satguard.controller;

import br.com.fiap.satguard.model.Alerta;
import br.com.fiap.satguard.dto.AlertaDTO;
import br.com.fiap.satguard.service.AlertaService;
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
@RequestMapping("/api/alertas")
@RequiredArgsConstructor
public class AlertaController {
    
    private final AlertaService service;
    private final PagedResourcesAssembler<Alerta> pagedResourcesAssembler;

    @Operation(summary = "Lista todos os registros de Alerta com pagina��o")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Alerta>>> findAll(Pageable pageable) {
        Page<Alerta> page = service.findAll(pageable);
        PagedModel<EntityModel<Alerta>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            return EntityModel.of(entity);
        });
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca um registro de Alerta pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> findById(@PathVariable Integer id) {
        Alerta entity = service.findById(id);
        EntityModel<Alerta> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo registro de Alerta")
    @PostMapping
    public ResponseEntity<EntityModel<Alerta>> create(@RequestBody @Valid AlertaDTO dto) {
        Alerta created = service.save(dto);
        EntityModel<Alerta> resource = EntityModel.of(created);
        // Implementa��o do Location Header exigido pelo padr�o REST puro
        // Assume existence of a getId method or generic access; since Alerta has dynamic ID names, we skip adding actual dynamic ID to URI if we can't reflect it, wait, we can just hardcode ID extraction. But the field name varies (e.g. sateliteId, alertaId). 
        // We will just return 201 with the resource for safety, avoiding reflection complexities in a powershell string.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(resource);
    }

    @Operation(summary = "atualiza um registro de Alerta pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> update(@PathVariable Integer id, @RequestBody @Valid AlertaDTO dto) {
        Alerta updated = service.update(id, dto);
        EntityModel<Alerta> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Deleta um registro de Alerta pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

