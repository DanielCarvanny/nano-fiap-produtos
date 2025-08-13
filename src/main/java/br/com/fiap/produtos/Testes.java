package br.com.fiap.produtos;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.CategoriaCollectionRepository;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Testes {
    public static void main(String[] args) {
        // Teste de Erro no cadastro de categoria
        {
            Categoria categoria = new Categoria();
            categoria.setNome("Eletrônicos");

            Categoria categoriaSalva = CategoriaCollectionRepository.save(categoria);

            System.out.println("Categoria: " + categoriaSalva);
        }

        // Teste de Acerto no cadastro de categoria
        {
            Categoria categoria = new Categoria();
            categoria.setNome("Informática");

            Categoria categoriaSalva = CategoriaCollectionRepository.save(categoria);

            System.out.println("Categoria: " + categoriaSalva);
        }

        // Teste de Erro no cadastro de produto
        {
            Produto produto = new Produto();
            produto.setNome("Kindle")
                    .setDescricao("e-reader")
                    .setCategoria(CategoriaCollectionRepository.findByNome("Eletrônicos").get(0))
                    .setDtCadastro(LocalDateTime.now())
                    .setPreco(BigDecimal.valueOf(600));

            Produto produtoSalvo = ProdutoCollectionRepository.save(produto);

            System.out.println("Produto: " + produtoSalvo);
        }

        // Teste de Acerto no cadastro de produto
        {
            Produto produto = new Produto();
            produto.setNome("Iphone 14")
                    .setDescricao("Aparelho celular")
                    .setCategoria(CategoriaCollectionRepository.findByNome("Celulares").getFirst())
                    .setDtCadastro(LocalDateTime.now())
                    .setPreco(BigDecimal.valueOf(12_000));

            Produto produtoSalvo = ProdutoCollectionRepository.save(produto);

            System.out.println("Produto: " + produtoSalvo + " ID: " + produtoSalvo.getId());
        }
    }
}