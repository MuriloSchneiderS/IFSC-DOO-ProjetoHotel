package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Veiculo;
import view.TelaBuscaVeiculo;

public class ControllerBuscaVeiculo implements ActionListener {

    TelaBuscaVeiculo telaBuscaVeiculo;

    public ControllerBuscaVeiculo(TelaBuscaVeiculo telaBuscaVeiculo) {

        this.telaBuscaVeiculo = telaBuscaVeiculo;

        this.telaBuscaVeiculo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonFiltar().addActionListener(this);
        this.telaBuscaVeiculo.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaVeiculo.getjButtonCarregar()) {
            if(this.telaBuscaVeiculo.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadVeiculo.codigo = (int) this.telaBuscaVeiculo.getjTableDados().getValueAt(this.telaBuscaVeiculo.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaVeiculo.dispose();
            }
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonFiltar()) {
            if (this.telaBuscaVeiculo.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaVeiculo.getjCBFiltro().getSelectedIndex() == 0) {//Ordenar por Id
                    //Criando objeto para receber o dado que virà do banco de dados
                    Veiculo veiculo = new Veiculo();
                    //Carregando o registro do veiculo na entidade para o objeto veiculo
                    veiculo = service.VeiculoService.Carregar(Integer.parseInt(this.telaBuscaVeiculo.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o veiculo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                    tabela.addRow(new Object[]{
                        veiculo.getId(), 
                        veiculo.getPlaca(), 
                        veiculo.getCor(), 
                        veiculo.getModelo().getDescricao(), 
                        veiculo.getFuncionario().getNome(), 
                        veiculo.getFornecedor().getNome(), 
                        veiculo.getHospede().getNome()
                    });
                } else if (this.telaBuscaVeiculo.getjCBFiltro().getSelectedIndex() == 1) {//Ordenar por Descricao
                    //Criando a lista para receber as veiculos
                    List<Veiculo> listaVeiculos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o veiculo da tabela a ele
                    listaVeiculos = service.VeiculoService.Carregar("nome", this.telaBuscaVeiculo.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os veiculos na tabela
                    for(Veiculo veiculoAtualDaLista : listaVeiculos){
                        tabela.addRow(new Object[]{
                            veiculoAtualDaLista.getId(), 
                            veiculoAtualDaLista.getPlaca(), 
                            veiculoAtualDaLista.getCor(), 
                            veiculoAtualDaLista.getModelo().getDescricao(), 
                            veiculoAtualDaLista.getFuncionario().getNome(), 
                            veiculoAtualDaLista.getFornecedor().getNome(), 
                            veiculoAtualDaLista.getHospede().getNome()
                        });
                    }
                }
            }
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonSair()) {
            this.telaBuscaVeiculo.dispose();
        }
    }
}
