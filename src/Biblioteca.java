import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Classe principal do sistema
// Responsável por toda a lógica da biblioteca:
// livros, utilizadores, empréstimos e histórico
public class Biblioteca {

    // ===================== ESTRUTURAS DE DADOS =====================

    // Lista de livros disponíveis na biblioteca
    private LinkedList<Livro> livros;

    // Lista de utilizadores registados
    private LinkedList<Utilizador> utilizadores;

    // Lista de empréstimos ativos ou já realizados
    private LinkedList<Emprestimo> emprestimos;

    // Fila de espera (FIFO - primeiro a entrar, primeiro a sair)
    private Queue<Emprestimo> filaEspera;

    // Pilha de histórico (LIFO - último registo aparece primeiro)
    private Stack<String> historico;

    // ===================== CONSTRUTOR =====================

    // Inicializa todas as estruturas quando a biblioteca é criada
    public Biblioteca() {

        livros = new LinkedList<>();
        utilizadores = new LinkedList<>();
        emprestimos = new LinkedList<>();

        filaEspera = new LinkedList<>();
        historico = new Stack<>();
    }

    // =========================================================
    // ===================== MÉTODOS DE LIVROS =================
    // =========================================================

    // Adiciona um livro à lista de livros
    public void adicionarLivro(Livro livro) {

        livros.add(livro); // adiciona na lista

        // regista no histórico a ação feita
        historico.push("Livro adicionado: " + livro.getTitulo());
    }

    // Procura um livro pelo ISBN (identificador único)
    public Livro buscarLivroISBN(String isbn) {

        // percorre todos os livros
        for (Livro l : livros) {

            // compara ISBN
            if (l.getIsbn().equals(isbn)) {
                return l; // retorna se encontrar
            }
        }

        // se não encontrar, devolve null
        return null;
    }

    // Atualiza dados de um livro
    public void atualizarLivro(String isbn,
                               String novoTitulo,
                               String novoAutor,
                               int novoAno) {

        // procura o livro
        Livro livro = buscarLivroISBN(isbn);

        // se existir, atualiza os dados
        if (livro != null) {

            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAno);

            // regista ação no histórico
            historico.push("[LIVRO] ATUALIZADO -> " + livro.getTitulo());

            System.out.println("Livro atualizado.");

        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Procura livro pelo título (ignorando maiúsculas/minúsculas)
    public Livro buscarLivroTitulo(String titulo) {

        for (Livro l : livros) {

            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }

        return null;
    }

    // Procura todos os livros de um autor
    public LinkedList<Livro> buscarLivroAutor(String autor) {

        // lista onde serão guardados os resultados
        LinkedList<Livro> resultado = new LinkedList<>();

        for (Livro l : livros) {

            if (l.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(l);
            }
        }

        return resultado;
    }

    // Remove um livro pelo ISBN
    public void removerLivro(String isbn) {

        Livro livro = buscarLivroISBN(isbn);

        if (livro != null) {

            livros.remove(livro);

            historico.push("[LIVRO] REMOVIDO -> " + livro.getTitulo());

            System.out.println("Livro removido.");

        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Lista livros ordenados por título (Bubble Sort manual)
    public void listarLivros() {

        // algoritmo de ordenação simples (não otimizado)
        for (int i = 0; i < livros.size() - 1; i++) {

            for (int j = 0; j < livros.size() - i - 1; j++) {

                // compara títulos alfabeticamente
                if (livros.get(j).getTitulo()
                        .compareToIgnoreCase(livros.get(j + 1).getTitulo()) > 0) {

                    // troca de posições
                    Livro temp = livros.get(j);
                    livros.set(j, livros.get(j + 1));
                    livros.set(j + 1, temp);
                }
            }
        }

        // imprime todos os livros
        for (Livro l : livros) {
            System.out.println(l);
            System.out.println("----------------");
        }
    }

    // =========================================================
    // ===================== UTILIZADORES ======================
    // =========================================================

    // Adiciona utilizador
    public void adicionarUtilizador(Utilizador u) {

        utilizadores.add(u);

        historico.push("[UTILIZADOR] registado -> " + u.getNome());
    }

    // Procura utilizador por ID
    public Utilizador buscarUtilizadorID(int id) {

        for (Utilizador u : utilizadores) {

            if (u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    // Procura utilizador por nome
    public Utilizador buscarUtilizadorNome(String nome) {

        for (Utilizador u : utilizadores) {

            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }

        return null;
    }

    // Atualiza dados do utilizador
    public void atualizarUtilizador(int id,
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

    // Remove utilizador
    public void removerUtilizador(int id) {

        Utilizador u = buscarUtilizadorID(id);

        if (u != null) {

            utilizadores.remove(u);

            historico.push("[UTILIZADOR] REMOVIDO -> " + u.getNome());

            System.out.println("Utilizador removido.");

        } else {
            System.out.println("Utilizador não encontrado.");
        }
    }

    // Lista utilizadores ordenados por nome
    public void listarUtilizadores() {

        // Bubble sort por nome
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

        // imprime utilizadores
        for (Utilizador u : utilizadores) {
            System.out.println(u);
            System.out.println("----------------");
        }
    }

    // =========================================================
    // ===================== EMPRÉSTIMOS =======================
    // =========================================================

    // Realiza empréstimo de um livro
    public void realizarEmprestimo(String isbn, int idUtilizador) {

        // procura livro e utilizador
        Livro livro = buscarLivroISBN(isbn);
        Utilizador utilizador = buscarUtilizadorID(idUtilizador);

        // validação básica
        if (livro == null || utilizador == null) {
            System.out.println("Livro ou utilizador não encontrado.");
            return;
        }

        // verifica se já está emprestado
        for (Emprestimo e : emprestimos) {

            if (e.getLivro().getIsbn().equals(isbn) &&
                e.getDataDevolucao() == null) {

                System.out.println("Livro já está emprestado.");
                return;
            }
        }

        // se livro não estiver disponível
        if (!livro.isDisponivel()) {

            // adiciona à fila de espera
            filaEspera.add(new Emprestimo(
                    livro,
                    utilizador,
                    LocalDate.now(),
                    LocalDate.now().plusDays(7)
            ));

            System.out.println("Livro indisponível. Entrou na fila.");

            return;
        }

        // marca como indisponível
        livro.setDisponivel(false);

        // cria empréstimo
        Emprestimo e = new Emprestimo(
                livro,
                utilizador,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        emprestimos.add(e);

        historico.push("Empréstimo: " + livro.getTitulo());

        System.out.println("Empréstimo registado.");
    }

    // Devolve livro
    public void devolverLivro(String isbn) {

        for (Emprestimo e : emprestimos) {

            if (e.getLivro().getIsbn().equals(isbn)) {

                // regista devolução
                e.registrarDevolucao();

                // torna livro disponível novamente
                e.getLivro().setDisponivel(true);

                // verifica atraso
                if (e.estaAtrasado()) {
                    System.out.println("Livro devolvido com atraso.");
                }

                // verifica fila de espera
                if (!filaEspera.isEmpty()) {

                    Emprestimo proximo = filaEspera.poll();

                    emprestimos.add(proximo);

                    proximo.getLivro().setDisponivel(false);

                    System.out.println(
                            "Livro atribuído a: " +
                            proximo.getUtilizador().getNome()
                    );

                } else {
                    System.out.println("Livro devolvido dentro do prazo.");
                }

                historico.push("[DEVOLVIDO] " + e.getLivro().getTitulo());

                return;
            }
        }

        System.out.println("Empréstimo não encontrado.");
    }

    // Lista empréstimos
    public void listarEmprestimos() {

        for (Emprestimo e : emprestimos) {
            System.out.println(e);
            System.out.println("----------------");
        }
    }

    // =========================================================
    // ===================== HISTÓRICO =========================
    // =========================================================

    // Mostra histórico de operações
    public void mostrarHistorico() {

        if (historico.isEmpty()) {
            System.out.println("Sem histórico.");
            return;
        }

        for (String h : historico) {
            System.out.println(h);
        }
    }

    // =========================================================
    // ===================== GETTERS ============================
    // =========================================================

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