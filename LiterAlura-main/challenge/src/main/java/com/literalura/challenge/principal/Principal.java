package com.literalura.challenge.principal;

import com.literalura.challenge.model.*;
import com.literalura.challenge.repository.AutorRepository;
import com.literalura.challenge.service.ConsumoApi;
import com.literalura.challenge.service.ConverteDados;

import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private AutorRepository repositorio;
    private final String mensagemIdioma = """
                                           Digite o idioma para pesquisar os livros
                                           1 - espanhol
                                           2 - ingles
                                           3 - frances
                                           4 - portugues         
                                           """;

    public Principal( AutorRepository repository) {
        this.repositorio = repository;
    }

    public void mostrarMenu() {
        var opcao = -1;

        var menu = """
                 \n --- Bem vindo ao Literalura ---
    
                 1.- Buscar livro por título
                 2.- Listar livros registrados
                 3.- Listar autores registrados
                 4.- Listar autores vivos a partir de um determinado ano
                 5.- Listar livros por idioma

                 0.- Sair do programa
                 
                 Escolha a opção através de seu número:   
                 """;

        while (opcao != 0) {
            System.out.println(menu);
            try {
                opcao = Integer.valueOf(teclado.nextLine());
                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo do aplicativo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida: " + e.getMessage());

            }
        }
    }

    public void buscarLivroPorTitulo(){
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String livroPedido = teclado.nextLine();
        var json = consumoApi.obterDados(URL_BASE + livroPedido.replace(" ", "%20"));

        if (!json.contains("\"results\":[]")){
            Dados dados = conversor.obterDados(json, Dados.class);
            Optional<DadosLivros> livroBuscado = dados.resultados().stream().findFirst();
            String mostrar = "--------------Livro---------------"+
                    "\nTitulo: " +livroBuscado.get().titulo() +
                    "\nAutor: " + livroBuscado.get().autor().get(0).nome() +
                    "\nIdioma: "  + livroBuscado.get().idiomas() +
                    "\nNumero de downloads: " + livroBuscado.get().download() +
                    "\n-------------------------------------";
            System.out.println(mostrar);

            List<Livro> livroEncontrado = livroBuscado.stream().map(r -> new Livro(r)).collect(Collectors.toList());
            Autor autorDados = livroBuscado.stream().
                    flatMap(l -> l.autor().stream()
                        .map(a -> new Autor(a)))
                    .collect(Collectors.toList()).stream().findFirst().get();
            String nome = livroEncontrado.get(0).getTitulo();
            Optional<Livro> estaNoBancoDeDados = repositorio.buscarLivroPorNome(nome);

            if (estaNoBancoDeDados.isPresent()){
                System.out.println("O livro já está no banco de dados.");
            } else {
                repositorio.save(autorDados);
                Autor autor = autorDados;
                autor.setLivros(livroEncontrado);
                repositorio.save(autor);
            }

        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void listarLivrosRegistrados(){
        List<Livro> livrosNoBancoDeDados = repositorio.buscarTodosLivros();
        implementarListaLivros(livrosNoBancoDeDados);
    }

    public void listarAutoresRegistrados(){
        List<Autor> autoresNoBancoDeDados = repositorio.findAll();
        implementarAutores(autoresNoBancoDeDados);
    }

    public void listarAutoresVivos(){
        System.out.println("Introduza o ano para buscar autores vivos até esse ano:");
        String year = teclado.nextLine();
        List<Autor> autoresVivos =repositorio.buscarAutoresVivos(year);

        if (autoresVivos.isEmpty()){
            System.out.println("Não há autores vivos para o ano registrado");
        } else {
            implementarAutores(autoresVivos);
        }
    }


    public void listarLivrosPorIdioma(){
        System.out.println(mensagemIdioma);
        try {
            var opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1:
                    buscarLivrosIdioma("es");
                    break;
                case 2:
                    buscarLivrosIdioma("en");
                    break;
                case 3:
                    buscarLivrosIdioma("fr");
                    break;
                case 4:
                    buscarLivrosIdioma("pt");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida: ");
        }
    }

    public void buscarLivrosIdioma(String clave){
        try {
            Idioma idiomaSelecionado = Idioma.valueOf(clave.toUpperCase());
            List<Livro> livrosIdiomaSelecionado = repositorio.buscarLibrosIdioma(idiomaSelecionado);

            if (livrosIdiomaSelecionado.isEmpty()) {
                System.out.println("Não há livros registrados nesse idioma");
            } else {
                implementarListaLivros(livrosIdiomaSelecionado);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Digite um idioma válido no formato especificado.");
        }

    }

    public void implementarAutores(List<Autor> autors){
        autors.forEach(a -> System.out.println("-----------------------------------------" +
                "\nAutor: " + a.getNome() +
                "\nData de nascimento: " + a.getNascimento() +
                "\nData de falecimento: " + a.getMorte() +
                "\nLivros: " + a.getLivros().stream()
                .map(n -> n.getTitulo()).collect(Collectors.toList())+
                "\n---------------------------------------"));
    }

    public void implementarListaLivros(List<Livro> livros){
        livros.forEach(l -> System.out.println(
                        "-----------------LIVRO-----------------" +
                        "\nTítulo: " + l.getTitulo() +
                        "\nAutor: " + l.getAutor().getNome() +
                        "\nIdioma: " + l.getIdiomas()+
                        "\nNúmero de downloads: " + l.getDownload() +
                        "\n----------------------------------------"
        ));
    }
}
