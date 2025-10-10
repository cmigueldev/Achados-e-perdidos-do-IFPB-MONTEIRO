package br.edu.ifpb.sistema_achados_e_perdidos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O nome do usuario é obrigatório")
	@Column(nullable = false)
    private String nome;

    @NotBlank(message = "O email do usuario é obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A matrícula do usuario é obrigatória")
	@Column(unique = true, nullable = false)
    private String matricula;

    @NotBlank(message = "A senha do usuario é obrigatória")
    @Column(nullable = false)
    private String senha;

    @NotBlank(message = "O telefone do usuario é obrigatório")
    @Column(nullable = false,unique = true)
    private String telefone;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



}


