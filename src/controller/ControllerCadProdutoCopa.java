package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.CopaQuarto;
import model.ProdutoCopa;
import view.TelaBuscaProdutoCopa;
import view.TelaCadastroProdutoCopa;

public class ControllerCadProdutoCopa implements ActionListener {

    TelaCadastroProdutoCopa telaCadastroProdutoCopa;
    public static int codigo;

    public ControllerCadProdutoCopa(TelaCadastroProdutoCopa telaCadastroProdutoCopa) {

        this.telaCadastroProdutoCopa = telaCadastroProdutoCopa;

        this.telaCadastroProdutoCopa.getjButtonNovo().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonCancelar().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonGravar().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonBuscar().addActionListener(this);
        this.telaCadastroProdutoCopa.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanelDados(), false);
        
        //Carregar o campo Marca
        List<CopaQuarto> copaQuartos = service.CopaQuartoService.Carregar("id", "%");
        for(CopaQuarto copaQuarto : copaQuartos){
            this.telaCadastroProdutoCopa.getjComboBoxCopaQuarto().addItem(copaQuarto.getId()+"");
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanelDados(), true);
            this.telaCadastroProdutoCopa.getjTextFieldId().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroProdutoCopa.getjPanelDados())) {
                ProdutoCopa produtoCopa = new ProdutoCopa();
                CopaQuarto copaQuarto = new CopaQuarto();

                produtoCopa.setDescricao(this.telaCadastroProdutoCopa.getjTextFieldDescricao().getText());
                produtoCopa.setValor((float)this.telaCadastroProdutoCopa.getjSpinnerValor().getValue());
                produtoCopa.setObs(this.telaCadastroProdutoCopa.getjTextFieldObs().getText());
                copaQuarto.setId(Integer.parseInt(this.telaCadastroProdutoCopa.getjComboBoxCopaQuarto().getSelectedItem().toString()));
                produtoCopa.setCopaQuarto(copaQuarto);
                        
                if (this.telaCadastroProdutoCopa.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    produtoCopa.setStatus('A');
                    service.ProdutoCopaService.Criar(produtoCopa);
                } else {
                    //Atualização
                    produtoCopa.setId(Integer.parseInt(this.telaCadastroProdutoCopa.getjTextFieldId().getText()));
                    service.ProdutoCopaService.Atualizar(produtoCopa);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaProdutoCopa telaBuscaProdutoCopa = new TelaBuscaProdutoCopa(null, true);
            ControllerBuscaProdutoCopa controllerBuscaProdutoCopa = new ControllerBuscaProdutoCopa(telaBuscaProdutoCopa);
            telaBuscaProdutoCopa.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de produtoCopa
                utilities.Utilities.ativaDesativa(this.telaCadastroProdutoCopa.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroProdutoCopa.getjPanelDados(), true);

                this.telaCadastroProdutoCopa.getjTextFieldId().setText(codigo + "");
                this.telaCadastroProdutoCopa.getjTextFieldId().setEnabled(false);

                ProdutoCopa produtoCopa = new ProdutoCopa();
                produtoCopa = service.ProdutoCopaService.Carregar(codigo);

                this.telaCadastroProdutoCopa.getjTextFieldId().setText(produtoCopa.getId()+"");
                this.telaCadastroProdutoCopa.getjTextFieldDescricao().setText(produtoCopa.getDescricao());
                this.telaCadastroProdutoCopa.getjSpinnerValor().setValue(produtoCopa.getValor());
                this.telaCadastroProdutoCopa.getjTextFieldObs().setText(produtoCopa.getObs());
                this.telaCadastroProdutoCopa.getjComboBoxCopaQuarto().setSelectedItem(produtoCopa.getCopaQuarto().getId()+"");
                
                this.telaCadastroProdutoCopa.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroProdutoCopa.getjButtonSair()) {
            this.telaCadastroProdutoCopa.dispose();
        }
    }
}
