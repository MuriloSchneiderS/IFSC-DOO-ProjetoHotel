package utilities;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Utilities {

    public static void ativaDesativa(JPanel painel, boolean ativa) {

        Component[] vetComponentes = painel.getComponents();
        for (Component componenteAtual : vetComponentes) {
            if (componenteAtual instanceof JButton) {
                if (((JButton) componenteAtual).getActionCommand() == "0") {
                    componenteAtual.setEnabled(ativa);
                } else {
                    componenteAtual.setEnabled(!ativa);
                }
            }
        }
    }

    public static void limpaComponentes(JPanel painel, boolean ativa) {
        Component[] vetCmponentes = painel.getComponents();
        for (Component componenteAtual : vetCmponentes) {
            if (componenteAtual instanceof JTextField) {
                JTextField textField = (JTextField) componenteAtual;
                if (textField.isEditable()) {
                    textField.setText("");
                }
                componenteAtual.setEnabled(ativa);
            } else if (componenteAtual instanceof JFormattedTextField) {
                ((JFormattedTextField) componenteAtual).setText("");
                componenteAtual.setEnabled(ativa);
            } else if (componenteAtual instanceof JComboBox) {
                ((JComboBox) componenteAtual).setSelectedIndex(-1);
                componenteAtual.setEnabled(ativa);
            } else if (componenteAtual instanceof JCheckBox) {
                ((JCheckBox) componenteAtual).setSelected(false);
                componenteAtual.setEnabled(ativa);
            } else if (componenteAtual instanceof JPasswordField) {
                ((JPasswordField) componenteAtual).setText("");
                componenteAtual.setEnabled(ativa);
            } else if (componenteAtual instanceof JTextArea) {
                ((JTextArea) componenteAtual).setEnabled(ativa);
            } else if (componenteAtual instanceof JSpinner) {
                ((JSpinner) componenteAtual).setValue(0);
                ((JSpinner) componenteAtual).setEnabled(ativa);
            }
        }
    }

    public static boolean todosOsCamposPreenchidos(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                // Verifica se o campo esta desabilitado e ignora a validação
                if (!textField.isEnabled()) {
                    continue; // Pula a validação para este campo, ID pode estar vazio
                } else if (textField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, " atributo obrigatório.");
                    textField.requestFocus();
                    return false;
                }
            } else if (component instanceof JFormattedTextField) {
                JFormattedTextField formattedTextField = (JFormattedTextField) component;
                if (formattedTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "atributo obrigatório.");
                    formattedTextField.requestFocus();
                    return false;
                }
            } else if (component instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) component;
                if (comboBox.getSelectedItem() == null || comboBox.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null, "atributo obrigatório.");
                    comboBox.requestFocus();
                    return false;
                }
            } else if (component instanceof JPasswordField) {
                JPasswordField passwordField = (JPasswordField) component;
                if (passwordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "atributo obrigatório.");
                    passwordField.requestFocus();
                    return false;
                }
            } else if (component instanceof JTextArea) {
                JTextArea textArea = (JTextArea) component;
                if (textArea.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "atributo obrigatório.");
                    textArea.requestFocus();
                    return false;
                }
            } else if (component instanceof JSpinner) {
                JSpinner spinner = (JSpinner) component;
                if (spinner.getValue() == null || spinner.getValue().toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "atributo obrigatório.");
                    spinner.requestFocus();
                    return false;
                }
            }
        }
        return true; // Todos os campos estão preenchidos
    }

}
