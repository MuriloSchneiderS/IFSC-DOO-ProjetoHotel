package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import model.Modelo;
import view.TelaBuscaModelo;

public class ControllerBuscaModelo implements ActionListener {

    TelaBuscaModelo telaBuscaModelo;

    public ControllerBuscaModelo(TelaBuscaModelo telaBuscaModelo) {

        this.telaBuscaModelo = telaBuscaModelo;

        this.telaBuscaModelo.getjButtonCarregar().addActionListener(this);
        this.telaBuscaModelo.getjButtonFiltar().addActionListener(this);
        this.telaBuscaModelo.getjButtonSair().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        //Botão Carregar
        if (evento.getSource() == this.telaBuscaModelo.getjButtonCarregar()) {
            if(this.telaBuscaModelo.getjTableDados().getRowCount()==0){
                JOptionPane.showMessageDialog(null,"Nenhum dado selecionado.");
            }else{
                //retorno dos dados para a tela de cadastro
                ControllerCadModelo.codigo = (int) this.telaBuscaModelo.getjTableDados().getValueAt(this.telaBuscaModelo.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaModelo.dispose();
            }
            
        //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonFiltar()) {
            if (this.telaBuscaModelo.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                tabela.setRowCount(0); // Reseta a tabela
                
                switch (this.telaBuscaModelo.getjCBFiltro().getSelectedIndex()) {
                    case 0://Id
                        {
                            //Criando objeto para receber o dado que virà do banco de dados
                            Modelo modelo = new Modelo();
                            //Carregando o registro do modelo na entidade para o objeto modelo
                            modelo = service.ModeloService.Carregar(Integer.parseInt(this.telaBuscaModelo.getjTFFiltro().getText()));
                            //Criando um objeto tabela do tipo defaulttableModel e atribuindo o modelo da tela a ele
                            tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                            tabela.addRow(new Object[]{
                                modelo.getId(),
                                modelo.getDescricao(),
                                service.MarcaService.Carregar(modelo.getMarca().getId()).getDescricao()
                            });     
                            break;
                        }
                    case 1://Descricao
                        {
                            //Criando a lista para receber os modelos
                            List<Modelo> listaModelos = new ArrayList<>();
                            //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o modelo da tabela a ele
                            listaModelos = service.ModeloService.Carregar("descricao", this.telaBuscaModelo.getjTFFiltro().getText());
                            tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                            tabela.setRowCount(0);
                            //Adicionando os modelos na tabela
                            for(Modelo modeloAtualDaLista : listaModelos){
                                tabela.addRow(new Object[]{
                                    modeloAtualDaLista.getId(),
                                    modeloAtualDaLista.getDescricao(),
                                    service.MarcaService.Carregar(modeloAtualDaLista.getMarca().getId()).getDescricao()
                                });
                            }       
                            break;
                        }
                    case 2://Marca
                            List<Modelo> listaModelos = new ArrayList<>();
                            Marca marcaPesquisada = service.MarcaService.Carregar("descricao", this.telaBuscaModelo.getjTFFiltro().getText()).getFirst();
                            
                            listaModelos = service.ModeloService.Carregar("marca_id", marcaPesquisada.getId()+"");
                            tabela = (DefaultTableModel) this.telaBuscaModelo.getjTableDados().getModel();
                            tabela.setRowCount(0);
                            
                            for(Modelo modeloAtualDaLista : listaModelos){
                                tabela.addRow(new Object[]{
                                    modeloAtualDaLista.getId(),
                                    modeloAtualDaLista.getDescricao(),
                                    service.MarcaService.Carregar(modeloAtualDaLista.getMarca().getId()).getDescricao()
                                });
                            }
                        break;
                    default:
                        break;
                }
            }
            
        //Botão Sair
        } else if (evento.getSource() == this.telaBuscaModelo.getjButtonSair()) {
            this.telaBuscaModelo.dispose();
        }
    }
}
