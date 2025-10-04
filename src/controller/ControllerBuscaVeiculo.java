package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Marca;
import model.Modelo;
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
            if (this.telaBuscaVeiculo.getjTableDados().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum dado selecionado.");
            } else {
                //retorno dos dados para a tela de cadastro
                ControllerCadVeiculo.codigo = (int) this.telaBuscaVeiculo.getjTableDados().getValueAt(this.telaBuscaVeiculo.getjTableDados().getSelectedRow(), 0);
                this.telaBuscaVeiculo.dispose();
            }

            //Botão Filtrar
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonFiltar()) {
            if (this.telaBuscaVeiculo.getjTFFiltro().getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(null, "Sem Dados para a Seleção...");
            } else {
                DefaultTableModel tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                tabela.setRowCount(0); // Reseta a tabela

                switch (this.telaBuscaVeiculo.getjCBFiltro().getSelectedIndex()) {
                    case 0://Id
                    {
                        //Criando objeto para receber o dado que virà do banco de dados
                        Veiculo veiculo = new Veiculo();
                        Modelo modelo = new Modelo();
                        //Carregando o registro do veiculo na entidade para o objeto veiculo
                        veiculo = service.VeiculoService.Carregar(Integer.parseInt(this.telaBuscaVeiculo.getjTFFiltro().getText()));
                        modelo = service.ModeloService.Carregar(veiculo.getModelo().getId());
                        //Criando um objeto tabela do tipo defaulttableModel e atribuindo o veiculo da tela a ele
                        tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                        tabela.addRow(new Object[]{
                            veiculo.getId(),
                            veiculo.getPlaca(),
                            veiculo.getCor(),
                            service.MarcaService.Carregar(modelo.getMarca().getId()).getDescricao(),
                            modelo.getDescricao()
                        });
                        break;
                    }
                    case 1://Placa
                    {
                        //Criando a lista para receber os veiculos
                        List<Veiculo> listaVeiculos = new ArrayList<>();
                        Modelo modelo = new Modelo();
                        
                        //lista de veiculos com a placa igual a placa pesquisada
                        listaVeiculos = service.VeiculoService.Carregar("placa", this.telaBuscaVeiculo.getjTFFiltro().getText());
                        
                        //Criando um objeto tabela do tipo defaulttablemodel e atribuindo o veiculo da tabela a ele
                        tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                        tabela.setRowCount(0);
                        
                        //Adicionando os veiculos na tabela
                        for (Veiculo veiculoAtualDaLista : listaVeiculos) {
                            modelo = service.ModeloService.Carregar(veiculoAtualDaLista.getModelo().getId());
                            tabela.addRow(new Object[]{
                                veiculoAtualDaLista.getId(),
                                veiculoAtualDaLista.getPlaca(),
                                veiculoAtualDaLista.getCor(),
                                service.MarcaService.Carregar(modelo.getMarca().getId()).getDescricao(),
                                modelo.getDescricao()
                            });
                        }
                        break;
                    }
                    case 2://Cor
                    {
                        List<Veiculo> listaVeiculos = new ArrayList<>();
                        Modelo modelo = new Modelo();

                        listaVeiculos = service.VeiculoService.Carregar("cor", this.telaBuscaVeiculo.getjTFFiltro().getText());
                        tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                        tabela.setRowCount(0);

                        for (Veiculo veiculoAtualDaLista : listaVeiculos) {
                            modelo = service.ModeloService.Carregar(veiculoAtualDaLista.getModelo().getId());
                            tabela.addRow(new Object[]{
                                veiculoAtualDaLista.getId(),
                                veiculoAtualDaLista.getPlaca(),
                                veiculoAtualDaLista.getCor(),
                                service.MarcaService.Carregar(modelo.getMarca().getId()).getDescricao(),
                                modelo.getDescricao()
                            });
                        }
                        break;
                    }
                    case 3://Marca
                    {
                        //Busca a marca pela descrição digitada
                        List<Marca> marcasEncontradas = service.MarcaService.Carregar("descricao", this.telaBuscaVeiculo.getjTFFiltro().getText());
                        Marca marca = marcasEncontradas.getFirst();

                        //Busca todos os modelos que pertencem a essa marca
                        List<Modelo> modelosDaMarca = service.ModeloService.Carregar("marca_id", marca.getId()+"");

                        //Coleta todos os veículos desses modelos
                        List<Veiculo> listaVeiculos = new ArrayList<>();
                        for (Modelo modelo : modelosDaMarca) {
                            List<Veiculo> veiculosDoModelo = service.VeiculoService.Carregar("modelo_id", modelo.getId()+"");
                            listaVeiculos.addAll(veiculosDoModelo);
                        }

                        //Preenche a tabela
                        tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                        tabela.setRowCount(0);

                        for (Veiculo veiculo : listaVeiculos) {
                            Modelo modelo = service.ModeloService.Carregar(veiculo.getModelo().getId());
                            tabela.addRow(new Object[]{
                                veiculo.getId(),
                                veiculo.getPlaca(),
                                veiculo.getCor(),
                                marca.getDescricao(),
                                modelo.getDescricao()
                            });
                        }
                        break;
                    }
                    case 4://Modelo
                    {
                        List<Veiculo> listaVeiculos = new ArrayList<>();
                        Modelo modelo = new Modelo();

                        //lista de veiculos em que o modelo_id == id do modelo pesquisado pela descricao
                        listaVeiculos = service.VeiculoService.Carregar("modelo_id", service.ModeloService.Carregar("descricao", this.telaBuscaVeiculo.getjTFFiltro().getText()).getFirst().getId() + "");
                        tabela = (DefaultTableModel) this.telaBuscaVeiculo.getjTableDados().getModel();
                        tabela.setRowCount(0);

                        for (Veiculo veiculoAtualDaLista : listaVeiculos) {
                            modelo = service.ModeloService.Carregar(veiculoAtualDaLista.getModelo().getId());
                            tabela.addRow(new Object[]{
                                veiculoAtualDaLista.getId(),
                                veiculoAtualDaLista.getPlaca(),
                                veiculoAtualDaLista.getCor(),
                                service.MarcaService.Carregar(modelo.getMarca().getId()).getDescricao(),
                                modelo.getDescricao()
                            });
                        }
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Campo de pesquisa não configurado!");
                        break;
                }
            }

            //Botão Sair
        } else if (evento.getSource() == this.telaBuscaVeiculo.getjButtonSair()) {
            this.telaBuscaVeiculo.dispose();
        }
    }
}
