package controller;

import static controller.ControllerCadVeiculo.codigo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Veiculo;
import view.TelaBuscaVeiculo;
import view.TelaCadastroVeiculo;

public class ControllerCadVeiculo implements ActionListener {

    TelaCadastroVeiculo telaCadastroVeiculo;
    public static int codigo;

    public ControllerCadVeiculo(TelaCadastroVeiculo telaCadastroVeiculo) {

        this.telaCadastroVeiculo = telaCadastroVeiculo;

        this.telaCadastroVeiculo.getjButtonNovo().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonCancelar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonGravar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonBuscar().addActionListener(this);
        this.telaCadastroVeiculo.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaCadastroVeiculo.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), true);
            this.telaCadastroVeiculo.getjTextFieldId().setEnabled(false);
            //Data atual colocada em data de cadastro
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroVeiculo.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroVeiculo.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonGravar()) {
            if (this.telaCadastroVeiculo.getjTextFieldPlaca().getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Atributo Obrigatório.");
                this.telaCadastroVeiculo.getjTextFieldPlaca().requestFocus();
            } else {
                Veiculo veiculo = new Veiculo();

                veiculo.setPlaca(this.telaCadastroVeiculo.getjTextFieldPlaca().getText());
                veiculo.setCor(this.telaCadastroVeiculo.getjTextFieldCor().getText());
                veiculo.setModelo(service.ModeloService.Carregar("descricao", this.telaCadastroVeiculo.getjComboBoxModelo().getSelectedItem().toString()).getFirst());
                veiculo.setFuncionario(service.FuncionarioService.Carregar("nome", this.telaCadastroVeiculo.getjTextFieldFuncionario().getText()).getFirst());
                veiculo.setFornecedor(service.FornecedorService.Carregar("nome", this.telaCadastroVeiculo.getjTextFieldFornecedor().getText()).getFirst());
                veiculo.setHospede(service.HospedeService.Carregar("nome", this.telaCadastroVeiculo.getjTextFieldHospede().getText()).getFirst());
                
                if (this.telaCadastroVeiculo.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    veiculo.setStatus('A');
                    service.VeiculoService.Criar(veiculo);
                } else {
                    //Atualização
                    veiculo.setId(Integer.parseInt(this.telaCadastroVeiculo.getjTextFieldId().getText()));
                    service.VeiculoService.Atualizar(veiculo);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), false);
            }
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaVeiculo telaBuscaVeiculo = new TelaBuscaVeiculo(null, true);
            ControllerBuscaVeiculo controllerBuscaVeiculo = new ControllerBuscaVeiculo(telaBuscaVeiculo);
            telaBuscaVeiculo.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de veiculo
                utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), true);

                this.telaCadastroVeiculo.getjTextFieldId().setText(codigo + "");
                this.telaCadastroVeiculo.getjTextFieldId().setEnabled(false);

                Veiculo veiculo = new Veiculo();
                veiculo = service.VeiculoService.Carregar(codigo);

                this.telaCadastroVeiculo.getjTextFieldPlaca().setText(veiculo.getPlaca());
                this.telaCadastroVeiculo.getjTextFieldCor().setText(veiculo.getCor());
                this.telaCadastroVeiculo.getjComboBoxModelo().setSelectedItem(veiculo.getModelo().getDescricao());
                this.telaCadastroVeiculo.getjTextFieldFuncionario().setText(veiculo.getFuncionario().getNome());
                this.telaCadastroVeiculo.getjTextFieldFornecedor().setText(veiculo.getFornecedor().getNome());
                this.telaCadastroVeiculo.getjTextFieldHospede().setText(veiculo.getHospede().getNome());
                
                this.telaCadastroVeiculo.getjTextFieldPlaca().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonSair()) {
            this.telaCadastroVeiculo.dispose();
        }
    }
}
