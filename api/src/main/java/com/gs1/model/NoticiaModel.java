package com.gs1.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tg_Noticia")
public class NoticiaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="id_noticia")
	private Long id_Noticia;
	
	@Column(name = "nm_titulo", nullable = false)
	private String nm_Titulo;
	
	@Column(name = "nm_subtitulo", nullable = false)
	private String nm_Subtitulo;
	
	@Column(name = "dt_noticia", nullable = false)
	private LocalDate dt_Noticia;
	
	@Column(name = "ds_imagem", nullable = false)
	private String ds_Imagem;
	
	@Column(name = "ds_link", nullable = false)	
	private String ds_Link;
	

}
