package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.DetritoEspacial;
import br.com.fiap.satguard.dto.DetritoEspacialDTO;
import br.com.fiap.satguard.service.DetritoEspacialService;
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
@RequestMapping("/api/detritoespacials")
public class DetritoEspacialController {
    @Autowired
    private DetritoEspacialService service;

    @Autowired
    private PagedResourcesAssembler<DetritoEspacial> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DetritoEspacial>>> findAll(Pageable pageable) {
        Page<DetritoEspacial> page = service.findAll(pageable);
        PagedModel<EntityModel<DetritoEspacial>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<DetritoEspacial> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DetritoEspacial>> findById(@PathVariable Integer id) {
        DetritoEspacial entity = service.findById(id);
        EntityModel<DetritoEspacial> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<DetritoEspacial>> create(@RequestBody @Valid DetritoEspacialDTO dto) {
        DetritoEspacial created = service.save(dto);
        EntityModel<DetritoEspacial> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DetritoEspacial>> update(@PathVariable Integer id, @RequestBody @Valid DetritoEspacialDTO dto) {
        DetritoEspacial updated = service.update(id, dto);
        EntityModel<DetritoEspacial> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DetritoEspacialController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
