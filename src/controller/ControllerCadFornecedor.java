package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Fornecedor;
import view.TelaBuscaFornecedor;
import view.TelaCadastroFornecedor;

public class ControllerCadFornecedor implements ActionListener {

    TelaCadastroFornecedor telaCadastroFornecedor;
    public static int codigo;

    public ControllerCadFornecedor(TelaCadastroFornecedor telaCadastroFornecedor) {

        this.telaCadastroFornecedor = telaCadastroFornecedor;

        this.telaCadastroFornecedor.getjButtonNovo().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonCancelar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonGravar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonBuscar().addActionListener(this);
        this.telaCadastroFornecedor.getjButtonSair().addActionListener(this);

        utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanelBotoes(), true);
        utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanelDados(), false);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Novo
        if (evento.getSource() == this.telaCadastroFornecedor.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanelDados(), true);
            //Data atual colocada em data de cadastro
            this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setEnabled(false);
            
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanelDados(), false);
            
        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonGravar()) {
            if(utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroFornecedor.getjPanelDados())){
                Fornecedor fornecedor = new Fornecedor();
                
                fornecedor.setCep(this.telaCadastroFornecedor.getjFormattedTextFieldCep().getText());
                fornecedor.setCnpj(this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().getText());
                fornecedor.setCpf(this.telaCadastroFornecedor.getjFormattedTextFieldCpf().getText());
                fornecedor.setDataCadastro(this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().getText());
                fornecedor.setBairro(this.telaCadastroFornecedor.getjTextFieldBairro().getText());
                fornecedor.setCidade(this.telaCadastroFornecedor.getjTextFieldCidade().getText());
                fornecedor.setComplemento(this.telaCadastroFornecedor.getjTextFieldComplemento().getText());
                fornecedor.setContato(this.telaCadastroFornecedor.getjTextFieldContato().getText());
                fornecedor.setEmail(this.telaCadastroFornecedor.getjTextFieldEmail().getText());
                fornecedor.setInscricaoEstadual(this.telaCadastroFornecedor.getjTextFieldInscricaoEstadual().getText());
                fornecedor.setLogradouro(this.telaCadastroFornecedor.getjTextFieldLogradouro().getText());
                fornecedor.setNome(this.telaCadastroFornecedor.getjTextFieldNomeFantasia().getText());
                fornecedor.setFone1(this.telaCadastroFornecedor.getjFormattedTextFieldFone1().getText());
                fornecedor.setFone2(this.telaCadastroFornecedor.getjFormattedTextFieldFone2().getText());
                fornecedor.setObs(this.telaCadastroFornecedor.getjTextFieldObs().getText());
                fornecedor.setRazaoSocial(this.telaCadastroFornecedor.getjTextFieldRazaoSocial().getText());
                fornecedor.setRg(this.telaCadastroFornecedor.getjFormattedTextFieldRg().getText());
                
                if (this.telaCadastroFornecedor.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    //Inclusão
                    fornecedor.setStatus('A');
                    service.FornecedorService.Criar(fornecedor);
                } else {
                    //Atualização
                    fornecedor.setId(Integer.parseInt(this.telaCadastroFornecedor.getjTextFieldId().getText()));
                    service.FornecedorService.Atualizar(fornecedor);
                }
            }
            utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanelDados(), false);
            
        //Botão Buscar
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonBuscar()) {
            codigo = 0;

            TelaBuscaFornecedor telaBuscaFornecedor = new TelaBuscaFornecedor(null, true);
            ControllerBuscaFornecedor controllerBuscaFornecedor = new ControllerBuscaFornecedor(telaBuscaFornecedor);
            telaBuscaFornecedor.setVisible(true);

            if (codigo != 0) {
                //rotina de carga de fornecedor
                utilities.Utilities.ativaDesativa(this.telaCadastroFornecedor.getjPanelBotoes(), false);
                utilities.Utilities.limpaComponentes(this.telaCadastroFornecedor.getjPanelDados(), true);

                this.telaCadastroFornecedor.getjTextFieldId().setText(codigo + "");
                this.telaCadastroFornecedor.getjTextFieldId().setEnabled(false);

                Fornecedor fornecedor = new Fornecedor();
                fornecedor = service.FornecedorService.Carregar(codigo);

                this.telaCadastroFornecedor.getjFormattedTextFieldCep().setText(fornecedor.getCep());
                this.telaCadastroFornecedor.getjFormattedTextFieldCnpj().setText(fornecedor.getCnpj());
                this.telaCadastroFornecedor.getjFormattedTextFieldCpf().setText(fornecedor.getCpf());
                this.telaCadastroFornecedor.getjFormattedTextFieldDataCadastro().setText(fornecedor.getDataCadastro());
                this.telaCadastroFornecedor.getjTextFieldBairro().setText(fornecedor.getBairro());
                this.telaCadastroFornecedor.getjTextFieldCidade().setText(fornecedor.getCidade());
                this.telaCadastroFornecedor.getjTextFieldComplemento().setText(fornecedor.getComplemento());
                this.telaCadastroFornecedor.getjTextFieldContato().setText(fornecedor.getContato());
                this.telaCadastroFornecedor.getjTextFieldEmail().setText(fornecedor.getEmail());
                this.telaCadastroFornecedor.getjTextFieldId().setText(fornecedor.getId() + "");
                this.telaCadastroFornecedor.getjTextFieldInscricaoEstadual().setText(fornecedor.getInscricaoEstadual());
                this.telaCadastroFornecedor.getjTextFieldLogradouro().setText(fornecedor.getLogradouro());
                this.telaCadastroFornecedor.getjTextFieldNomeFantasia().setText(fornecedor.getNome());
                this.telaCadastroFornecedor.getjFormattedTextFieldFone1().setText(fornecedor.getFone1());
                this.telaCadastroFornecedor.getjFormattedTextFieldFone2().setText(fornecedor.getFone2());
                this.telaCadastroFornecedor.getjTextFieldObs().setText(fornecedor.getObs());
                this.telaCadastroFornecedor.getjTextFieldRazaoSocial().setText(fornecedor.getRazaoSocial());
                this.telaCadastroFornecedor.getjFormattedTextFieldRg().setText(fornecedor.getRg());

                this.telaCadastroFornecedor.getjTextFieldNomeFantasia().requestFocus();
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroFornecedor.getjButtonSair()) {
            this.telaCadastroFornecedor.dispose();
        }
    }
}
