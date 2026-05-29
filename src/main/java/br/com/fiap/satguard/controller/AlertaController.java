package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.Alerta;
import br.com.fiap.satguard.dto.AlertaDTO;
import br.com.fiap.satguard.service.AlertaService;
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
@RequestMapping("/api/alertas")
public class AlertaController {
    @Autowired
    private AlertaService service;

    @Autowired
    private PagedResourcesAssembler<Alerta> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Alerta>>> findAll(Pageable pageable) {
        Page<Alerta> page = service.findAll(pageable);
        PagedModel<EntityModel<Alerta>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<Alerta> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> findById(@PathVariable Integer id) {
        Alerta entity = service.findById(id);
        EntityModel<Alerta> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Alerta>> create(@RequestBody @Valid AlertaDTO dto) {
        Alerta created = service.save(dto);
        EntityModel<Alerta> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> update(@PathVariable Integer id, @RequestBody @Valid AlertaDTO dto) {
        Alerta updated = service.update(id, dto);
        EntityModel<Alerta> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlertaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
