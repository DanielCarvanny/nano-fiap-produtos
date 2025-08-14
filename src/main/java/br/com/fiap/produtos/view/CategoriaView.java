package br.com.fiap.produtos.view;

import br.com.fiap.produtos.model.Categoria;
import br.com.fiap.produtos.model.Produto;
import br.com.fiap.produtos.repository.CategoriaCollectionRepository;

import javax.swing.*;

public class CategoriaView {
    static CategoriaCollectionRepository repository;

    public static Categoria select(Categoria categoria){
        try {
            return (Categoria) JOptionPane.showInputDialog(
                    null,
                    "Selecione uma categoria",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    repository.findAll().toArray(),
                    categoria == null ? 1 : categoria
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar categoria: " + e.getMessage());
            return null;
        }
    }

    public static Categoria form(Categoria categoria){
        String nome = null;
        do {
            nome = JOptionPane.showInputDialog(null,
                    "Informe o nome da categoria: ",
                    categoria.getNome() != null ?  categoria.getNome() : "");

            if (nome == null) { // Usuário clicou em Cancelar
                return null; // Encerra todo o formulário
            }
        }while (nome.trim().isEmpty());
        return new Categoria(nome);
    }
}
