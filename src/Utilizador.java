public class Utilizador {

    // ===================== ATRIBUTOS =====================

    private int id;               // identificador único do utilizador
    private String nome;          // nome do utilizador
    private String email;         // email do utilizador
    private String telefone;      // contacto telefónico

    // ===================== CONSTRUTOR =====================

    public Utilizador(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // ===================== GETTERS =====================

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    // ===================== SETTERS =====================

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // ===================== TO STRING =====================

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nNome: " + nome +
                "\nEmail: " + email +
                "\nTelefone: " + telefone;
    }
}