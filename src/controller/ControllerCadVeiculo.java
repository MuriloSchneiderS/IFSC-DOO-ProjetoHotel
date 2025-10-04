package controller;

import static controller.ControllerCadVeiculo.codigo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Fornecedor;
import model.Funcionario;
import model.Hospede;
import model.Modelo;
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
        
        //Carregar o campo Modelo
        List<Modelo> modelos = service.ModeloService.Carregar("descricao", "%");
        for(Modelo modelo : modelos){
            this.telaCadastroVeiculo.getjComboBoxModelo().addItem(modelo.getDescricao());
        }
        //Carregar o campo Funcionario
        List<Funcionario> funcionarios = service.FuncionarioService.Carregar("nome", "%");
        for(Funcionario funcionario : funcionarios){
            this.telaCadastroVeiculo.getjComboBoxFuncionario().addItem(funcionario.getNome());
        }
        //Carregar o campo Fornecedor
        List<Fornecedor> fornecedores = service.FornecedorService.Carregar("nome", "%");
        for(Fornecedor fornecedor : fornecedores){
            this.telaCadastroVeiculo.getjComboBoxFornecedor().addItem(fornecedor.getNome());
        }
        //Carregar o campo Hospede
        List<Hospede> hospedes = service.HospedeService.Carregar("nome", "%");
        for(Hospede hospede : hospedes){
            this.telaCadastroVeiculo.getjComboBoxHospede().addItem(hospede.getNome());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Novo
        if (evento.getSource() == this.telaCadastroVeiculo.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), true);
            this.telaCadastroVeiculo.getjTextFieldId().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroVeiculo.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroVeiculo.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroVeiculo.getjPanelDados())) {
                Veiculo veiculo = new Veiculo();

                veiculo.setPlaca(this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().getText());
                veiculo.setCor(this.telaCadastroVeiculo.getjTextFieldCor().getText());
                veiculo.setModelo(service.ModeloService.Carregar("descricao", this.telaCadastroVeiculo.getjComboBoxModelo().getSelectedItem().toString()).getFirst());
                veiculo.setFuncionario(service.FuncionarioService.Carregar("nome", this.telaCadastroVeiculo.getjComboBoxFuncionario().getSelectedItem().toString()).getFirst());
                veiculo.setFornecedor(service.FornecedorService.Carregar("nome", this.telaCadastroVeiculo.getjComboBoxFornecedor().getSelectedItem().toString()).getFirst());
                veiculo.setHospede(service.HospedeService.Carregar("nome", this.telaCadastroVeiculo.getjComboBoxHospede().getSelectedItem().toString()).getFirst());
                
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
                Modelo modelo = service.ModeloService.Carregar(veiculo.getModelo().getId());
                Funcionario funcionario = service.FuncionarioService.Carregar(veiculo.getFuncionario().getId());
                Fornecedor fornecedor = service.FornecedorService.Carregar(veiculo.getFornecedor().getId());
                Hospede hospede = service.HospedeService.Carregar(veiculo.getHospede().getId());

                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().setText(veiculo.getPlaca());
                this.telaCadastroVeiculo.getjTextFieldCor().setText(veiculo.getCor());
                this.telaCadastroVeiculo.getjComboBoxModelo().setSelectedItem(modelo.getDescricao());
                this.telaCadastroVeiculo.getjComboBoxFuncionario().setSelectedItem(funcionario.getNome());
                this.telaCadastroVeiculo.getjComboBoxFornecedor().setSelectedItem(fornecedor.getNome());
                this.telaCadastroVeiculo.getjComboBoxHospede().setSelectedItem(hospede.getNome());
                
                this.telaCadastroVeiculo.getjFormattedTextFieldPlaca().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroVeiculo.getjButtonSair()) {
            this.telaCadastroVeiculo.dispose();
        }
    }
}
