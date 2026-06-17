import java.time.LocalDate;

// Classe que representa um empréstimo de livro a um utilizador
public class Emprestimo {

    // ===================== ATRIBUTOS =====================

    private Livro livro;                 // livro emprestado
    private Utilizador utilizador;       // utilizador que fez o empréstimo

    private LocalDate dataEmprestimo;    // data em que foi emprestado
    private LocalDate dataPrevista;      // data limite para devolução
    private LocalDate dataDevolucao;     // data real da devolução (null se ainda não devolvido)

    // ===================== CONSTRUTOR =====================

    public Emprestimo(Livro livro,
                      Utilizador utilizador,
                      LocalDate dataEmprestimo,
                      LocalDate dataPrevista) {

        this.livro = livro;
        this.utilizador = utilizador;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;

        // dataDevolucao começa como null porque ainda não foi devolvido
    }

    // ===================== VERIFICAR ATRASO =====================

    public boolean estaAtrasado() {

        // Se ainda não foi devolvido, não consideramos atraso aqui
        if (dataDevolucao == null) {
            return false;
        }

        // Se devolveu depois da data prevista, está atrasado
        return dataDevolucao.isAfter(dataPrevista);
    }

    // ===================== GETTERS =====================

    public Livro getLivro() {
        return livro;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    // ===================== REGISTAR DEVOLUÇÃO =====================

    public void registrarDevolucao() {
        // define a data atual como data de devolução
        dataDevolucao = LocalDate.now();
    }

    // ===================== TO STRING =====================

    @Override
    public String toString() {

        return "\nLivro: " + livro.getTitulo()
                + "\nUtilizador: " + utilizador.getNome()
                + "\nData Empréstimo: " + dataEmprestimo
                + "\nData Prevista: " + dataPrevista
                + "\nData Devolução: "
                + (dataDevolucao == null ? "Ainda não devolvido" : dataDevolucao);
    }
}