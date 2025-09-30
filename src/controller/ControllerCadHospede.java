package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Hospede;
import view.TelaBuscaHospede;
import view.TelaCadastroHospede;

public class ControllerCadHospede implements ActionListener {

    TelaCadastroHospede telaCadastroHospede;
    public static int codigo;

    public ControllerCadHospede(TelaCadastroHospede telaCadastroHospede) {

        this.telaCadastroHospede = telaCadastroHospede;

        this.telaCadastroHospede.getjButtonNovo().addActionListener(this);
        this.telaCadastroHospede.getjButtonCancelar().addActionListener(this);
        this.telaCadastroHospede.getjButtonGravar().addActionListener(this);
        this.telaCadastroHospede.getjButtonBuscar().addActionListener(this);
        this.telaCadastroHospede.getjButtonSair().addActionListener(this);

        //Desenvolver as setagens de situação inicial dos componentes
        /*this.telaCadastroHospede.getjButtonNovo().setEnabled(true);
        this.telaCadastroHospede.getjButtonCancelar().setEnabled(false);
        this.telaCadastroHospede.getjButtonGravar().setEnabled(false);
        this.telaCadastroHospede.getjButtonBuscar().setEnabled(true);
        this.telaCadastroHospede.getjButtonSair().setEnabled(true);*/
        utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroHospede.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanelDados(), true);
            //Data atual colocada em data de cadastro
            this.telaCadastroHospede.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonGravar()) {
            /*if (this.telaCadastroHospede.getjTextFieldNomeFantasia().getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Atributo Obrigatório.");
                this.telaCadastroHospede.getjTextFieldNomeFantasia().requestFocus();
            } else {}*/
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroHospede.getjPanelDados())){
                Hospede hospede = new Hospede();

                hospede.setCep(this.telaCadastroHospede.getjFormattedTextFieldCep().getText());
                hospede.setCnpj(this.telaCadastroHospede.getjFormattedTextFieldCnpj().getText());
                hospede.setCpf(this.telaCadastroHospede.getjFormattedTextFieldCpf().getText());
                hospede.setDataCadastro(this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().getText());
                hospede.setBairro(this.telaCadastroHospede.getjTextFieldBairro().getText());
                hospede.setCidade(this.telaCadastroHospede.getjTextFieldCidade().getText());
                hospede.setComplemento(this.telaCadastroHospede.getjTextFieldComplemento().getText());
                hospede.setContato(this.telaCadastroHospede.getjTextFieldContato().getText());
                hospede.setEmail(this.telaCadastroHospede.getjTextFieldEmail().getText());
                hospede.setInscricaoEstadual(this.telaCadastroHospede.getjTextFieldInscricaoEstadual().getText());
                hospede.setLogradouro(this.telaCadastroHospede.getjTextFieldLogradouro().getText());
                hospede.setNome(this.telaCadastroHospede.getjTextFieldNomeFantasia().getText());
                hospede.setFone1(this.telaCadastroHospede.getjFormattedTextFieldFone1().getText());
                hospede.setFone2(this.telaCadastroHospede.getjFormattedTextFieldFone2().getText());
                hospede.setObs(this.telaCadastroHospede.getjTextFieldObs().getText());
                hospede.setRazaoSocial(this.telaCadastroHospede.getjTextFieldRazaoSocial().getText());
                hospede.setRg(this.telaCadastroHospede.getjTextFieldRg().getText());

                if (this.telaCadastroHospede.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    hospede.setStatus('A');
                    service.HospedeService.Criar(hospede);
                } else {
                    //Atualização
                    hospede.setId(Integer.parseInt(this.telaCadastroHospede.getjTextFieldId().getText()));
                    service.HospedeService.Atualizar(hospede);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaHospede telaBuscaHospede = new TelaBuscaHospede(null, true);
            ControllerBuscaHospede controllerBuscaHospede = new ControllerBuscaHospede(telaBuscaHospede);
            telaBuscaHospede.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de hospede
                utilities.Utilities.ativaDesativa(this.telaCadastroHospede.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroHospede.getjPanelDados(), true);

                this.telaCadastroHospede.getjTextFieldId().setText(codigo + "");
                this.telaCadastroHospede.getjTextFieldId().setEnabled(false);

                Hospede hospede = new Hospede();
                hospede = service.HospedeService.Carregar(codigo);

                this.telaCadastroHospede.getjFormattedTextFieldCep().setText(hospede.getCep());
                this.telaCadastroHospede.getjFormattedTextFieldCnpj().setText(hospede.getCnpj());
                this.telaCadastroHospede.getjFormattedTextFieldCpf().setText(hospede.getCpf());
                this.telaCadastroHospede.getjFormattedTextFieldDataCadastro().setText(hospede.getDataCadastro());
                this.telaCadastroHospede.getjTextFieldBairro().setText(hospede.getBairro());
                this.telaCadastroHospede.getjTextFieldCidade().setText(hospede.getCidade());
                this.telaCadastroHospede.getjTextFieldComplemento().setText(hospede.getComplemento());
                this.telaCadastroHospede.getjTextFieldContato().setText(hospede.getContato());
                this.telaCadastroHospede.getjTextFieldEmail().setText(hospede.getEmail());
                this.telaCadastroHospede.getjTextFieldId().setText(hospede.getId() + "");
                this.telaCadastroHospede.getjTextFieldInscricaoEstadual().setText(hospede.getInscricaoEstadual());
                this.telaCadastroHospede.getjTextFieldLogradouro().setText(hospede.getLogradouro());
                this.telaCadastroHospede.getjTextFieldNomeFantasia().setText(hospede.getNome());
                this.telaCadastroHospede.getjFormattedTextFieldFone1().setText(hospede.getFone1());
                this.telaCadastroHospede.getjFormattedTextFieldFone2().setText(hospede.getFone2());
                this.telaCadastroHospede.getjTextFieldObs().setText(hospede.getObs());
                this.telaCadastroHospede.getjTextFieldRazaoSocial().setText(hospede.getRazaoSocial());
                this.telaCadastroHospede.getjTextFieldRg().setText(hospede.getRg());

                this.telaCadastroHospede.getjTextFieldNomeFantasia().requestFocus();
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroHospede.getjButtonSair()) {
            this.telaCadastroHospede.dispose();
        }
    }
}
