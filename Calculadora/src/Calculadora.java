import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField tela;
    private String operador = "";
    private double primeiroNumero = 0;
    private boolean limparTela = false;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tela = new JTextField();
        tela.setHorizontalAlignment(JTextField.RIGHT);
        tela.setEditable(false);
        tela.setFont(new Font("Arial", Font.BOLD, 24));
        add(tela, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "R", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 18));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            if (limparTela) {
                tela.setText("");
                limparTela = false;
            }
            tela.setText(tela.getText() + comando);
        } else if ("/*-+".contains(comando)) {
            operador = comando;
            try {
                primeiroNumero = Double.parseDouble(tela.getText());
            } catch (NumberFormatException ex) {
                tela.setText("Erro");
                return;
            }
            limparTela = true;
        } else if (comando.equals("=")) {
            try {
                double segundoNumero = Double.parseDouble(tela.getText());
                double resultado = calcular(primeiroNumero, segundoNumero, operador);
                tela.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                tela.setText("Erro");
            }
        } else if (comando.equals("R")) {
            tela.setText("");
            primeiroNumero = 0;
            operador = "";
        }
    }

    private double calcular(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Erro: Divisão por zero");
                    return 0;
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
