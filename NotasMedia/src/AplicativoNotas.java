import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AplicativoNotas extends JFrame implements ActionListener {
    private JTextField campoNota;
    private JTextArea areaNotas;
    private JLabel resultado;
    private ArrayList<Double> notas;

    public AplicativoNotas() {
        setTitle("Aplicativo de Notas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        notas = new ArrayList<>();

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new FlowLayout());

        JLabel labelNota = new JLabel("Nota:");
        painelEntrada.add(labelNota);

        campoNota = new JTextField(10);
        painelEntrada.add(campoNota);

        JButton botaoAdicionar = new JButton("Adicionar Nota");
        botaoAdicionar.addActionListener(this);
        painelEntrada.add(botaoAdicionar);

        add(painelEntrada, BorderLayout.NORTH);

        areaNotas = new JTextArea();
        areaNotas.setEditable(false);
        areaNotas.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaNotas);
        add(scroll, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout());

        JButton botaoCalcular = new JButton("Calcular Média");
        botaoCalcular.addActionListener(this);
        painelInferior.add(botaoCalcular, BorderLayout.WEST);

        resultado = new JLabel("Média: - | Status: -");
        resultado.setFont(new Font("Arial", Font.BOLD, 14));
        resultado.setHorizontalAlignment(JLabel.CENTER);
        painelInferior.add(resultado, BorderLayout.CENTER);

        add(painelInferior, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("Adicionar Nota")) {
            adicionarNota();
        } else if (comando.equals("Calcular Média")) {
            calcularMedia();
        }
    }

    private void adicionarNota() {
        try {
            double nota = Double.parseDouble(campoNota.getText());
            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(this, "Digite uma nota válida entre 0 e 10.");
                return;
            }

            notas.add(nota);
            atualizarAreaNotas();
            campoNota.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.");
        }
    }

    private void atualizarAreaNotas() {
        StringBuilder listaNotas = new StringBuilder("Notas:\n");
        for (double nota : notas) {
            listaNotas.append(nota).append("\n");
        }
        areaNotas.setText(listaNotas.toString());
    }

    private void calcularMedia() {
        if (notas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma nota inserida.");
            return;
        }

        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        double media = soma / notas.size();

        String status = media >= 7.0 ? "Aprovado" : "Reprovado";
        resultado.setText(String.format("Média: %.2f | Status: %s", media, status));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplicativoNotas app = new AplicativoNotas();
            app.setVisible(true);
        });
    }
}
