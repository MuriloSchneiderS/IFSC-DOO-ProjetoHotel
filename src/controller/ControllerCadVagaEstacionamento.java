package controller;

import static controller.ControllerCadVagaEstacionamento.codigo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.VagaEstacionamento;
import view.TelaBuscaVagaEstacionamento;
import view.TelaCadastroVagaEstacionamento;

public class ControllerCadVagaEstacionamento implements ActionListener {

    TelaCadastroVagaEstacionamento telaCadastroVagaEstacionamento;
    public static int codigo;

    public ControllerCadVagaEstacionamento(TelaCadastroVagaEstacionamento telaCadastroVagaEstacionamento) {

        this.telaCadastroVagaEstacionamento = telaCadastroVagaEstacionamento;

        this.telaCadastroVagaEstacionamento.getjButtonNovo().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonGravar().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonBuscar().addActionListener(this);
        this.telaCadastroVagaEstacionamento.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanelDados(), true);
            this.telaCadastroVagaEstacionamento.getjTextFieldId().setEnabled(false);
            //Data atual colocada em data de cadastro
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroVagaEstacionamento.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroVagaEstacionamento.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonGravar()) {
            if (this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Atributo Obrigatório.");
                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().requestFocus();
            } else {
                VagaEstacionamento vaga_estacionamento = new VagaEstacionamento();

                vaga_estacionamento.setDescricao(this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().getText());
                vaga_estacionamento.setObs(this.telaCadastroVagaEstacionamento.getjTextFieldObs().getText());
                vaga_estacionamento.setMetragemVaga((float) this.telaCadastroVagaEstacionamento.getjSpinnerMetragemVaga().getValue());
                vaga_estacionamento.setStatus('A');
                
                if (this.telaCadastroVagaEstacionamento.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    vaga_estacionamento.setStatus('A');
                    service.VagaEstacionamentoService.Criar(vaga_estacionamento);
                } else {
                    //Atualização
                    vaga_estacionamento.setId(Integer.parseInt(this.telaCadastroVagaEstacionamento.getjTextFieldId().getText()));
                    service.VagaEstacionamentoService.Atualizar(vaga_estacionamento);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaVagaEstacionamento telaBuscaVagaEstacionamento = new TelaBuscaVagaEstacionamento(null, true);
            ControllerBuscaVagaEstacionamento controllerBuscaVagaEstacionamento = new ControllerBuscaVagaEstacionamento(telaBuscaVagaEstacionamento);
            telaBuscaVagaEstacionamento.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de vaga_estacionamento
                utilities.Utilities.ativaDesativa(this.telaCadastroVagaEstacionamento.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroVagaEstacionamento.getjPanelDados(), true);

                this.telaCadastroVagaEstacionamento.getjTextFieldId().setText(codigo + "");
                this.telaCadastroVagaEstacionamento.getjTextFieldId().setEnabled(false);

                VagaEstacionamento vaga_estacionamento = new VagaEstacionamento();
                vaga_estacionamento = service.VagaEstacionamentoService.Carregar(codigo);

                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().setText(vaga_estacionamento.getDescricao());
                this.telaCadastroVagaEstacionamento.getjTextFieldObs().setText(vaga_estacionamento.getObs());
                this.telaCadastroVagaEstacionamento.getjSpinnerMetragemVaga().setValue(vaga_estacionamento.getMetragemVaga());
                
                this.telaCadastroVagaEstacionamento.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroVagaEstacionamento.getjButtonSair()) {
            this.telaCadastroVagaEstacionamento.dispose();
        }
    }
}
