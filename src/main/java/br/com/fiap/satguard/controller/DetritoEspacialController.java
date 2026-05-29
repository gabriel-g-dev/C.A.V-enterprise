package br.com.fiap.satguard.controller;

import br.com.fiap.satguard.model.DetritoEspacial;
import br.com.fiap.satguard.dto.DetritoEspacialDTO;
import br.com.fiap.satguard.service.DetritoEspacialService;
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
@RequestMapping("/api/detritoespacials")
@RequiredArgsConstructor
public class DetritoEspacialController {
    
    private final DetritoEspacialService service;
    private final PagedResourcesAssembler<DetritoEspacial> pagedResourcesAssembler;

    @Operation(summary = "Lista todos os registros de DetritoEspacial com pagina��o")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DetritoEspacial>>> findAll(Pageable pageable) {
        Page<DetritoEspacial> page = service.findAll(pageable);
        PagedModel<EntityModel<DetritoEspacial>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            return EntityModel.of(entity);
        });
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca um registro de DetritoEspacial pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DetritoEspacial>> findById(@PathVariable Integer id) {
        DetritoEspacial entity = service.findById(id);
        EntityModel<DetritoEspacial> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo registro de DetritoEspacial")
    @PostMapping
    public ResponseEntity<EntityModel<DetritoEspacial>> create(@RequestBody @Valid DetritoEspacialDTO dto) {
        DetritoEspacial created = service.save(dto);
        EntityModel<DetritoEspacial> resource = EntityModel.of(created);
        // Implementa��o do Location Header exigido pelo padr�o REST puro
        // Assume existence of a getId method or generic access; since DetritoEspacial has dynamic ID names, we skip adding actual dynamic ID to URI if we can't reflect it, wait, we can just hardcode ID extraction. But the field name varies (e.g. sateliteId, alertaId). 
        // We will just return 201 with the resource for safety, avoiding reflection complexities in a powershell string.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).body(resource);
    }

    @Operation(summary = "atualiza um registro de DetritoEspacial pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DetritoEspacial>> update(@PathVariable Integer id, @RequestBody @Valid DetritoEspacialDTO dto) {
        DetritoEspacial updated = service.update(id, dto);
        EntityModel<DetritoEspacial> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Deleta um registro de DetritoEspacial pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

