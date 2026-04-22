package utilities;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

    public static boolean validaCPF(String cpf) {
        char[] digitosCPF = cpf.replaceAll("[.-]", "").toCharArray();
        // rejeita CPFs com tamanho incorreto, caracteres não numéricos ou todos iguais
        if (digitosCPF.length != 11) {
            return false;
        }
        for (char c : digitosCPF) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        boolean todosIguais = true;
        for (int i = 1; i < digitosCPF.length; i++) {
            if (digitosCPF[i] != digitosCPF[0]) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        //primeiro digito verificador
        int valorTotal = 0;
        for (int i = 0, multiplicador = 10; i < 9; i++, multiplicador--) {//1º digito X 10, 2º X 9... 9º X 2
            valorTotal += (digitosCPF[i] - '0'/*transforma de char para int*/) * multiplicador;
        }
        int primeiroDigitoVerificador = 11 - valorTotal % 11;
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }
        boolean primeiraVerificacao = primeiroDigitoVerificador == (digitosCPF[9] - '0');

        //segundo digito verificador
        valorTotal = 0;
        for (int i = 0, multiplicador = 11; i < 10; i++, multiplicador--) {//1º digito X 11, 2º X 10... 10º X 2
            valorTotal += (digitosCPF[i] - '0') * multiplicador;
        }
        int segundoDigitoVerificador = 11 - valorTotal % 11;
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }
        boolean segundaVerificacao = segundoDigitoVerificador == (digitosCPF[10] - '0');

        return primeiraVerificacao && segundaVerificacao;
    }

    public static boolean validaCNPJ(String cnpj) {
        char[] digitosCNPJ = cnpj.replaceAll("[./-]", "").toCharArray();
        // rejeita CNPJs com tamanho incorreto, caracteres não numéricos ou todos iguais
        if (digitosCNPJ.length != 14) {
            return false;
        }
        for (char c : digitosCNPJ) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        boolean todosIguais = true;
        for (int i = 1; i < digitosCNPJ.length; i++) {
            if (digitosCNPJ[i] != digitosCNPJ[0]) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        //Primeira verificação: inverte o número de CNPJ e adiciona pesos de 2 a 9:
        int valorTotal = 0;
        for (int i = 11, peso = 2; i >= 0; i--, peso++) {//12º digito X 2, 11º X 3... 5º X 9, 4º X 2, 3º X 3, 2º X 4, 1º X 5.
            if (peso == 10) {
                peso = 2;
            }
            valorTotal += (digitosCNPJ[i] - '0') * peso;
        }
        int primeiroDigitoVerificador = 11 - valorTotal % 11;
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }
        boolean primeiraVerificacao = primeiroDigitoVerificador == (digitosCNPJ[12] - '0');

        //Segunda verificação: Mesma regra do primeiro dígito, mas agora a partir do 13º número:
        valorTotal = 0;
        for (int i = 12, peso = 2; i >= 0; i--, peso++) {//13º digito X 2, 12º X 3... 6º X 9, 5º X 2, 4º X 3, 3º X 4, 2º X 5, 1º X 6.
            if (peso == 10) {
                peso = 2;
            }
            valorTotal += (digitosCNPJ[i] - '0') * peso;
        }
        int segundoDigitoVerificador = 11 - valorTotal % 11;
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }
        boolean segundaVerificacao = segundoDigitoVerificador == (digitosCNPJ[13] - '0');

        return primeiraVerificacao && segundaVerificacao;
    }

    //Data Valida
    public static boolean validaData(String data) {//"DD/MM/YYYY"
        //FORMATO
        if (data == null || !data.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        String[] dataArray = data.split("/");
        int dia = Integer.parseInt(dataArray[0]), mes = Integer.parseInt(dataArray[1]), ano = Integer.parseInt(dataArray[2]);

        //ANO
        if (ano < 1) {
            return false;
        }
        boolean isBissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
        //MES
        if (mes < 1 || mes > 12) {
            return false;
        }
        //DIA
        int[] totDiasDoMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//Total de dias de cada mes em ordem
        if (mes == 2 && isBissexto) {
            totDiasDoMes[2] = 29;//Fevereiro bissexto
        }

        return dia > 0 && dia <= totDiasDoMes[mes];
    }

    //Compara Datas
    public static int comparaDatas(String dataString1, String dataString2) {
        //0: iguais
        //1: 1 > 2 - data 1 é posterior
        //2: 2 > 1 - data 2 é posterior

        if (!validaData(dataString1) || !validaData(dataString2)) {
            JOptionPane.showMessageDialog(null, "Data invalida.");
        }

        String[] partes1 = dataString1.split("/");
        int dia1 = Integer.parseInt(partes1[0]);
        int mes1 = Integer.parseInt(partes1[1]);
        int ano1 = Integer.parseInt(partes1[2]);

        String[] partes2 = dataString2.split("/");
        int dia2 = Integer.parseInt(partes2[0]);
        int mes2 = Integer.parseInt(partes2[1]);
        int ano2 = Integer.parseInt(partes2[2]);

        //ANO
        if (ano1 != ano2) {
            return (ano1 < ano2) ? 2 : 1;
        }
        //MES
        if (mes1 != mes2) {
            return (mes1 < mes2) ? 2 : 1;
        }
        //DIA
        if (dia1 != dia2) {
            return (dia1 < dia2) ? 2 : 1;
        }

        return 0;
    }

    public static String formataDataParaMySQL(String data) {
        if (data == null || data.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SimpleDateFormat entrada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat mysql = new SimpleDateFormat("yyyy-MM-dd");
        entrada.setLenient(false);

        try {
            Date d = entrada.parse(data); // falha se inválida (ex: 31/02)
            return mysql.format(d);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Data inválida. Use o formato dd/MM/yyyy e verifique valores.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    public static String formataDataHoraParaMySQL(String dataHora) {
        if (dataHora == null || dataHora.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data/hora vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (dataHora.startsWith("00/00/0000")) {
            return "9999-01-01 00:00:00";//BD não aceita datas nulas, esta substitui, sendo uma data impossivel
        }
        
        //Formato dd/MM/yyyy HH:mm:ss
        SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        formato1.setLenient(false);

        //Formato de new Date().toString() -> "EEE MMM dd HH:mm:ss zzz yyyy"
        SimpleDateFormat formato2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        formato2.setLenient(false);

        SimpleDateFormat mysql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Tenta primeiro formato
        try {
            Date d = formato1.parse(dataHora);
            return mysql.format(d);
        } catch (ParseException ex1) {
            //Tenta segundo formato
            try {
                Date d = formato2.parse(dataHora);
                return mysql.format(d);
            } catch (ParseException ex2) {
                JOptionPane.showMessageDialog(
                        null,
                        "Data/hora inválida. Use um dos formatos:\n"
                        + "• dd/MM/yyyy HH:mm:ss\n"
                        + "• EEE MMM dd HH:mm:ss zzz yyyy",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }
    }

    public static String formataDataDeMySQL(String dataMySQL) {
        if (dataMySQL == null || dataMySQL.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        SimpleDateFormat mysql = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat br = new SimpleDateFormat("dd/MM/yyyy");
        mysql.setLenient(false);

        try {
            Date d = mysql.parse(dataMySQL); // falha se inválida
            return br.format(d);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Data inválida. Use o formato yyyy-MM-dd e verifique valores.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    public static String formataDataHoraDeMySQL(String dataHoraMySQL) {
        if (dataHoraMySQL == null || dataHoraMySQL.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data/hora vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        //Verifica se é a data "nula" (9999-01-01)
        if (dataHoraMySQL.startsWith("9999-01-01")) {
            return "00/00/0000 00:00:00";
        }
        SimpleDateFormat formatoAlvo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataHora = null;

        //Lista de formatos possíveis do MySQL
        String[] formatosPossiveis = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd"
        };
        //Tenta fazer o parse com cada formato
        for (String formatoAtual : formatosPossiveis) {
            SimpleDateFormat formato = new SimpleDateFormat(formatoAtual);
            formato.setLenient(false);

            try {
                dataHora = formato.parse(dataHoraMySQL);
                return formatoAlvo.format(dataHora);
            } catch (ParseException ex) {
                continue;//Continua tentando o próximo formato se falhar
            }
        }
        //Se nenhum formato funcionar
        JOptionPane.showMessageDialog(
                null,
                "Formato de Data/Hora inválido! ",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
        return null;
    }
}
