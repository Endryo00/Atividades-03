import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaDiaria extends JFrame {
    private JTextField campoCompromisso;
    private JSpinner spinnerDataHora;
    private JTable tabelaCompromissos;
    private DefaultTableModel modeloTabela;

    public AgendaDiaria() {
        setTitle("Agenda Diária");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelEntrada.add(new JLabel("Compromisso:"));
        campoCompromisso = new JTextField();
        painelEntrada.add(campoCompromisso);

        painelEntrada.add(new JLabel("Data e Hora:"));
        spinnerDataHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDataHora, "dd/MM/yyyy HH:mm");
        spinnerDataHora.setEditor(editor);
        painelEntrada.add(spinnerDataHora);

        add(painelEntrada, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new String[]{"Data e Hora", "Descrição"}, 0);
        tabelaCompromissos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaCompromissos);
        add(scrollTabela, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton botaoAdicionar = new JButton("Adicionar Compromisso");
        JButton botaoRemover = new JButton("Remover Compromisso");

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        add(painelBotoes, BorderLayout.SOUTH);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCompromisso();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCompromisso();
            }
        });
    }

    private void adicionarCompromisso() {
        try {
            String descricao = campoCompromisso.getText().trim();
            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira a descrição do compromisso.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date dataHora = (Date) spinnerDataHora.getValue();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataHoraFormatada = formato.format(dataHora);

            modeloTabela.addRow(new Object[]{dataHoraFormatada, descricao});
            campoCompromisso.setText(""); // Limpa o campo após adicionar
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar compromisso: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerCompromisso() {
        int linhaSelecionada = tabelaCompromissos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            modeloTabela.removeRow(linhaSelecionada);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um compromisso para remover.", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AgendaDiaria agenda = new AgendaDiaria();
            agenda.setVisible(true);
        });
    }
}
