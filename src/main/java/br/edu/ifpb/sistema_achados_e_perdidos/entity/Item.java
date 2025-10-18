package br.edu.ifpb.sistema_achados_e_perdidos.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "TB_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    @NotBlank(message = "O nome do item é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A categoria do item é obrigatória")
    @Column(nullable = false)
    private String categoria;

    @NotBlank(message = "O local do item é obrigatório")
    @Column(nullable = false)
    private String local;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Criacao", nullable = false)
    private Date dataEncontrado;

    @NotBlank(message = "A foto do item é obrigatória")
    @Column(nullable = false)
    private String fotoDoItem;

    @NotBlank(message = "O status do item é obrigatório")
    @Column(nullable = false)
    private String status;

    @NotBlank(message = "A descrição do item é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			optional = false)
	private Usuario usuario;
  
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getFotoDoItem() {
        return fotoDoItem;
    }
    public void setFotoDoItem(String fotoDoItem) {
        this.fotoDoItem = fotoDoItem;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getDataEncontrado() {
        return dataEncontrado;
    }
    public void setDataEncontrado(Date dataEncontrado) {
        this.dataEncontrado = dataEncontrado;
    }
    
}
