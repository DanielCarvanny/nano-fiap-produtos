package br.com.fiap.produtos.view;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoView {

    static ProdutoCollectionRepository repository;

    public static Produto form(Produto produto) {
        if (produto == null) {
            produto = new Produto();
        }

        // Categoria
        Categoria categoria = null;
        do {

            categoria = CategoriaView.select(produto.getCategoria());

            if (categoria == null){
                return null;
            }

        }while (categoria == null);

        // Nome
        String nome = null;
        do {
            nome = JOptionPane.showInputDialog(
                    null,
                    "Informe o nome do produto:",
                    produto.getNome() != null ? produto.getNome() : ""
            );

            if (nome == null) { // Usuário clicou em Cancelar
                return null; // Encerra todo o formulário
            }
        } while (nome.trim().isEmpty());

        // Descrição
        String descricao = null;
        do {
            descricao = JOptionPane.showInputDialog(
                    null,
                    "Informe a descrição do produto:",
                    produto.getDescricao() != null ? produto.getDescricao() : ""
            );

            if (descricao == null) { // Usuário clicou em Cancelar
                return null; // Encerra todo o formulário
            }
        } while (descricao.trim().isEmpty());

        // Preço
        double preco = 0;
        do {

            try {
                preco = Double.parseDouble(JOptionPane.showInputDialog(null,
                        "Informe o preço do produto:", produto.getPreco()));
            }catch (Exception e){
                preco = 0;
            }

        }while(preco <= 0);

        Produto ret = produto;

        ret.setNome(nome)
                .setCategoria(categoria)
                .setDescricao(descricao)
                .setDtCadastro(LocalDateTime.now())
                .setPreco(BigDecimal.valueOf(preco));

        return ret;
    }

    public static Produto select(Produto produto) {
        try {
            return (Produto) JOptionPane.showInputDialog(
                    null,
                    "Selecione um produto",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    repository.findAll().toArray(),
                    produto == null ? 1 : produto
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar produto: " + e.getMessage());
            return null;
        }
    }

    public static void update(Produto produto){
        if (form(produto) != null){
            JOptionPane.showMessageDialog(
                    null,
                    "Produto alterado com sucesso!"
            );
            show(produto);
        }else{
            System.out.println("Operação cancelada pelo usuário");
            JOptionPane.showMessageDialog(null, "Operação cancelada",
                    "Operação cancelada",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void show(Produto produto) {
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Produto inválido!");
        }else {
            String textoFormatado = String.format(
                    "ID: %d\n" +
                            "Nome: %s\n" +
                            "Descrição: %s\n" +
                            "Preço: R$%,.2f\n" +
                            "Categoria: %s\n" +
                            "Data de Cadastro: %s",
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getCategoria() != null ? produto.getCategoria().getNome() : "Nenhuma",
                    produto.getDtCadastro()
            );

            JOptionPane.showMessageDialog(null, textoFormatado);
        }
    }

}
