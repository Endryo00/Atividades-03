import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC extends JFrame implements ActionListener {
    private JTextField campoPeso, campoAltura;
    private JLabel resultadoIMC;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField();
        add(campoPeso);

        add(new JLabel("Altura (m):"));
        campoAltura = new JTextField();
        add(campoAltura);

        JButton botaoCalcular = new JButton("Calcular IMC");
        botaoCalcular.addActionListener(this);
        add(botaoCalcular);

        resultadoIMC = new JLabel("Resultado: ");
        add(resultadoIMC);

        add(new JLabel(""));
        add(new JLabel(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calcularIMC();
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(campoPeso.getText().trim());
            double altura = Double.parseDouble(campoAltura.getText().trim());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser maiores que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double imc = peso / (altura * altura);

            String categoria;
            if (imc < 18.5) {
                categoria = "Baixo peso";
            } else if (imc >= 18.5 && imc < 24.9) {
                categoria = "Peso normal";
            } else if (imc >= 25 && imc < 29.9) {
                categoria = "Sobrepeso";
            } else {
                categoria = "Obesidade";
            }

            resultadoIMC.setText(String.format("IMC: %.2f (%s)", imc, categoria));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insira valores numéricos válidos para peso e altura.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraIMC app = new CalculadoraIMC();
            app.setVisible(true);
        });
    }
}
