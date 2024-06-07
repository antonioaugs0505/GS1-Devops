package com.gs1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gs1.assembler.NoticiaModelAssembler;
import com.gs1.model.NoticiaModel;
import com.gs1.service.NoticiaService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private NoticiaModelAssembler assembler;

    @PostMapping
    public CollectionModel<EntityModel<NoticiaModel>> createNoticias(@Validated @RequestBody List<NoticiaModel> noticias) {
        List<EntityModel<NoticiaModel>> noticiaModels = noticiaService.createNoticias(noticias).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(noticiaModels,
                linkTo(methodOn(NoticiaController.class).getAllNoticias()).withSelfRel());
    }


    @GetMapping
    public CollectionModel<EntityModel<NoticiaModel>> getAllNoticias() {
        List<EntityModel<NoticiaModel>> noticias = noticiaService.getAllNoticias().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(noticias,
                linkTo(methodOn(NoticiaController.class).getAllNoticias()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<NoticiaModel>> getNoticiaById(@PathVariable Long id) {
        return noticiaService.getNoticiaById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<NoticiaModel>> updateNoticia(@PathVariable Long id, @RequestBody NoticiaModel noticiaDetails) {
        return noticiaService.updateNoticia(id, noticiaDetails)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Long id) {
        if (noticiaService.deleteNoticia(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
