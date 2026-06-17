import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Biblioteca {

    private LinkedList<Livro> livros;
    private LinkedList<Utilizador> utilizadores;
    private LinkedList<Emprestimo> emprestimos;

    private Queue<Emprestimo> filaEspera;

    private Stack<String> historico;

    public Biblioteca() {

        livros = new LinkedList<>();
        utilizadores = new LinkedList<>();
        emprestimos = new LinkedList<>();

        filaEspera = new LinkedList<>();

        historico = new Stack<>();
    }

    // LIVROS

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        historico.push("Livro adicionado: " + livro.getTitulo());
    }

    public Livro buscarLivroISBN(String isbn) {

        for (Livro l : livros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }

        return null;
    }

    public LinkedList<Livro> listarLivrosGUI() {
        return livros;
    }

    public void atualizarLivro(String isbn,
                           String novoTitulo,
                           String novoAutor,
                           int novoAno) {

        Livro livro = buscarLivroISBN(isbn);

        if(livro != null) {

            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAno);

            historico.push(
                "[LIVRO] ATUALIZADO -> " +
                livro.getTitulo()
            );

            System.out.println("Livro atualizado.");
        }
        else {
            System.out.println("Livro não encontrado.");
        }
    }

    public Livro buscarLivroTitulo(String titulo) {

        for (Livro l : livros) {

            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }

        return null;
    }

    public void atualizarUtilizador(
        int id,
        String nome,
        String email,
        String telefone) {

        Utilizador u = buscarUtilizadorID(id);

        if (u != null) {
            u.setNome(nome);
            u.setEmail(email);
            u.setTelefone(telefone);

            historico.push("[UTILIZADOR] ATUALIZADO -> " + u.getNome());

            System.out.println("Utilizador atualizado.");
        } else {
            System.out.println("Utilizador não encontrado.");
        }
    }

    public void removerUtilizador(int id) {

        Utilizador u = buscarUtilizadorID(id);

        if (u != null) {

            utilizadores.remove(u);

            historico.push(
                    "[UTILIZADOR] REMOVIDO -> "
                            + u.getNome());

            System.out.println("Utilizador removido.");
        } else {
            System.out.println("Utilizador não encontrado.");
        }
    }

    public Utilizador buscarUtilizadorNome(
        String nome) {

        for (Utilizador u : utilizadores) {

            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }

        return null;
    }

    public void listarUtilizadores() {

        for (int i = 0; i < utilizadores.size() - 1; i++) {
            for (int j = 0; j < utilizadores.size() - i - 1; j++) {

                if (utilizadores.get(j).getNome()
                        .compareToIgnoreCase(utilizadores.get(j + 1).getNome()) > 0) {

                    Utilizador temp = utilizadores.get(j);
                    utilizadores.set(j, utilizadores.get(j + 1));
                    utilizadores.set(j + 1, temp);
                }
            }
        }

        for (Utilizador u : utilizadores) {

            System.out.println(u);
            System.out.println("----------------");
        }
    }

    public LinkedList<Livro> buscarLivroAutor(String autor) {

        LinkedList<Livro> resultado = new LinkedList<>();

        for (Livro l : livros) {
            if (l.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(l);
            }
        }

        return resultado;
    }

    public void removerLivro(String isbn) {

        Livro livro = buscarLivroISBN(isbn);

        if (livro != null) {
            livros.remove(livro);
            historico.push("[LIVRO] REMOVIDO -> " + livro.getTitulo());
            System.out.println("Livro removido.");
        }
        else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void listarLivros() {

        for (int i = 0; i < livros.size() - 1; i++) {
            for (int j = 0; j < livros.size() - i - 1; j++) {

                if (livros.get(j).getTitulo()
                        .compareToIgnoreCase(livros.get(j + 1).getTitulo()) > 0) {

                    Livro temp = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, temp);
                }
            }
        }

        for (Livro l : livros) {
            System.out.println(l);
            System.out.println("----------------");
        }
    }

    // UTILIZADORES

    public void adicionarUtilizador(Utilizador u) {

        utilizadores.add(u);

        historico.push(
                "[UTILIZADOR] registado -> "
                        + u.getNome()
        );
    }

    public Utilizador buscarUtilizadorID(int id) {

        for (Utilizador u : utilizadores) {

            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    // EMPRÉSTIMOS

    public void realizarEmprestimo(
            String isbn,
            int idUtilizador) {

        Livro livro = buscarLivroISBN(isbn);

        Utilizador utilizador =
                buscarUtilizadorID(idUtilizador);

        if (livro == null || utilizador == null) {

            System.out.println(
                    "Livro ou utilizador não encontrado."
            );

            return;
        }

        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getIsbn().equals(isbn) &&
                e.getDataDevolucao() == null) {

                System.out.println("Livro já está emprestado.");
                return;
            }
        }

        if (!livro.isDisponivel()) {

            livro.setDisponivel(false);
            filaEspera.add(
                new Emprestimo(
                    livro,
                    utilizador,
                    LocalDate.now(),
                    LocalDate.now().plusDays(7)
                )
            );
            System.out.println(
                    "Livro indisponível. Utilizador colocado na fila."
            );

            return;
        }

        livro.setDisponivel(false);

        Emprestimo e =
                new Emprestimo(
                        livro,
                        utilizador,
                        LocalDate.now(),
                        LocalDate.now().plusDays(7)
                );

        emprestimos.add(e);

        historico.push(
                "Empréstimo realizado: "
                        + livro.getTitulo()
        );

        System.out.println(
                "Empréstimo registado."
        );
    }

    public void devolverLivro(String isbn) {

        for (Emprestimo e : emprestimos) {

            if (e.getLivro().getIsbn().equals(isbn)) {

                e.registrarDevolucao();

                e.getLivro().setDisponivel(true);

                if (e.estaAtrasado()) {

                    System.out.println(
                            "Livro devolvido com atraso.");
                }

                if (!filaEspera.isEmpty()) {

                    Emprestimo proximo = filaEspera.poll();

                    emprestimos.add(proximo);

                    proximo.getLivro().setDisponivel(false);

                    System.out.println(
                        "Livro atribuído automaticamente a: "
                        + proximo.getUtilizador().getNome()
                    );
                }
                else {

                    System.out.println(
                            "Livro devolvido dentro do prazo.");
                }

                historico.push("[LIVRO] DEVOLVIDO -> " + e.getLivro().getTitulo());

                return;
            }
        }

        System.out.println(
                "Empréstimo não encontrado.");
    }

    public void listarEmprestimos() {

        for (Emprestimo e : emprestimos) {

            System.out.println(e);

            System.out.println("----------------");
        }
    }

    // HISTÓRICO

    public void mostrarHistorico() {

        if (historico.isEmpty()) {

            System.out.println("Sem histórico.");

            return;
        }

        for (String operacao : historico) {

            System.out.println(operacao);
        }
    }

    public LinkedList<Livro> getLivros() {
        return livros;
    }

    public LinkedList<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public LinkedList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public Stack<String> getHistorico() {
        return historico;
    }
}