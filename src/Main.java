import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcao;

        do {

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

            System.out.print("\nOpção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    sc.nextLine();

                    biblioteca.adicionarLivro(new Livro(titulo, autor, isbn, ano));
                }

                case 2 -> biblioteca.listarLivros();

                case 3 -> {
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

                case 4 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("ID Utilizador: ");
                    int id = sc.nextInt();

                    biblioteca.realizarEmprestimo(isbn, id);
                }

                case 5 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    biblioteca.devolverLivro(isbn);
                }

                case 6 -> biblioteca.listarEmprestimos();

                case 7 -> biblioteca.mostrarHistorico();

                case 8 -> {
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
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    biblioteca.removerLivro(isbn);
                }

                case 10 -> {
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
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    biblioteca.removerUtilizador(id);
                }

                case 12 -> biblioteca.listarUtilizadores();

                case 13 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.println(biblioteca.buscarLivroISBN(isbn));
                }

                case 14 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.println(biblioteca.buscarLivroTitulo(titulo));
                }

                case 15 -> {
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    for (Livro l : biblioteca.buscarLivroAutor(autor)) {
                        System.out.println(l);
                    }
                }

                case 16 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    System.out.println(biblioteca.buscarUtilizadorID(id));
                }

                case 17 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.println(biblioteca.buscarUtilizadorNome(nome));
                }

                case 0 -> System.out.println("Programa encerrado.");

                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}