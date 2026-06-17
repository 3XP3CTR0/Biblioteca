import javax.swing.*;
import java.awt.*;

public class BibliotecaGUI extends JFrame {

    private Biblioteca biblioteca = new Biblioteca();

    private JTextArea output = new JTextArea(20, 50);

    public BibliotecaGUI() {

        setTitle("Sistema de Biblioteca");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== PAINEL DE BOTOES =====
        JPanel panel = new JPanel(new GridLayout(4, 3, 5, 5));

        // LIVROS
        JButton addLivro = new JButton("Adicionar Livro");
        JButton listarLivros = new JButton("Listar Livros");
        JButton removerLivro = new JButton("Remover Livro");

        // UTILIZADORES
        JButton addUser = new JButton("Adicionar Utilizador");
        JButton listarUsers = new JButton("Listar Utilizadores");
        JButton removerUser = new JButton("Remover Utilizador");

        // EMPRÉSTIMOS
        JButton emprestar = new JButton("Emprestar Livro");
        JButton devolver = new JButton("Devolver Livro");
        JButton listarEmp = new JButton("Listar Empréstimos");

        // OUTROS
        JButton historico = new JButton("Histórico");
        JButton limpar = new JButton("Limpar Tela");

        panel.add(addLivro);
        panel.add(listarLivros);
        panel.add(removerLivro);

        panel.add(addUser);
        panel.add(listarUsers);
        panel.add(removerUser);

        panel.add(emprestar);
        panel.add(devolver);
        panel.add(listarEmp);

        panel.add(historico);
        panel.add(limpar);

        add(panel, BorderLayout.NORTH);

        // ===== ÁREA DE SAÍDA =====
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.CENTER);

        // ===== AÇÕES =====

        addLivro.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog("Título:");
            String autor = JOptionPane.showInputDialog("Autor:");
            String isbn = JOptionPane.showInputDialog("ISBN:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog("Ano:"));

            biblioteca.adicionarLivro(new Livro(titulo, autor, isbn, ano));

            output.append("Livro adicionado: " + titulo + "\n");
        });

        listarLivros.addActionListener(e -> {

            output.append("\n--- LIVROS ---\n");

            for (Livro l : biblioteca.getLivros()) {
                output.append(l.toString() + "\n----------------\n");
            }
        });

        removerLivro.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("ISBN do livro:");
            biblioteca.removerLivro(isbn);

            output.append("Livro removido (ISBN: " + isbn + ")\n");
        });

        addUser.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
            String nome = JOptionPane.showInputDialog("Nome:");
            String email = JOptionPane.showInputDialog("Email:");
            String tel = JOptionPane.showInputDialog("Telefone:");

            biblioteca.adicionarUtilizador(new Utilizador(id, nome, email, tel));

            output.append("Utilizador adicionado: " + nome + "\n");
        });

        listarUsers.addActionListener(e -> {

            output.append("\n--- UTILIZADORES ---\n");

            for (Utilizador u : biblioteca.getUtilizadores()) {
                output.append(u.toString() + "\n----------------\n");
            }
        });

        removerUser.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID Utilizador:"));
            biblioteca.removerUtilizador(id);

            output.append("Utilizador removido: " + id + "\n");
        });

        emprestar.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("ISBN:");
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID Utilizador:"));

            biblioteca.realizarEmprestimo(isbn, id);

            output.append("Empréstimo realizado\n");
        });

        devolver.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog("ISBN:");
            biblioteca.devolverLivro(isbn);

            output.append("Livro devolvido\n");
        });

        listarEmp.addActionListener(ev -> {

            output.append("\n--- EMPRÉSTIMOS ---\n");

            for (Emprestimo emp : biblioteca.getEmprestimos()) {
                output.append(emp.toString() + "\n----------------\n");
            }
        });

        historico.addActionListener(e -> {

            output.append("\n--- HISTÓRICO ---\n");

            for (String h : biblioteca.getHistorico()) {
                output.append(h + "\n");
            }
        });

        limpar.addActionListener(e -> output.setText(""));

        setVisible(true);
    }

    public static void main(String[] args) {
        new BibliotecaGUI();
    }
}