package com.gs1.assembler;

import com.gs1.controller.NoticiaController;
import com.gs1.model.NoticiaModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NoticiaModelAssembler implements RepresentationModelAssembler<NoticiaModel, EntityModel<NoticiaModel>> {

    @Override
    public EntityModel<NoticiaModel> toModel(NoticiaModel noticia) {
        return EntityModel.of(noticia,
                linkTo(methodOn(NoticiaController.class).getNoticiaById(noticia.getId_Noticia())).withSelfRel(),
                linkTo(methodOn(NoticiaController.class).getAllNoticias()).withRel("noticias"));
    }
}
