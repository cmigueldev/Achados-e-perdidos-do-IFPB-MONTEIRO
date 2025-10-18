package br.edu.ifpb.sistema_achados_e_perdidos.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
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

    @NotBlank(message = "O telefone do usuario é obrigatório")
    @Column(nullable = false,unique = true)
    private String telefone;
 
    @OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "usuario")
	private List<Item> itens;

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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Item> getItens() {
        return itens;
    }
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}

