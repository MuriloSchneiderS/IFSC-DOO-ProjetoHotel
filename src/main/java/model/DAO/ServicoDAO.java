package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Servico;

public class ServicoDAO implements InterfaceDAO<Servico> {

    private static ServicoDAO instance;
    protected EntityManager entityManager;

    public ServicoDAO() {
        entityManager = getEntityManager();
    }

    public static ServicoDAO getInstance() {
        if (instance == null) {
            instance = new ServicoDAO();
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
    public void Create(Servico objeto) {
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
    public Servico Retrieve(int id) {

        Servico servico = entityManager.find(Servico.class, id);
        return servico;
    }

    @Override
    public List<Servico> Retrieve(String atributo, String valor) {

        List<Servico> listaServico = new ArrayList<>();
        listaServico = entityManager.createQuery(" Select x From Servico x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Servico.class).getResultList();
        return listaServico;
    }

    @Override
    public void Update(Servico objeto) {
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
    public void Delete(Servico objeto) {

        try{
            entityManager.getTransaction().begin();
            Servico servico = new Servico();
            servico = entityManager.find(Servico.class, objeto.getId());
            if(servico != null){
                entityManager.remove(servico);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}