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
@Table(name = "TG_Usuario")
public class UsuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id_Usuario;
	
	@Column(name = "nm_usuario", nullable = false)
	private String nm_Usuario;
	
	@Column(name = "nr_cpf", unique = true, nullable = false)
	private Long nr_Cpf;
	
	@Column(name = "nm_rg", nullable = true)
	private String nm_Rg;
	
	@Column(name = "dt_nascimento", nullable = true)
	private LocalDate dt_Nascimento;
	
	@Column(name = "nm_email", nullable = false)
	private String nm_Email;
	
	@Column(name = "nm_senha", nullable = false)
	private String nm_Senha;
	
	@Column(name = "dt_cadastro", nullable = true)
	private LocalDate dt_Cadastro;
	
	
	
	
	
	
	

}
