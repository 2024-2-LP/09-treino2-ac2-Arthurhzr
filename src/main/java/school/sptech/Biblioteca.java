package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro){
        if (livro == null  || livro.getTitulo() == null ||
                livro.getAutor() == null ||
                livro.getDataPublicacao() == null ||
                livro.getTitulo().isBlank() ||
                livro.getTitulo().isEmpty()
                || livro.getAutor().isBlank() ||
                livro.getAutor().isEmpty()){
            throw new ArgumentoInvalidoException("Dados invalidos");
        } else {
            livros.add(livro);
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException("");
        }
        if (!livros.removeIf(livro -> titulo.equalsIgnoreCase(livro.getTitulo()))) {
            throw new LivroNaoEncontradoException();
        }

    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException();
        }

        Livro busca = livros.stream().
                filter(livroAtual -> livroAtual.getTitulo().equalsIgnoreCase(titulo)).
                findFirst().
                orElseThrow(
                        LivroNaoEncontradoException::new
                );
        return busca;
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

    public List<Livro> retornarTopCincoLivros(){
        List<Livro> livroAntes = livros.stream()
                .sorted(Comparator.comparingDouble(Livro :: calcularMediaAvaliacoes))
                .limit(5)
                .toList().reversed();
        return livroAntes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
