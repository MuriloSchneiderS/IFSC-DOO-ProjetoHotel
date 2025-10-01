package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Marca;
import model.Modelo;
import view.TelaBuscaModelo;
import view.TelaCadastroModelo;

public class ControllerCadModelo implements ActionListener {

    TelaCadastroModelo telaCadastroModelo;
    public static int codigo;

    public ControllerCadModelo(TelaCadastroModelo telaCadastroModelo) {

        this.telaCadastroModelo = telaCadastroModelo;

        this.telaCadastroModelo.getjButtonNovo().addActionListener(this);
        this.telaCadastroModelo.getjButtonCancelar().addActionListener(this);
        this.telaCadastroModelo.getjButtonGravar().addActionListener(this);
        this.telaCadastroModelo.getjButtonBuscar().addActionListener(this);
        this.telaCadastroModelo.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanelDados(), false);
        
        //Carregar o campo Marca
        List<Marca> marcas = service.MarcaService.Carregar("descricao", "%");
        for(Marca marca : marcas){
            this.telaCadastroModelo.getjComboBoxMarca().addItem(marca.getDescricao());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroModelo.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanelDados(), true);
            this.telaCadastroModelo.getjTextFieldId().setEnabled(false);
            
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroModelo.getjPanelDados())) {
                Modelo modelo = new Modelo();
                
                modelo.setDescricao(this.telaCadastroModelo.getjTextFieldDescricao().getText());
                modelo.setMarca(service.MarcaService.Carregar("descricao", this.telaCadastroModelo.getjComboBoxMarca().getSelectedItem().toString()).getFirst());
                
                if (this.telaCadastroModelo.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    modelo.setStatus('A');
                    service.ModeloService.Criar(modelo);
                } else {
                    //Atualização
                    modelo.setId(Integer.parseInt(this.telaCadastroModelo.getjTextFieldId().getText()));
                    service.ModeloService.Atualizar(modelo);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaModelo telaBuscaModelo = new TelaBuscaModelo(null, true);
            ControllerBuscaModelo controllerBuscaModelo = new ControllerBuscaModelo(telaBuscaModelo);
            telaBuscaModelo.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de modelo
                utilities.Utilities.ativaDesativa(this.telaCadastroModelo.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroModelo.getjPanelDados(), true);

                this.telaCadastroModelo.getjTextFieldId().setText(codigo + "");
                this.telaCadastroModelo.getjTextFieldId().setEnabled(false);

                Modelo modelo = new Modelo();
                modelo = service.ModeloService.Carregar(codigo);

                this.telaCadastroModelo.getjTextFieldDescricao().setText(modelo.getDescricao());
                this.telaCadastroModelo.getjComboBoxMarca().setSelectedItem(modelo.getMarca().getDescricao());
                
                this.telaCadastroModelo.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroModelo.getjButtonSair()) {
            this.telaCadastroModelo.dispose();
        }
    }
}
