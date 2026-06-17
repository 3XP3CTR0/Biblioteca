import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private Utilizador utilizador;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevista;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro,
                       Utilizador utilizador,
                       LocalDate dataEmprestimo,
                       LocalDate dataPrevista) {

        this.livro = livro;
        this.utilizador = utilizador;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
    }

    public boolean estaAtrasado() {

        if (dataDevolucao == null) {
            return false;
        }

        return dataDevolucao.isAfter(dataPrevista);
    }

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

    public void registrarDevolucao() {
        dataDevolucao = LocalDate.now();
    }

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