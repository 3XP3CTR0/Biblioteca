import java.util.Scanner;

//Trabalho prático de Programação Orientada a Objetos - Sistema de Biblioteca
//Alunos: Danilo Alves e Lucas Modesto


// Classe principal do programa (ponto de entrada)
public class Main {

    public static void main(String[] args) {

        // Scanner para leitura de dados do teclado
        Scanner sc = new Scanner(System.in);

        // Criação do objeto Biblioteca (onde ficam livros, utilizadores e empréstimos)
        Biblioteca biblioteca = new Biblioteca();

        int opcao;

        // Loop principal do menu (repete até o utilizador escolher sair)
        do {

            // MENU PRINCIPAL
            System.out.println("\n==============================");
            System.out.println("       SISTEMA BIBLIOTECA     ");
            System.out.println("==============================");

            System.out.println("\n--- LIVROS ---");
            System.out.println("1  - Adicionar Livro");
            System.out.println("2  - Listar Livros");
            System.out.println("8  - Atualizar Livro");
            System.out.println("9  - Remover Livro");
            System.out.println("13 - Buscar por ISBN");
            System.out.println("14 - Buscar por Título");
            System.out.println("15 - Buscar por Autor");

            System.out.println("\n--- UTILIZADORES ---");
            System.out.println("3  - Adicionar Utilizador");
            System.out.println("12 - Listar Utilizadores");
            System.out.println("10 - Atualizar Utilizador");
            System.out.println("11 - Remover Utilizador");
            System.out.println("16 - Buscar por ID");
            System.out.println("17 - Buscar por Nome");

            System.out.println("\n--- EMPRÉSTIMOS ---");
            System.out.println("4  - Realizar Empréstimo");
            System.out.println("5  - Devolver Livro");
            System.out.println("6  - Ver Empréstimos");

            System.out.println("\n--- OUTROS ---");
            System.out.println("7  - Histórico");
            System.out.println("0  - Sair");

            // Leitura da opção escolhida pelo utilizador
            System.out.print("\nOpção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer (evita bugs com nextLine)

            // Estrutura de decisão baseada na opção escolhida
            switch (opcao) {

                // ===================== LIVROS =====================

                case 1 -> {
                    // Adicionar livro
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();

                    // Cria e adiciona livro à biblioteca
                    biblioteca.adicionarLivro(new Livro(titulo, autor, isbn, ano));
                }

                case 2 -> biblioteca.listarLivros();

                case 8 -> {
                    // Atualizar livro
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Novo título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Novo autor: ");
                    String autor = sc.nextLine();

                    System.out.print("Novo ano: ");
                    int ano = sc.nextInt();

                    biblioteca.atualizarLivro(isbn, titulo, autor, ano);
                }

                case 9 -> {
                    // Remover livro
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    biblioteca.removerLivro(isbn);
                }

                case 13 -> {
                    // Buscar por ISBN
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.println(biblioteca.buscarLivroISBN(isbn));
                }

                case 14 -> {
                    // Buscar por título
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.println(biblioteca.buscarLivroTitulo(titulo));
                }

                case 15 -> {
                    // Buscar por autor
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    for (Livro l : biblioteca.buscarLivroAutor(autor)) {
                        System.out.println(l);
                    }
                }

                // ===================== UTILIZADORES =====================

                case 3 -> {
                    // Adicionar utilizador
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    biblioteca.adicionarUtilizador(new Utilizador(id, nome, email, telefone));
                }

                case 12 -> biblioteca.listarUtilizadores();

                case 10 -> {
                    // Atualizar utilizador
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    biblioteca.atualizarUtilizador(id, nome, email, telefone);
                }

                case 11 -> {
                    // Remover utilizador
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    biblioteca.removerUtilizador(id);
                }

                case 16 -> {
                    // Buscar utilizador por ID
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    
                    Utilizador u = biblioteca.buscarUtilizadorID(id);

                    if (u != null) {
                        System.out.println(u);
                    } else {
                        System.out.println("Utilizador não encontrado.");
                    }
                }

                case 17 -> {
                    // Buscar utilizador por nome
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.println(biblioteca.buscarUtilizadorNome(nome));
                }

                // ===================== EMPRÉSTIMOS =====================

                case 4 -> {
                    // Realizar empréstimo
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("ID Utilizador: ");
                    int id = sc.nextInt();

                    biblioteca.realizarEmprestimo(isbn, id);
                }

                case 5 -> {
                    // Devolver livro
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    biblioteca.devolverLivro(isbn);
                }

                case 6 -> biblioteca.listarEmprestimos();

                case 7 -> biblioteca.mostrarHistorico();

                // ===================== SAÍDA =====================

                case 0 -> System.out.println("Programa encerrado.");

                // Caso inválido
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0); // repete enquanto não sair

        // Fecha o scanner (boa prática)
        sc.close();
    }
}