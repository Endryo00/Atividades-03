import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

public class CadastroContatos extends JFrame implements ActionListener {
    private JTextField campoNome, campoTelefone, campoEmail;
    private JList<String> listaContatos;
    private DefaultListModel<String> modeloLista;

    public CadastroContatos() {
        setTitle("Cadastro de Contatos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(4, 2, 5, 5));

        painelEntrada.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelEntrada.add(campoNome);

        painelEntrada.add(new JLabel("Telefone:"));
        campoTelefone = new JTextField();
        painelEntrada.add(campoTelefone);

        painelEntrada.add(new JLabel("E-mail:"));
        campoEmail = new JTextField();
        painelEntrada.add(campoEmail);

        JButton botaoAdicionar = new JButton("Adicionar Contato");
        botaoAdicionar.addActionListener(this);
        painelEntrada.add(botaoAdicionar);

        JButton botaoRemover = new JButton("Remover Contato");
        botaoRemover.addActionListener(this);
        painelEntrada.add(botaoRemover);

        add(painelEntrada, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaContatos = new JList<>(modeloLista);
        listaContatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaContatos);
        add(scrollLista, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Adicionar Contato")) {
            adicionarContato();
        } else if (comando.equals("Remover Contato")) {
            removerContato();
        }
    }

    private void adicionarContato() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contato = String.format("%s - %s - %s", nome, telefone, email);
        modeloLista.addElement(contato);

        campoNome.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    private void removerContato() {
        int indiceSelecionado = listaContatos.getSelectedIndex();

        if (indiceSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contato para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        modeloLista.remove(indiceSelecionado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroContatos app = new CadastroContatos();
            app.setVisible(true);
        });
    }
}
