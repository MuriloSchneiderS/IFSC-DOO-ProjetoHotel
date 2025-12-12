package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Veiculo;

public class VeiculoDAO implements InterfaceDAO<Veiculo> {

    private static VeiculoDAO instance;
    protected EntityManager entityManager;

    public VeiculoDAO() {
        entityManager = getEntityManager();
    }

    public static VeiculoDAO getInstance() {
        if (instance == null) {
            instance = new VeiculoDAO();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void Create(Veiculo objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public Veiculo Retrieve(int id) {

        Veiculo veiculo = entityManager.find(Veiculo.class, id);
        return veiculo;
    }

    @Override
    public List<Veiculo> Retrieve(String atributo, String valor) {

        List<Veiculo> listaVeiculo = new ArrayList<>();
        listaVeiculo = entityManager.createQuery(" Select x From Veiculo x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Veiculo.class).getResultList();
        return listaVeiculo;
    }

    @Override
    public void Update(Veiculo objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void Delete(Veiculo objeto) {

        try{
            entityManager.getTransaction().begin();
            Veiculo veiculo = new Veiculo();
            veiculo = entityManager.find(Veiculo.class, objeto.getId());
            if(veiculo != null){
                entityManager.remove(veiculo);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}