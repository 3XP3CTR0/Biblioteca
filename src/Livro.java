public class Livro {

    // ===================== ATRIBUTOS =====================

    private String titulo;          // título do livro
    private String autor;           // autor do livro
    private String isbn;            // identificador único do livro
    private int anoPublicacao;      // ano em que foi publicado
    private boolean disponivel;     // indica se o livro está disponível para empréstimo

    // ===================== CONSTRUTOR =====================

    public Livro(String titulo, String autor, String isbn, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;

        // por padrão, quando um livro é criado, ele está disponível
        this.disponivel = true;
    }

    // ===================== GETTERS E SETTERS =====================

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    // OBS: normalmente ISBN não deveria ser alterado, mas aqui está permitido
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // ===================== REPRESENTAÇÃO EM TEXTO =====================

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nISBN: " + isbn +
                "\nAno: " + anoPublicacao +
                "\nDisponível: " + (disponivel ? "Sim" : "Não");
    }
}