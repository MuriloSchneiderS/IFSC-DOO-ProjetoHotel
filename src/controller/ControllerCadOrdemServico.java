package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Check;
import model.CheckQuarto;
import model.OrdemServico;
import model.Servico;
import model.Quarto;
import view.TelaCadastroOrdemServico;

public class ControllerCadOrdemServico implements ActionListener {

    TelaCadastroOrdemServico telaCadastroOrdemServico;
    public static int codigo;

    public ControllerCadOrdemServico(TelaCadastroOrdemServico telaCadastroOrdemServico) {

        this.telaCadastroOrdemServico = telaCadastroOrdemServico;

        this.telaCadastroOrdemServico.getjButtonNovo().addActionListener(this);
        this.telaCadastroOrdemServico.getjButtonGravar().addActionListener(this);
        this.telaCadastroOrdemServico.getjButtonCancelar().addActionListener(this);
        this.telaCadastroOrdemServico.getjButtonSair().addActionListener(this);

        //Carregar o campo Quarto
        List<Quarto> quartos = service.QuartoService.Carregar("descricao", "%");
        for (Quarto quarto : quartos) {
            this.telaCadastroOrdemServico.getjComboBoxQuarto().addItem(quarto.getDescricao());
        }
        //Carregar o campo Servico
        List<Servico> servicos = service.ServicoService.Carregar("descricao", "%");
        for (Servico servico : servicos) {
            this.telaCadastroOrdemServico.getjComboBoxServico().addItem(servico.getDescricao());
        }
        
        //carregar variavel codigo com valor 0 para novo ou valor armazenado em id para editar
        codigo = telaCadastroOrdemServico.getjTextFieldId().getText().isEmpty()? 0 : Integer.parseInt(telaCadastroOrdemServico.getjTextFieldId().getText());
        if (codigo == 0){//nova ordemServico
            utilities.Utilities.ativaDesativa(this.telaCadastroOrdemServico.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroOrdemServico.getjPanelDados(), false);
        } else {//rotina de carga de ordemServico
            codigo = Integer.parseInt(telaCadastroOrdemServico.getjTextFieldId().getText());
            utilities.Utilities.ativaDesativa(this.telaCadastroOrdemServico.getjPanelBotoes(), false);

            this.telaCadastroOrdemServico.getjTextFieldId().setText(codigo + "");
            this.telaCadastroOrdemServico.getjTextFieldId().setEnabled(false);
            
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().setEnabled(false);

            OrdemServico ordemServico = service.OrdemServicoService.Carregar(codigo);
            Check check = service.CheckService.Carregar(ordemServico.getCheck().getId());
            Servico servico = service.ServicoService.Carregar(ordemServico.getServico().getId());
            Quarto quarto = service.QuartoService.Carregar(ordemServico.getQuarto().getId());

            this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().setText(ordemServico.getDataHoraPrevistaInicio());
            this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaSaida().setText(ordemServico.getDataHoraPrevistaTermino());
            this.telaCadastroOrdemServico.getjComboBoxQuarto().setSelectedItem(quarto.getDescricao());
            this.telaCadastroOrdemServico.getjComboBoxServico().setSelectedItem(servico.getDescricao());
            this.telaCadastroOrdemServico.getjTextFieldObs().setText(ordemServico.getObs());

            this.telaCadastroOrdemServico.getjTextFieldObs().requestFocus();
        }
        this.telaCadastroOrdemServico.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        //Botão Novo
        if (evento.getSource() == this.telaCadastroOrdemServico.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroOrdemServico.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroOrdemServico.getjPanelDados(), true);
            this.telaCadastroOrdemServico.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().setEnabled(false);

        //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroOrdemServico.getjButtonGravar()) {
            if (utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroOrdemServico.getjPanelDados())) {
                //Inclusão
                if (codigo == 0 || this.telaCadastroOrdemServico.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    OrdemServico ordemServico = new OrdemServico();
                    
                    //Salvar o Quarto selecionado
                    Quarto quarto = service.QuartoService.Carregar("descricao", (String) this.telaCadastroOrdemServico.getjComboBoxQuarto().getSelectedItem()).get(0);
                    
                    //Salvar o Servico selecionado
                    Servico servico = service.ServicoService.Carregar("descricao", (String) this.telaCadastroOrdemServico.getjComboBoxServico().getSelectedItem()).get(0);

                    //Cria o check_quarto com base na ordemServico e retorna o que foi criado para poder criar o check
                    CheckQuarto checkQuarto = new CheckQuarto(//Check quarto armazena a datahora de inicio e fim para quando for editar
                            this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                            this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaSaida().getText(),
                            this.telaCadastroOrdemServico.getjTextFieldObs().getText(),
                            'A',
                            quarto
                    );
                    service.CheckQuartoService.Criar(checkQuarto);
                    checkQuarto = service.CheckQuartoService.Carregar(-1);

                    //Criar o check com o check_quarto
                    Check check = new Check(
                            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().getText(),
                            "00/00/0000 00:00:00",
                            "00/00/0000 00:00:00",
                            this.telaCadastroOrdemServico.getjTextFieldObs().getText(),
                            'A',
                            checkQuarto
                    );
                    service.CheckService.Criar(check);
                    check = service.CheckService.Carregar(-1);
                    System.out.println(this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().getText());
                    ordemServico.setDataHoraCadastro(this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().getText());
                    ordemServico.setDataHoraPrevistaInicio(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    ordemServico.setDataHoraPrevistaTermino(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaSaida().getText());
                    ordemServico.setObs(this.telaCadastroOrdemServico.getjTextFieldObs().getText());
                    ordemServico.setStatus('A');
                    ordemServico.setCheck(check);
                    ordemServico.setServico(servico);
                    ordemServico.setQuarto(quarto);
                    service.OrdemServicoService.Criar(ordemServico);
                //Atualização
                } else {
                    OrdemServico ordemServico = service.OrdemServicoService.Carregar("id", String.valueOf(codigo)).get(0);

                    // Atualizar dados da ordemServico
                    ordemServico.setDataHoraCadastro(this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().getText());
                    ordemServico.setDataHoraPrevistaInicio(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    ordemServico.setDataHoraPrevistaTermino(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaSaida().getText());
                    ordemServico.setObs(this.telaCadastroOrdemServico.getjTextFieldObs().getText());
                    ordemServico.setStatus('A');

                    //Recuperar o Quarto selecionado
                    Quarto quarto = service.QuartoService.Carregar("descricao", (String) this.telaCadastroOrdemServico.getjComboBoxQuarto().getSelectedItem()).get(0);
                    
                    //Recuperar o Servico selecionado
                    Servico servico = service.ServicoService.Carregar("descricao", (String) this.telaCadastroOrdemServico.getjComboBoxServico().getSelectedItem()).get(0);
                    
                    //Atualizar os dados do Check Quarto
                    CheckQuarto checkQuarto = ordemServico.getCheck().getCheckQuarto();
                    checkQuarto.setQuarto(quarto);
                    checkQuarto.setObs(this.telaCadastroOrdemServico.getjTextFieldObs().getText());
                    checkQuarto.setDataHoraInicio(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaEntrada().getText());
                    checkQuarto.setDataHoraFim(this.telaCadastroOrdemServico.getjFormattedTextFieldDataPrevistaSaida().getText());

                    //Reiniciar o Check
                    Check check = new Check(
                            ordemServico.getCheck().getId(),
                            this.telaCadastroOrdemServico.getjFormattedTextFieldDataCadastro().getText(),
                            "00/00/0000 00:00:00",
                            "00/00/0000 00:00:00",
                            this.telaCadastroOrdemServico.getjTextFieldObs().getText(),
                            'A',
                            checkQuarto
                    );

                    //Subir atualizações
                    service.CheckQuartoService.Atualizar(checkQuarto);
                    service.CheckService.Atualizar(check);
                    service.ServicoService.Atualizar(servico);
                    
                    //Atualizar a OrdemServico
                    service.OrdemServicoService.Atualizar(ordemServico);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroOrdemServico.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroOrdemServico.getjPanelDados(), false);
            }
        //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroOrdemServico.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroOrdemServico.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroOrdemServico.getjPanelDados(), false);
        //Botão Sair
        } else if (evento.getSource() == this.telaCadastroOrdemServico.getjButtonSair()) {
            this.telaCadastroOrdemServico.dispose();
        }
    }
}
