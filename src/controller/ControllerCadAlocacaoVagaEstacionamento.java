package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Check;
import model.CheckQuarto;
import model.Veiculo;
import model.VagaEstacionamento;
import model.AlocacaoVaga;
import model.Quarto;
import view.TelaCadastroAlocacaoVagaEstacionamento;

public class ControllerCadAlocacaoVagaEstacionamento implements ActionListener {

    TelaCadastroAlocacaoVagaEstacionamento telaCadastroAlocacaoVagaEstacionamento;
    public static int codigo;

    public ControllerCadAlocacaoVagaEstacionamento(TelaCadastroAlocacaoVagaEstacionamento telaCadastroAlocacaoVagaEstacionamento) {

        this.telaCadastroAlocacaoVagaEstacionamento = telaCadastroAlocacaoVagaEstacionamento;

        this.telaCadastroAlocacaoVagaEstacionamento.getjButtonNovo().addActionListener(this);
        this.telaCadastroAlocacaoVagaEstacionamento.getjButtonGravar().addActionListener(this);
        this.telaCadastroAlocacaoVagaEstacionamento.getjButtonCancelar().addActionListener(this);
        this.telaCadastroAlocacaoVagaEstacionamento.getjButtonSair().addActionListener(this);

        //Carregar o campo VagaEstacionamento
        List<VagaEstacionamento> vagaEstacionamentos = service.VagaEstacionamentoService.Carregar("descricao", "%");
        for (VagaEstacionamento vagaEstacionamento : vagaEstacionamentos) {
            this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVagaEstacionamento().addItem(vagaEstacionamento.getDescricao());
        }
        //Carregar o campo Veiculo
        List<Veiculo> veiculos = service.VeiculoService.Carregar("placa", "%");
        for (Veiculo veiculo : veiculos) {
            this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVeiculo().addItem(veiculo.getPlaca());
        }

        //carregar variavel codigo com valor 0 para novo ou valor armazenado em id para editar
        codigo = this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().getText().isEmpty() ? 0 : Integer.parseInt(telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().getText());
        if (codigo == 0) {//nova alocacaoVaga
            utilities.Utilities.ativaDesativa(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelDados(), false);
        } else {//rotina de carga de alocacaoVaga da vaga do estacionamento
            codigo = Integer.parseInt(telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().getText());
            utilities.Utilities.ativaDesativa(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelBotoes(), false);

            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().setText(codigo + "");
            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().setEnabled(false);

            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().setEnabled(false);

            AlocacaoVaga alocacaoVaga = service.AlocacaoVagaService.Carregar(codigo);
            Veiculo veiculo = service.VeiculoService.Carregar(alocacaoVaga.getVeiculo().getId());
            VagaEstacionamento vagaEstacionamento = service.VagaEstacionamentoService.Carregar(alocacaoVaga.getVagaEstacionamento().getId());
            Check check = service.CheckService.Carregar(alocacaoVaga.getCheck().getId());

            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaEntrada().setText(check.getDataHoraEntrada());
            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaSaida().setText(check.getDataHoraSaida());
            this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVagaEstacionamento().setSelectedItem(vagaEstacionamento.getDescricao());
            this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVeiculo().setSelectedItem(veiculo.getPlaca());
            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().setText(alocacaoVaga.getObs());

            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().requestFocus();
        }
        this.telaCadastroAlocacaoVagaEstacionamento.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        //Botão Novo
        if (evento.getSource() == this.telaCadastroAlocacaoVagaEstacionamento.getjButtonNovo()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelBotoes(), false);
            utilities.Utilities.limpaComponentes(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelDados(), true);
            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().setEnabled(false);
            java.util.Date dataAtual = new Date();
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            String novaData = dataFormatada.format(dataAtual);
            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().setText(novaData);
            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().setEnabled(false);

            //Botão Gravar
        } else if (evento.getSource() == this.telaCadastroAlocacaoVagaEstacionamento.getjButtonGravar()) {
            if (utilities.Utilities.todosOsCamposPreenchidos(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelDados())) {
                //Inclusão
                if (codigo == 0 || this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldId().getText().trim().equalsIgnoreCase("")) {
                    AlocacaoVaga alocacaoVaga = new AlocacaoVaga();

                    //Salvar o Veiculo selecionado
                    Veiculo veiculo = service.VeiculoService.Carregar("placa", (String) this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVeiculo().getSelectedItem()).get(0);

                    //Salvar o VagaEstacionamento selecionado
                    VagaEstacionamento vagaEstacionamento = service.VagaEstacionamentoService.Carregar("descricao", (String) this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVagaEstacionamento().getSelectedItem()).get(0);

                    //Salvar o Quarto do hospede dono do veiculo
                    //Precisa do id do quarto
                    //id do quarto esta em check_quarto.quarto_id
                    //id do check_quarto esta em check.check_quarto_id
                    //id do check esta em check_hospede.check_id
                    //buscar check_hospede pelo hospede_id
                    //id do hospede esta em veiculo.hospede_id
                    Quarto quarto = service.QuartoService.Carregar(
                            service.CheckQuartoService.Carregar(
                                    service.CheckService.Carregar(
                                            service.CheckHospedeService.Carregar(
                                                    "hospede_id", veiculo.getHospede().getId()+""
                                            ).getLast().getCheck().getId()
                                    ).getCheckQuarto().getId()
                            ).getQuarto().getId()
                    );

                    //Criar o check_quarto para poder criar o check
                    CheckQuarto checkQuarto = new CheckQuarto(//Check quarto armazena a datahora de inicio e fim para quando for editar
                            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaSaida().getText(),
                            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().getText(),
                            'A',
                            quarto
                    );
                    service.CheckQuartoService.Criar(checkQuarto);
                    checkQuarto = service.CheckQuartoService.Carregar(-1);

                    //Criar o check com o check_quarto
                    Check check = new Check(
                            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().getText(),
                            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaEntrada().getText(),
                            this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaSaida().getText(),
                            this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().getText(),
                            'A',
                            checkQuarto
                    );
                    service.CheckService.Criar(check);
                    check = service.CheckService.Carregar(-1);
                    
                    alocacaoVaga.setObs(this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().getText());
                    alocacaoVaga.setStatus('A');
                    alocacaoVaga.setCheck(check);
                    alocacaoVaga.setVeiculo(veiculo);
                    alocacaoVaga.setVagaEstacionamento(vagaEstacionamento);
                    service.AlocacaoVagaService.Criar(alocacaoVaga);
                //Atualização
                } else {
                    AlocacaoVaga alocacaoVaga = service.AlocacaoVagaService.Carregar("id", String.valueOf(codigo)).get(0);
                    
                    String dataPrevistaEntrada = this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaEntrada().getText();
                    String dataPrevistaSaida = this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataPrevistaSaida().getText();
                    String vagaSelecionada = (String) this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVagaEstacionamento().getSelectedItem();
                    String veiculoSelecionado = (String) this.telaCadastroAlocacaoVagaEstacionamento.getjComboBoxVeiculo().getSelectedItem();
                    String obs = this.telaCadastroAlocacaoVagaEstacionamento.getjTextFieldObs().getText();
                    
                    //Recuperar o VagaEstacionamento selecionado
                    VagaEstacionamento vagaEstacionamento = service.VagaEstacionamentoService.Carregar("descricao", vagaSelecionada).get(0);

                    //Recuperar o Veiculo selecionado
                    Veiculo veiculo = service.VeiculoService.Carregar("placa", veiculoSelecionado).get(0);

                    //Atualizar dados da alocacao_vaga
                    alocacaoVaga.setVeiculo(veiculo);
                    alocacaoVaga.setVagaEstacionamento(vagaEstacionamento);
                    alocacaoVaga.setObs(obs);
                    alocacaoVaga.setStatus('A');
                    
                    //Atualizar dados do check
                    Check check = service.CheckService.Carregar(alocacaoVaga.getCheck().getId());
                    check.setDataHoraCadastro(this.telaCadastroAlocacaoVagaEstacionamento.getjFormattedTextFieldDataCadastro().getText());
                    check.setDataHoraEntrada(dataPrevistaEntrada);
                    check.setDataHoraSaida(dataPrevistaSaida);
                    check.setObs(obs);
                    
                    CheckQuarto checkQuarto = service.CheckQuartoService.Carregar(check.getCheckQuarto().getId());
                    checkQuarto.setDataHoraInicio(dataPrevistaEntrada);
                    checkQuarto.setDataHoraFim(dataPrevistaSaida);
                    Quarto quarto = service.QuartoService.Carregar(
                            service.CheckQuartoService.Carregar(
                                    service.CheckService.Carregar(
                                            service.CheckHospedeService.Carregar(
                                                    "hospede_id", veiculo.getHospede().getId()+""
                                            ).getLast().getCheck().getId()
                                    ).getCheckQuarto().getId()
                            ).getQuarto().getId()
                    );
                    checkQuarto.setQuarto(quarto);
                    
                    check.setCheckQuarto(checkQuarto);
                    
                    //Subir atualizações
                    service.CheckQuartoService.Atualizar(checkQuarto);
                    service.CheckService.Atualizar(check);
                    service.AlocacaoVagaService.Atualizar(alocacaoVaga);
                }
                utilities.Utilities.ativaDesativa(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelBotoes(), true);
                utilities.Utilities.limpaComponentes(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelDados(), false);
            }
            //Botão Cancelar
        } else if (evento.getSource() == this.telaCadastroAlocacaoVagaEstacionamento.getjButtonCancelar()) {
            utilities.Utilities.ativaDesativa(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelBotoes(), true);
            utilities.Utilities.limpaComponentes(this.telaCadastroAlocacaoVagaEstacionamento.getjPanelDados(), false);
            //Botão Sair
        } else if (evento.getSource() == this.telaCadastroAlocacaoVagaEstacionamento.getjButtonSair()) {
            this.telaCadastroAlocacaoVagaEstacionamento.dispose();
        }
    }
}
