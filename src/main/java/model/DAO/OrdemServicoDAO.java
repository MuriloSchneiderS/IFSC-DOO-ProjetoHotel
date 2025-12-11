package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.OrdemServico;

public class OrdemServicoDAO implements InterfaceDAO<OrdemServico> {

    private static OrdemServicoDAO instance;
    protected EntityManager entityManager;

    public OrdemServicoDAO() {
        entityManager = getEntityManager();
    }

    public static OrdemServicoDAO getInstance() {
        if (instance == null) {
            instance = new OrdemServicoDAO();
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
    public void Create(OrdemServico objeto) {
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
    public OrdemServico Retrieve(int id) {

        OrdemServico ordemServico = entityManager.find(OrdemServico.class, id);
        return ordemServico;
    }

    @Override
    public List<OrdemServico> Retrieve(String atributo, String valor) {

        List<OrdemServico> listaOrdemServico = new ArrayList<>();
        listaOrdemServico = entityManager.createQuery(" Select hosp From ordemServico hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", OrdemServico.class).getResultList();
        return listaOrdemServico;
    }

    @Override
    public void Update(OrdemServico objeto) {
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
    public void Delete(OrdemServico objeto) {

        try{
            entityManager.getTransaction().begin();
            OrdemServico ordemServico = new OrdemServico();
            ordemServico = entityManager.find(OrdemServico.class, objeto.getId());
            if(ordemServico != null){
                entityManager.remove(ordemServico);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}