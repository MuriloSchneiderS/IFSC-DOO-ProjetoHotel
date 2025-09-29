package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Marca;
import view.TelaBuscaMarca;
import view.TelaCadastroMarca;

public class ControllerCadMarca implements ActionListener {

    TelaCadastroMarca telaCadastroMarca;
    public static int codigo;

    public ControllerCadMarca(TelaCadastroMarca telaCadastroMarca) {

        this.telaCadastroMarca = telaCadastroMarca;

        this.telaCadastroMarca.getjButtonNovo().addActionListener(this);
        this.telaCadastroMarca.getjButtonCancelar().addActionListener(this);
        this.telaCadastroMarca.getjButtonGravar().addActionListener(this);
        this.telaCadastroMarca.getjButtonBuscar().addActionListener(this);
        this.telaCadastroMarca.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroMarca.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanelDados(), true);
            this.telaCadastroMarca.getjTextFieldId().setEnabled(false);
            //Data atual colocada em data de cadastro
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroMarca.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroMarca.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonGravar()) {
            if (this.telaCadastroMarca.getjTextFieldDescricao().getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Atributo Obrigatório.");
                this.telaCadastroMarca.getjTextFieldDescricao().requestFocus();
            } else {
                Marca marca = new Marca();

                marca.setDescricao(this.telaCadastroMarca.getjTextFieldDescricao().getText());
                
                if (this.telaCadastroMarca.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    marca.setStatus('A');
                    service.MarcaService.Criar(marca);
                } else {
                    //Atualização
                    marca.setId(Integer.parseInt(this.telaCadastroMarca.getjTextFieldId().getText()));
                    service.MarcaService.Atualizar(marca);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaMarca telaBuscaMarca = new TelaBuscaMarca(null, true);
            ControllerBuscaMarca controllerBuscaMarca = new ControllerBuscaMarca(telaBuscaMarca);
            telaBuscaMarca.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de marca
                utilities.Utilities.ativaDesativa(this.telaCadastroMarca.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroMarca.getjPanelDados(), true);

                this.telaCadastroMarca.getjTextFieldId().setText(codigo + "");
                this.telaCadastroMarca.getjTextFieldId().setEnabled(false);

                Marca marca = new Marca();
                marca = service.MarcaService.Carregar(codigo);

                this.telaCadastroMarca.getjTextFieldDescricao().setText(marca.getDescricao());
                
                this.telaCadastroMarca.getjTextFieldDescricao().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroMarca.getjButtonSair()) {
            this.telaCadastroMarca.dispose();
        }
    }
}
