package model.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Hospede;

public class HospedeDAO implements InterfaceDAO<Hospede> {

    private static HospedeDAO instance;
    protected EntityManager entityManager;

    public HospedeDAO() {
        entityManager = getEntityManager();
    }

    public static HospedeDAO getInstance() {
        if (instance == null) {
            instance = new HospedeDAO();
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
    public void Create(Hospede objeto) {
        try {
            objeto.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataCadastro())));
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public Hospede Retrieve(int id) {

        Hospede hospede = entityManager.find(Hospede.class, id);
        return hospede;
    }

    @Override
    public List<Hospede> Retrieve(String atributo, String valor) {

        List<Hospede> listaHospede = new ArrayList<>();
        listaHospede = entityManager.createQuery(" Select x From Hospede x "
                + " where x." + atributo
                + " LIKE '%" + valor + "%'", Hospede.class).getResultList();
        return listaHospede;
    }

    @Override
    public void Update(Hospede objeto) {
        try {
            objeto.setDataCadastro(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(objeto.getDataCadastro())));
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void Delete(Hospede objeto) {

        try{
            entityManager.getTransaction().begin();
            Hospede hospede = new Hospede();
            hospede = entityManager.find(Hospede.class, objeto.getId());
            if(hospede != null){
                entityManager.remove(hospede);
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}