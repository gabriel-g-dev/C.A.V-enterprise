package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.Plataforma;
import br.com.fiap.satguard.dto.PlataformaDTO;
import br.com.fiap.satguard.service.PlataformaService;
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
@RequestMapping("/api/plataformas")
public class PlataformaController {
    @Autowired
    private PlataformaService service;

    @Autowired
    private PagedResourcesAssembler<Plataforma> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Plataforma>>> findAll(Pageable pageable) {
        Page<Plataforma> page = service.findAll(pageable);
        PagedModel<EntityModel<Plataforma>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<Plataforma> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Plataforma>> findById(@PathVariable Integer id) {
        Plataforma entity = service.findById(id);
        EntityModel<Plataforma> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Plataforma>> create(@RequestBody @Valid PlataformaDTO dto) {
        Plataforma created = service.save(dto);
        EntityModel<Plataforma> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Plataforma>> update(@PathVariable Integer id, @RequestBody @Valid PlataformaDTO dto) {
        Plataforma updated = service.update(id, dto);
        EntityModel<Plataforma> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlataformaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
