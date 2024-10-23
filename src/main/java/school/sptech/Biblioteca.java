package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca() {
    }

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro){
        if (livro == null  | livro.getTitulo().isBlank() |
                livro.getTitulo() == null |
                livro.getTitulo().isEmpty()
                | livro.getAutor().isBlank() |
                livro.getAutor().isEmpty() |
                livro.getAutor() == null |
                livro.getDataPublicacao() == null){
            throw new ArgumentoInvalidoException("Dados invalidos");
        } else {
            livros.add(livro);
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        Integer tamanhoLista = livros.size();
        if (titulo == null | titulo.isBlank() | titulo.isEmpty()) {
            throw new ArgumentoInvalidoException("");
        } else {
            for (Livro livro : livros) {
                if (titulo.equalsIgnoreCase(livro.getTitulo())) {
                    livros.remove(livro);
                }
                }

            if ((livros.size()) == tamanhoLista) {
                throw new LivroNaoEncontradoException();

            }

        }
    }

    public Integer contarLivros(){
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano){

        List<Livro> livroAntes = livros.stream()
                .filter(livro -> livro.getDataPublicacao().getYear() <= ano)
                .toList();
        return livroAntes;
    }

}
