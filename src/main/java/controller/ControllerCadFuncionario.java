package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bo.Funcionario;
import view.TelaBuscaFuncionario;
import view.TelaCadastroFuncionario;

public class ControllerCadFuncionario implements ActionListener {

    TelaCadastroFuncionario telaCadastroFuncionario;
    public static int codigo;

    public ControllerCadFuncionario(TelaCadastroFuncionario telaCadastroFuncionario) {

        this.telaCadastroFuncionario = telaCadastroFuncionario;

        this.telaCadastroFuncionario.getjButtonNovo().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonCancelar().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonGravar().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonBuscar().addActionListener(this);
        this.telaCadastroFuncionario.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroFuncionario.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanelDados(), true);
            //Data atual colocada em data de cadastro
            this.telaCadastroFuncionario.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroFuncionario.getjPanelDados())) {
                String cpf = this.telaCadastroFuncionario.getjFormattedTextFieldCpf().getText();
                if(!utilities.Utilities.validaCPF(cpf)){
                    JOptionPane.showMessageDialog(this.telaCadastroFuncionario, "CPF inválido. Por favor, verifique e tente novamente.");
                    return;//Interrompe a execução se o CPF for invalido
                }
                
                Funcionario funcionario = new Funcionario();

                funcionario.setCep(this.telaCadastroFuncionario.getjFormattedTextFieldCep().getText());
                funcionario.setCpf(cpf);
                funcionario.setDataCadastro(this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().getText());
                funcionario.setBairro(this.telaCadastroFuncionario.getjTextFieldBairro().getText());
                funcionario.setCidade(this.telaCadastroFuncionario.getjTextFieldCidade().getText());
                funcionario.setComplemento(this.telaCadastroFuncionario.getjTextFieldComplemento().getText());
                funcionario.setEmail(this.telaCadastroFuncionario.getjTextFieldEmail().getText());
                funcionario.setLogradouro(this.telaCadastroFuncionario.getjTextFieldLogradouro().getText());
                funcionario.setNome(this.telaCadastroFuncionario.getjTextFieldNomeFantasia().getText());
                funcionario.setFone1(this.telaCadastroFuncionario.getjFormattedTextFieldFone1().getText());
                funcionario.setFone2(this.telaCadastroFuncionario.getjFormattedTextFieldFone2().getText());
                funcionario.setObs(this.telaCadastroFuncionario.getjTextFieldObs().getText());
                funcionario.setRg(this.telaCadastroFuncionario.getjFormattedTextFieldRg().getText());
                funcionario.setUsuario(this.telaCadastroFuncionario.getjTextFieldUsuario().getText());
                funcionario.setSenha(this.telaCadastroFuncionario.getjPasswordFieldSenha().getPassword());

                if (this.telaCadastroFuncionario.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    funcionario.setStatus('A');
                    service.FuncionarioService.Criar(funcionario);
                } else {
                    //Atualização
                    funcionario.setId(Integer.parseInt(this.telaCadastroFuncionario.getjTextFieldId().getText()));
                    service.FuncionarioService.Atualizar(funcionario);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaFuncionario telaBuscaFuncionario = new TelaBuscaFuncionario(null, true);
            ControllerBuscaFuncionario controllerBuscaFuncionario = new ControllerBuscaFuncionario(telaBuscaFuncionario);
            telaBuscaFuncionario.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de funcionario
                utilities.Utilities.ativaDesativa(this.telaCadastroFuncionario.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroFuncionario.getjPanelDados(), true);

                this.telaCadastroFuncionario.getjTextFieldId().setText(codigo + "");
                this.telaCadastroFuncionario.getjTextFieldId().setEnabled(false);

                Funcionario funcionario = new Funcionario();
                funcionario = service.FuncionarioService.Carregar(codigo);

                this.telaCadastroFuncionario.getjFormattedTextFieldCep().setText(funcionario.getCep());
                this.telaCadastroFuncionario.getjFormattedTextFieldCpf().setText(funcionario.getCpf());
                
                    try {
                        this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setText(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(funcionario.getDataCadastro())));
                    } catch (ParseException ex) {
                        Logger.getLogger(ControllerCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                this.telaCadastroFuncionario.getjFormattedTextFieldDataCadastro().setEnabled(false);
                this.telaCadastroFuncionario.getjTextFieldBairro().setText(funcionario.getBairro());
                this.telaCadastroFuncionario.getjTextFieldCidade().setText(funcionario.getCidade());
                this.telaCadastroFuncionario.getjTextFieldComplemento().setText(funcionario.getComplemento());
                this.telaCadastroFuncionario.getjTextFieldEmail().setText(funcionario.getEmail());
                this.telaCadastroFuncionario.getjTextFieldId().setText(funcionario.getId() + "");
                this.telaCadastroFuncionario.getjTextFieldLogradouro().setText(funcionario.getLogradouro());
                this.telaCadastroFuncionario.getjTextFieldNomeFantasia().setText(funcionario.getNome());
                this.telaCadastroFuncionario.getjFormattedTextFieldFone1().setText(funcionario.getFone1());
                this.telaCadastroFuncionario.getjFormattedTextFieldFone2().setText(funcionario.getFone2());
                this.telaCadastroFuncionario.getjTextFieldObs().setText(funcionario.getObs());
                this.telaCadastroFuncionario.getjFormattedTextFieldRg().setText(funcionario.getRg());
                this.telaCadastroFuncionario.getjTextFieldUsuario().setText(funcionario.getUsuario());
                this.telaCadastroFuncionario.getjPasswordFieldSenha().setText(new String(funcionario.getSenha()));
                
                this.telaCadastroFuncionario.getjTextFieldNomeFantasia().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroFuncionario.getjButtonSair()) {
            this.telaCadastroFuncionario.dispose();
        }
    }
}
