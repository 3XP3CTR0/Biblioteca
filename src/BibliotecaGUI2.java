// Importa componentes gráficos do Swing
import javax.swing.*;

// Importa componentes de layout e cores
import java.awt.*;

// Classe da interface gráfica.
// Herda de JFrame para criar uma janela.
public class BibliotecaGUI2 extends JFrame {

    // Objeto responsável por toda a lógica do sistema
    // (livros, utilizadores, empréstimos, histórico)
    private Biblioteca biblioteca = new Biblioteca();

    // Área de texto onde serão mostrados os resultados
    private JTextArea output = new JTextArea();

    // Construtor da interface
    public BibliotecaGUI2() {

        // ================= CONFIGURAÇÃO DA JANELA =================

        // Define o título da janela
        setTitle("Sistema de Biblioteca");

        // Define largura e altura da janela
        setSize(1100, 650);

        // Fecha a aplicação quando a janela for fechada
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centraliza a janela no ecrã
        setLocationRelativeTo(null);

        // Define o layout principal
        setLayout(new BorderLayout());

        // ==========================================================
        // ===================== PAINEL SUPERIOR ====================
        // ==========================================================

        // Cria painel do topo
        JPanel topo = new JPanel();

        // Define a cor de fundo do painel
        topo.setBackground(new Color(44, 62, 80));

        // Cria o título principal
        JLabel titulo = new JLabel("📚 SISTEMA DE BIBLIOTECA");

        // Cor do texto
        titulo.setForeground(Color.WHITE);

        // Fonte do título
        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        // Adiciona o título ao painel
        topo.add(titulo);

        // Coloca o painel no topo da janela
        add(topo, BorderLayout.NORTH);

        // ==========================================================
        // ===================== MENU LATERAL =======================
        // ==========================================================

        // Painel onde ficarão todos os botões
        JPanel menu = new JPanel();

        // Organiza os botões verticalmente
        menu.setLayout(
                new GridLayout(
                        0,
                        1,
                        5,
                        5
                )
        );

        // Adiciona margens internas
        menu.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        // ================= BOTÕES DOS LIVROS =================

        JButton addLivro =
                new JButton("Adicionar Livro");

        JButton listarLivros =
                new JButton("Listar Livros");

        JButton buscarLivro =
                new JButton("Buscar Livro");

        JButton removerLivro =
                new JButton("Remover Livro");

        // ================= BOTÕES DOS UTILIZADORES =================

        JButton addUser =
                new JButton("Adicionar Utilizador");

        JButton listarUsers =
                new JButton("Listar Utilizadores");

        JButton buscarUtilizador =
                new JButton("Buscar Utilizador");

        JButton removerUser =
                new JButton("Remover Utilizador");

        // ================= BOTÕES DOS EMPRÉSTIMOS =================

        JButton emprestar =
                new JButton("Emprestar Livro");

        JButton devolver =
                new JButton("Devolver Livro");

        JButton listarEmp =
                new JButton("Listar Empréstimos");

        // ================= OUTRAS OPÇÕES =================

        JButton historico =
                new JButton("Histórico");

        JButton limpar =
                new JButton("Limpar Tela");

        // Vetor contendo todos os botões
        JButton[] botoes = {

                addLivro,
                listarLivros,
                buscarLivro,
                removerLivro,

                addUser,
                listarUsers,
                buscarUtilizador,
                removerUser,

                emprestar,
                devolver,
                listarEmp,

                historico,
                limpar
        };

        // Percorre todos os botões para aplicar o mesmo estilo
        for (JButton b : botoes) {

            // Cor de fundo
            b.setBackground(
                    new Color(52, 152, 219)
            );

            // Cor do texto
            b.setForeground(Color.WHITE);

            // Remove o contorno de foco
            b.setFocusPainted(false);

            // Define fonte
            b.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            13
                    )
            );

            // Adiciona botão ao menu lateral
            menu.add(b);
        }

        // Adiciona menu na esquerda da janela
        add(menu, BorderLayout.WEST);

        // ==========================================================
        // ================= ÁREA DE RESULTADOS =====================
        // ==========================================================

        // Impede edição manual do texto
        output.setEditable(false);

        // Fonte utilizada no output
        output.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        // Cor de fundo
        output.setBackground(
                new Color(245, 245, 245)
        );

        // Adiciona barra de scroll automática
        add(
                new JScrollPane(output),
                BorderLayout.CENTER
        );

        // ==========================================================
        // ================= EVENTOS DOS BOTÕES =====================
        // ==========================================================

        // Quando clicar em "Adicionar Livro"
        addLivro.addActionListener(e -> {

            // Solicita dados do livro
            String tituloLivro =
                    JOptionPane.showInputDialog("Título:");

            String autor =
                    JOptionPane.showInputDialog("Autor:");

            String isbn =
                    JOptionPane.showInputDialog("ISBN:");

            int ano =
                    Integer.parseInt(
                            JOptionPane.showInputDialog("Ano:")
                    );

            // Cria e adiciona livro
            biblioteca.adicionarLivro(
                    new Livro(
                            tituloLivro,
                            autor,
                            isbn,
                            ano
                    )
            );

            // Mostra mensagem no output
            output.append(
                    "✅ Livro adicionado: "
                            + tituloLivro +
                            "\n"
            );
        });

        // Listar todos os livros
        listarLivros.addActionListener(e -> {

            output.append(
                    "\n===== LIVROS =====\n"
            );

            for (Livro l : biblioteca.getLivros()) {

                output.append(
                        l +
                        "\n------------------\n"
                );
            }
        });

        // Procurar livro pelo ISBN
        buscarLivro.addActionListener(e -> {

            String isbn =
                    JOptionPane.showInputDialog("ISBN:");

            Livro livro =
                    biblioteca.buscarLivroISBN(isbn);

            if (livro != null) {

                output.append(
                        "\n" +
                        livro +
                        "\n"
                );

            } else {

                output.append(
                        "❌ Livro não encontrado\n"
                );
            }
        });

        // Remover livro
        removerLivro.addActionListener(e -> {

            String isbn =
                    JOptionPane.showInputDialog("ISBN:");

            biblioteca.removerLivro(isbn);

            output.append(
                    "🗑 Livro removido\n"
            );
        });

        //adicionar utilizador
        addUser.addActionListener(e -> {

                String nome = JOptionPane.showInputDialog("Nome:");
                String email = JOptionPane.showInputDialog("Email:");
                String telefone = JOptionPane.showInputDialog("Telefone:");
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));

                biblioteca.adicionarUtilizador(
                        new Utilizador(id, nome, email, telefone)
                );

                output.append("✅ Utilizador adicionado: " + nome + "\n");
        });

        emprestar.addActionListener(e -> {

                String isbn = JOptionPane.showInputDialog("ISBN:");
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID Utilizador:"));

                biblioteca.realizarEmprestimo(isbn, id);

                output.append("📚 Empréstimo realizado\n");
        });

        devolver.addActionListener(e -> {

                String isbn = JOptionPane.showInputDialog("ISBN:");

                biblioteca.devolverLivro(isbn);

                output.append("📥 Livro devolvido\n");
        });

        listarEmp.addActionListener(e -> {

                output.append("\n===== EMPRÉSTIMOS =====\n");

                for (Emprestimo emp : biblioteca.getEmprestimos()) {
                        output.append(emp + "\n------------------\n");
                }
        });

        historico.addActionListener(e -> {

                output.append("\n===== HISTÓRICO =====\n");

                for (String h : biblioteca.getHistorico()) {
                        output.append(h + "\n");
                }
        });

        // Listar todos os utilizadores
        listarUsers.addActionListener(e -> {

                output.append("\n===== UTILIZADORES =====\n");

                for (Utilizador u : biblioteca.getUtilizadores()) {
                        output.append(u + "\n------------------\n");
                }
        });

        // Os restantes ActionListener seguem exatamente
        // a mesma lógica:
        //
        // 1. Pedem dados ao utilizador
        // 2. Chamam métodos da classe Biblioteca
        // 3. Mostram o resultado na área output

        // Limpa completamente a área de resultados
        limpar.addActionListener(
                e -> output.setText("")
        );

        // Torna a janela visível
        setVisible(true);
    }

    // Método principal do programa
    public static void main(String[] args) {

        // Cria a interface na thread gráfica do Swing
        SwingUtilities.invokeLater(
                BibliotecaGUI2::new
        );
    }
}