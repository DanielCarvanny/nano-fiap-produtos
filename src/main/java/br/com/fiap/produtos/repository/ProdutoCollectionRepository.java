package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.model.Produto;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

public class ProdutoCollectionRepository {
    private static List<Produto> produtos;

    // Bloco de inicialização de produtos
    static { // Bloco estático - executa quando a classe é carregada
        produtos = new Vector<>();

        Produto kindle = new Produto();

        kindle.setNome("Kindle")
                .setDescricao("e-reader")
                .setCategoria(CategoriaCollectionRepository.findByNome("Eletrônicos").get(0))
                .setDtCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(600));

        produtos.add(kindle);
    }

    public static List<Produto> findAll() {
        return produtos;
    }

    public static Produto findById(long id){
        return produtos.stream().filter(p -> p.equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Produto> findByNome(String nome){
        return produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome))
                .toList();
    }

    public static Produto save(Produto produto){
        if(!produtos.contains(produto)){
            produto.setId((long) produtos.size() + 1);
            produtos.add(produto);
            JOptionPane.showMessageDialog(
                    null,
                    "Produto cadastrado com sucesso!"
            );
            return produto;
        }else {
            JOptionPane.showMessageDialog(
                    null,
                    "Já existe um produto com esse nome!"
            );
            return null;
        }
    }
}
