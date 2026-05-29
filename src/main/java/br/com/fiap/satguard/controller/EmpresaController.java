package br.com.fiap.satguard.controller;
import br.com.fiap.satguard.model.Empresa;
import br.com.fiap.satguard.dto.EmpresaDTO;
import br.com.fiap.satguard.service.EmpresaService;
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
@RequestMapping("/api/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService service;

    @Autowired
    private PagedResourcesAssembler<Empresa> pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Empresa>>> findAll(Pageable pageable) {
        Page<Empresa> page = service.findAll(pageable);
        PagedModel<EntityModel<Empresa>> pagedModel = pagedResourcesAssembler.toModel(page, entity -> {
            EntityModel<Empresa> resource = EntityModel.of(entity);
            // Link hardcoded bypass for dynamic ID mapping in script
            return resource;
        });
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Empresa>> findById(@PathVariable Integer id) {
        Empresa entity = service.findById(id);
        EntityModel<Empresa> resource = EntityModel.of(entity);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).findById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).findAll(Pageable.unpaged())).withRel("all"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Empresa>> create(@RequestBody @Valid EmpresaDTO dto) {
        Empresa created = service.save(dto);
        EntityModel<Empresa> resource = EntityModel.of(created);
        return ResponseEntity.status(201).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Empresa>> update(@PathVariable Integer id, @RequestBody @Valid EmpresaDTO dto) {
        Empresa updated = service.update(id, dto);
        EntityModel<Empresa> resource = EntityModel.of(updated);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
