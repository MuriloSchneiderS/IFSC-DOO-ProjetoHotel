package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Servico;
import view.TelaBuscaServico;
import view.TelaCadastroServico;

public class ControllerCadServico implements ActionListener {

    TelaCadastroServico telaCadastroServico;
    public static int codigo;

    public ControllerCadServico(TelaCadastroServico telaCadastroServico) {

        this.telaCadastroServico = telaCadastroServico;

        this.telaCadastroServico.getjButtonNovo().addActionListener(this);
        this.telaCadastroServico.getjButtonCancelar().addActionListener(this);
        this.telaCadastroServico.getjButtonGravar().addActionListener(this);
        this.telaCadastroServico.getjButtonBuscar().addActionListener(this);
        this.telaCadastroServico.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroServico.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanelDados(), true);
            this.telaCadastroServico.getjTextFieldId().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroServico.getjPanelDados())) {
                Servico servico = new Servico();
                
                servico.setDescricao(this.telaCadastroServico.getjTextFieldDescricao().getText());
                servico.setObs(this.telaCadastroServico.getjTextFieldDescricao().getText());
                
                if (this.telaCadastroServico.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    servico.setStatus('A');
                    service.ServicoService.Criar(servico);
                } else {
                    //Atualização
                    servico.setId(Integer.parseInt(this.telaCadastroServico.getjTextFieldId().getText()));
                    service.ServicoService.Atualizar(servico);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaServico telaBuscaServico = new TelaBuscaServico(null, true);
            ControllerBuscaServico controllerBuscaServico = new ControllerBuscaServico(telaBuscaServico);
            telaBuscaServico.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de servico
                utilities.Utilities.ativaDesativa(this.telaCadastroServico.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroServico.getjPanelDados(), true);

                this.telaCadastroServico.getjTextFieldId().setText(codigo + "");
                this.telaCadastroServico.getjTextFieldId().setEnabled(false);

                Servico servico = new Servico();
                servico = service.ServicoService.Carregar(codigo);

                this.telaCadastroServico.getjTextFieldDescricao().setText(servico.getDescricao());
                this.telaCadastroServico.getjTextFieldObs().setText(servico.getObs());
                
                this.telaCadastroServico.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroServico.getjButtonSair()) {
            this.telaCadastroServico.dispose();
        }
    }
}
