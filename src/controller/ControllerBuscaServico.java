package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Servico;
import view.TelaBuscaServico;

public class ControllerBuscaServico implements ActionListener {

    TelaBuscaServico telaBuscaServico;

    public ControllerBuscaServico(TelaBuscaServico telaBuscaServico) {

        this.telaBuscaServico = telaBuscaServico;

        this.telaBuscaServico.getjButtonCarregar().addActionListener(this);
        this.telaBuscaServico.getjButtonFiltar().addActionListener(this);
        this.telaBuscaServico.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaServico.getjButtonCarregar()) {
            if(this.telaBuscaServico.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadServico.codigo = (int) this.telaBuscaServico.getjTableDados().getValueAt(this.telaBuscaServico.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaServico.dispose();
            }
            
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaServico.getjButtonFiltar()) {
            if (this.telaBuscaServico.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                if (this.telaBuscaServico.getjCBFiltro().getSelectedIndex() == 0) {//Ordenar por Id
                    //Criando objeto para receber o dado que virà do banco de dados
                    Servico servico = new Servico();
                    //Carregando o registro do servico na entidade para o objeto servico
                    servico = service.ServicoService.Carregar(Integer.parseInt(this.telaBuscaServico.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o servico da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableDados().getModel();
                    tabela.addRow(new Object[]{
                        servico.getId(), 
                        servico.getDescricao(), 
                        servico.getObs()
                    });
                } else if (this.telaBuscaServico.getjCBFiltro().getSelectedIndex() == 1) {//Ordenar por Descricao
                    //Criando a lista para receber as servicos
                    List<Servico> listaServicos = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o servico da tabela a ele
                    listaServicos = service.ServicoService.Carregar("nome", this.telaBuscaServico.getjTFFiltro().getText());
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaServico.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os servicos na tabela
                    for(Servico servicoAtualDaLista : listaServicos){
                        tabela.addRow(new Object[]{
                            servicoAtualDaLista.getId(), 
                            servicoAtualDaLista.getDescricao(), 
                            servicoAtualDaLista.getObs()
                        });
                    }
                }
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaServico.getjButtonSair()) {
            this.telaBuscaServico.dispose();
        }
    }
}
