package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.Orbita;
import br.com.fiap.satguard.dto.OrbitaDTO;
import br.com.fiap.satguard.service.OrbitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orbitas")
public class OrbitaController {
    @Autowired
    private OrbitaService service;

    @Autowired
    private PagedResourcesAssembler<Orbita> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Orbita>>> findAll(Pageable pageable) {
        Page<Orbita> page = service.findAll(pageable);
        PagedModel<EntityModel<Orbita>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<Orbita> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Orbita>> findById(@PathVariable Integer id) {
        Orbita entity = service.findById(id);
        EntityModel<Orbita> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Orbita>> create(@RequestBody @Valid OrbitaDTO dto) {
        Orbita created = service.save(dto);
        EntityModel<Orbita> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Orbita>> update(@PathVariable Integer id, @RequestBody @Valid OrbitaDTO dto) {
        Orbita updated = service.update(id, dto);
        EntityModel<Orbita> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrbitaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
