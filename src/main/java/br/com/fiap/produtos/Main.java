package br.com.fiap.produtos;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.CategoriaCollectionRepository;
import br.com.fiap.produtos.repository.ProdutoCollectionRepository;
import br.com.fiap.produtos.view.CategoriaView;
import br.com.fiap.produtos.view.Opcao;
import br.com.fiap.produtos.view.OpcaoView;
import br.com.fiap.produtos.view.ProdutoView;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Opcao op = null;

        do{
            op = OpcaoView.select();
            switch (op){
                case CADRASTRAR_CATEGORIA -> createCategoria();
                case CADASTRAR_PRODUTO -> createProduto();
                case CONSULTAR_PRODUTO_POR_ID -> getProdutoById();
                case CONSULTAR_PRODUTO_POR_CATEGORIA -> getProdutoByCategoria();
                case ALTERAR_PRODUTO -> updateProduto();
                case ENCERRAR_SISTEMA -> encerrarSistema();
            }

        }while (op != Opcao.ENCERRAR_SISTEMA);
    }

    private static void encerrarSistema() {
        System.exit(0);
    }

    private static void updateProduto() {
        Produto produto = ProdutoView.select(null);
        ProdutoView.update(produto);
    }

    private static void getProdutoByCategoria() {
        Categoria categoria = CategoriaView.select(null);

        List<Produto> produtos = ProdutoCollectionRepository.findByCategoria(categoria);
        try{
            if (produtos.isEmpty()) {
                System.out.println("Vazio");
                JOptionPane.showMessageDialog(null,
                        "Não há produtos na categoria: " + categoria.getNome());
            } else {
                produtos.forEach(ProdutoView::show);
                produtos.forEach(System.out::println);
            }
        }catch (NullPointerException e){
            System.out.println("Operação cancelada pelo usuário");
            JOptionPane.showMessageDialog(null, "Operação cancelada",
                    "Operação cancelada",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    private static void getProdutoById() {
        try {
            // Solicita o ID do produto ao usuário
            String input = JOptionPane.showInputDialog(
                    null,
                    "Digite o ID do produto que deseja consultar:",
                    "Consulta de Produto por ID",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Se o usuário clicou em cancelar
            if (input == null) {
                return;
            }

            // Converte o input para Long
            Long id = Long.parseLong(input);

            // Busca o produto no repositório
            Produto produto = ProdutoCollectionRepository.findById(id);

            // Exibe o resultado
            if (produto != null) {
                JOptionPane.showMessageDialog(null, "Produto Encontrado");
                ProdutoView.show(produto);
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Nenhum produto encontrado com o ID " + id,
                        "Produto Não Encontrado",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "ID inválido! Por favor, digite apenas números.",
                    "Erro de Formato",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void createProduto() {
        Produto produto = ProdutoView.form(new Produto());
        if (produto == null) {
            System.out.println("Operação cancelada pelo usuário");
        } else {
            ProdutoCollectionRepository.save(produto);
        }
    }

    private static void createCategoria() {
        CategoriaView view = new CategoriaView();
        Categoria categoria = view.form(new Categoria());
        if (categoria == null) {
            System.out.println("Operação cancelada pelo usuário");
        } else {
            CategoriaCollectionRepository.save(categoria);
        }
    }
}