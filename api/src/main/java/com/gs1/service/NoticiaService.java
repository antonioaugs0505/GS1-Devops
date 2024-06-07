package com.gs1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gs1.model.NoticiaModel;
import com.gs1.repository.NoticiaRepository;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public NoticiaModel createNoticia(NoticiaModel noticia) {
        return noticiaRepository.save(noticia);
    }

    public List<NoticiaModel> createNoticias(List<NoticiaModel> noticias) {
        return noticiaRepository.saveAll(noticias);
    }

    public List<NoticiaModel> getAllNoticias() {
        return noticiaRepository.findAll();
    }

    public Optional<NoticiaModel> getNoticiaById(Long id) {
        return noticiaRepository.findById(id);
    }

    public Optional<NoticiaModel> updateNoticia(Long id, NoticiaModel noticiaDetails) {
        return noticiaRepository.findById(id).map(noticia -> {
            noticia.setNm_Titulo(noticiaDetails.getNm_Titulo());
            noticia.setNm_Subtitulo(noticiaDetails.getNm_Subtitulo());
            noticia.setDt_Noticia(noticiaDetails.getDt_Noticia());
            noticia.setDs_Imagem(noticiaDetails.getDs_Imagem());
            noticia.setDs_Link(noticiaDetails.getDs_Link());
            return noticiaRepository.save(noticia);
        });
    }

    public boolean deleteNoticia(Long id) {
        return noticiaRepository.findById(id).map(noticia -> {
            noticiaRepository.delete(noticia);
            return true;
        }).orElse(false);
    }
}
