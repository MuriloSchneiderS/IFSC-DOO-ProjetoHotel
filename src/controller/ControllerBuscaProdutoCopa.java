package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ProdutoCopa;
import view.TelaBuscaProdutoCopa;

public class ControllerBuscaProdutoCopa implements ActionListener {

    TelaBuscaProdutoCopa telaBuscaProdutoCopa;

    public ControllerBuscaProdutoCopa(TelaBuscaProdutoCopa telaBuscaProdutoCopa) {

        this.telaBuscaProdutoCopa = telaBuscaProdutoCopa;

        this.telaBuscaProdutoCopa.getjButtonCarregar().addActionListener(this);
        this.telaBuscaProdutoCopa.getjButtonFiltar().addActionListener(this);
        this.telaBuscaProdutoCopa.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonCarregar()) {
            if(this.telaBuscaProdutoCopa.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadProdutoCopa.codigo = (int) this.telaBuscaProdutoCopa.getjTableDados().getValueAt(this.telaBuscaProdutoCopa.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaProdutoCopa.dispose();
            }
            
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonFiltar()) {
            if (this.telaBuscaProdutoCopa.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                //Ordenar por Id
                if (this.telaBuscaProdutoCopa.getjCBFiltro().getSelectedIndex() == 0) {
                    //Criando objeto para receber o dado que virà do banco de dados
                    ProdutoCopa produtoCopa = new ProdutoCopa();
                    //Carregando o registro do produtoCopa na entidade para o objeto produtoCopa
                    produtoCopa = service.ProdutoCopaService.Carregar(Integer.parseInt(this.telaBuscaProdutoCopa.getjTFFiltro().getText()));
                    //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableDados().getModel();
                    tabela.addRow(new Object[]{
                        produtoCopa.getId(), 
                        produtoCopa.getDescricao(),
                        produtoCopa.getValor(),
                        produtoCopa.getObs(),
                        produtoCopa.getCopaQuartoId()
                    });
                    
                //Ordenar por Descricao
                } else if (this.telaBuscaProdutoCopa.getjCBFiltro().getSelectedIndex() == 1) {
                    //Criando a lista para receber as produtosCopa
                    List<ProdutoCopa> listaProdutosCopa = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaProdutosCopa = service.ProdutoCopaService.Carregar("descricao", this.telaBuscaProdutoCopa.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os produtosCopa na tabela
                    for(ProdutoCopa produtoCopaAtualDaLista : listaProdutosCopa){
                        tabela.addRow(new Object[]{
                            produtoCopaAtualDaLista.getId(), 
                            produtoCopaAtualDaLista.getDescricao(),
                            produtoCopaAtualDaLista.getValor(),
                            produtoCopaAtualDaLista.getObs(),
                            produtoCopaAtualDaLista.getCopaQuartoId()
                        });
                    }
                    
                //Ordenar por Valor
                } else if (this.telaBuscaProdutoCopa.getjCBFiltro().getSelectedIndex() == 2) {
                    //Criando a lista para receber as produtosCopa
                    List<ProdutoCopa> listaProdutosCopa = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaProdutosCopa = service.ProdutoCopaService.Carregar("valor", this.telaBuscaProdutoCopa.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os produtosCopa na tabela
                    for(ProdutoCopa produtoCopaAtualDaLista : listaProdutosCopa){
                        tabela.addRow(new Object[]{
                            produtoCopaAtualDaLista.getId(), 
                            produtoCopaAtualDaLista.getDescricao(),
                            produtoCopaAtualDaLista.getValor(),
                            produtoCopaAtualDaLista.getObs(),
                            produtoCopaAtualDaLista.getCopaQuartoId()
                        });
                    }
                    
                //Ordenar por Obs
                } else if (this.telaBuscaProdutoCopa.getjCBFiltro().getSelectedIndex() == 3) {
                    //Criando a lista para receber as produtosCopa
                    List<ProdutoCopa> listaProdutosCopa = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaProdutosCopa = service.ProdutoCopaService.Carregar("obs", this.telaBuscaProdutoCopa.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os produtosCopa na tabela
                    for(ProdutoCopa produtoCopaAtualDaLista : listaProdutosCopa){
                        tabela.addRow(new Object[]{
                            produtoCopaAtualDaLista.getId(), 
                            produtoCopaAtualDaLista.getDescricao(),
                            produtoCopaAtualDaLista.getValor(),
                            produtoCopaAtualDaLista.getObs(),
                            produtoCopaAtualDaLista.getCopaQuartoId()
                        });
                    }
                    
                //Ordenar por Copa Quarto
                } else if (this.telaBuscaProdutoCopa.getjCBFiltro().getSelectedIndex() == 4) {
                    //Criando a lista para receber as produtosCopa
                    List<ProdutoCopa> listaProdutosCopa = new ArrayList<>();
                    //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                    listaProdutosCopa = service.ProdutoCopaService.Carregar("copa_quarto_id", this.telaBuscaProdutoCopa.getjTFFiltro().getText());
                    
                    DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaProdutoCopa.getjTableDados().getModel();
                    tabela.setRowCount(0);
                    //Adicionando os produtosCopa na tabela
                    for(ProdutoCopa produtoCopaAtualDaLista : listaProdutosCopa){
                        tabela.addRow(new Object[]{
                            produtoCopaAtualDaLista.getId(), 
                            produtoCopaAtualDaLista.getDescricao(),
                            produtoCopaAtualDaLista.getValor(),
                            produtoCopaAtualDaLista.getObs(),
                            produtoCopaAtualDaLista.getCopaQuartoId()
                        });
                    }
                }
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaProdutoCopa.getjButtonSair()) {
            this.telaBuscaProdutoCopa.dispose();
        }
    }
}
