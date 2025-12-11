package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.DAO.InterfaceDAO;
import model.bo.CheckHospede;

public class CheckHospedeDAO implements InterfaceDAO<CheckHospede> {

    private static CheckHospedeDAO instance;
    protected EntityManager entityManager;

    public CheckHospedeDAO() {
        entityManager = getEntityManager();
    }

    public static CheckHospedeDAO getInstance() {
        if (instance == null) {
            instance = new CheckHospedeDAO();
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
    public void Create(CheckHospede objeto) {
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
    public CheckHospede Retrieve(int id) {

        CheckHospede checkCheckHospede = entityManager.find(CheckHospede.class, id);
        return checkCheckHospede;
    }

    @Override
    public List<CheckHospede> Retrieve(String atributo, String valor) {

        List<CheckHospede> listaCheckHospede = new ArrayList<>();
        listaCheckHospede = entityManager.createQuery(" Select hosp From checkCheckHospede hosp "
                + " where " + atributo
                + " like (%" + valor + " %)", CheckHospede.class).getResultList();
        return listaCheckHospede;
    }

    @Override
    public void Update(CheckHospede objeto) {
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
    public void Delete(CheckHospede objeto) {

        try{
            entityManager.getTransaction().begin();
            CheckHospede checkCheckHospede = new CheckHospede();
            checkCheckHospede = entityManager.find(CheckHospede.class, objeto.getId());
            if(checkCheckHospede != null){
                entityManager.remove(checkCheckHospede);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}