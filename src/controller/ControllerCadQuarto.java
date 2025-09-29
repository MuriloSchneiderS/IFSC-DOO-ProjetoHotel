package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Quarto;
import view.TelaBuscaQuarto;
import view.TelaCadastroQuarto;

public class ControllerCadQuarto implements ActionListener {

    TelaCadastroQuarto telaCadastroQuarto;
    public static int codigo;

    public ControllerCadQuarto(TelaCadastroQuarto telaCadastroQuarto) {

        this.telaCadastroQuarto = telaCadastroQuarto;

        this.telaCadastroQuarto.getjButtonNovo().addActionListener(this);
        this.telaCadastroQuarto.getjButtonCancelar().addActionListener(this);
        this.telaCadastroQuarto.getjButtonGravar().addActionListener(this);
        this.telaCadastroQuarto.getjButtonBuscar().addActionListener(this);
        this.telaCadastroQuarto.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroQuarto.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanelDados(), true);
            this.telaCadastroQuarto.getjTextFieldId().setEnabled(false);
            //Data atual colocada em data de cadastro
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroQuarto.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroQuarto.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonGravar()) {
            if (this.telaCadastroQuarto.getjTextFieldDescricao().getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Atributo Obrigatório.");
                this.telaCadastroQuarto.getjTextFieldDescricao().requestFocus();
            } else {
                Quarto quarto = new Quarto();

                quarto.setDescricao(this.telaCadastroQuarto.getjTextFieldDescricao().getText());
                quarto.setCapacidadeHospedes((int) this.telaCadastroQuarto.getjSpinnerCapacidadeHospedes().getValue());
                quarto.setMetragem((float) this.telaCadastroQuarto.getjSpinnerMetragem().getValue());
                quarto.setIdentificacao(this.telaCadastroQuarto.getjTextFieldIdentificacao().getText());
                quarto.setAndar((int) this.telaCadastroQuarto.getjSpinnerAndar().getValue());
                quarto.setFlagAnimais(this.telaCadastroQuarto.getjCheckBoxPermiteAnimais().isSelected()?1:0);
                quarto.setObs(this.telaCadastroQuarto.getjTextFieldObs().getText());
                
                if (this.telaCadastroQuarto.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    quarto.setStatus('A');
                    service.QuartoService.Criar(quarto);
                } else {
                    //Atualização
                    quarto.setStatus('B');
                    quarto.setId(Integer.parseInt(this.telaCadastroQuarto.getjTextFieldId().getText()));
                    service.QuartoService.Atualizar(quarto);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaQuarto telaBuscaQuarto = new TelaBuscaQuarto(null, true);
            ControllerBuscaQuarto controllerBuscaQuarto = new ControllerBuscaQuarto(telaBuscaQuarto);
            telaBuscaQuarto.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de quarto
                utilities.Utilities.ativaDesativa(this.telaCadastroQuarto.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroQuarto.getjPanelDados(), true);

                this.telaCadastroQuarto.getjTextFieldId().setText(codigo + "");
                this.telaCadastroQuarto.getjTextFieldId().setEnabled(false);

                Quarto quarto = new Quarto();
                quarto = service.QuartoService.Carregar(codigo);

                this.telaCadastroQuarto.getjTextFieldId().setText(quarto.getId()+"");
                this.telaCadastroQuarto.getjTextFieldDescricao().setText(quarto.getDescricao());
                this.telaCadastroQuarto.getjSpinnerCapacidadeHospedes().setValue(quarto.getCapacidadeHospedes());
                this.telaCadastroQuarto.getjSpinnerMetragem().setValue(quarto.getMetragem());
                this.telaCadastroQuarto.getjTextFieldIdentificacao().setText(quarto.getIdentificacao());
                this.telaCadastroQuarto.getjSpinnerAndar().setValue(quarto.getAndar());
                this.telaCadastroQuarto.getjCheckBoxPermiteAnimais().setSelected(quarto.getFlagAnimais() == 1);
                this.telaCadastroQuarto.getjTextFieldObs().setText(quarto.getObs());
                
                this.telaCadastroQuarto.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroQuarto.getjButtonSair()) {
            this.telaCadastroQuarto.dispose();
        }
    }
}
