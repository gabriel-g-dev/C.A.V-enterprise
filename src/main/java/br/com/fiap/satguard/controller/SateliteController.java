package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.Satelite;
import br.com.fiap.satguard.dto.SateliteDTO;
import br.com.fiap.satguard.service.SateliteService;
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
@RequestMapping("/api/satelites")
public class SateliteController {
    @Autowired
    private SateliteService service;

    @Autowired
    private PagedResourcesAssembler<Satelite> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Satelite>>> findAll(Pageable pageable) {
        Page<Satelite> page = service.findAll(pageable);
        PagedModel<EntityModel<Satelite>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<Satelite> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Satelite>> findById(@PathVariable Integer id) {
        Satelite entity = service.findById(id);
        EntityModel<Satelite> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Satelite>> create(@RequestBody @Valid SateliteDTO dto) {
        Satelite created = service.save(dto);
        EntityModel<Satelite> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Satelite>> update(@PathVariable Integer id, @RequestBody @Valid SateliteDTO dto) {
        Satelite updated = service.update(id, dto);
        EntityModel<Satelite> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SateliteController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
