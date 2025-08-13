package br.com.fiap.produtos.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Produto {
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime dtCadastro;

    private Categoria categoria;

    public Produto() {
    }

    // Construtor com o ID para produtos já castradados
    public Produto(Long id, String nome, String descricao, BigDecimal preco,
                   LocalDateTime dtCadastro, Categoria categoria) {
        this.setId(id);
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setPreco(preco);
        this.setDtCadastro(dtCadastro);
        this.setCategoria(categoria);
    }

    // Construtor sem o ID para produtos não castradados
    public Produto(String nome, String descricao, BigDecimal preco,
                   LocalDateTime dtCadastro, Categoria categoria) {
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setPreco(preco);
        this.setDtCadastro(dtCadastro);
        this.setCategoria(categoria);
    }

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public LocalDateTime getDtCadastro() {
        return dtCadastro;
    }

    public Produto setDtCadastro(LocalDateTime dtCadastro) {
        this.dtCadastro = dtCadastro;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public String toString(){
        return nome.toUpperCase();
    }
}
