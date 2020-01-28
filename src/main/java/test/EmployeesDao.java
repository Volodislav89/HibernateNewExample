package test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import test.dto.EmployeeEntity;

import java.util.List;

public class EmployeesDao {
    public List<EmployeeEntity> getEmployeeList() {
        Session session = null;
        List<EmployeeEntity> employeeEntityList = null;
        try {
            session = (Session) HibernateUtil.getSessionFactory();
            Query query = session.createQuery("select emp from EmployeeEntity emp");
            employeeEntityList = query.list();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return employeeEntityList;
    }

    public EmployeeEntity getEmployeeById(Integer employeeId) {
        Session session = null;
        EmployeeEntity employeeEntity = null;
        try {
            session = (Session) HibernateUtil.getSessionFactory();
            employeeEntity = (EmployeeEntity) session.get(EmployeeEntity.class, employeeId);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return employeeEntity;
    }

    public void insertEmployee(EmployeeEntity employeeEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = (Session) HibernateUtil.getSessionFactory();
            transaction = session.beginTransaction();
            session.save(employeeEntity);
            System.out.println("Inserted employee: " + employeeEntity.getFirstName());
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {}
        }
    }

    public void deleteEmployee(EmployeeEntity employeeEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = (Session) HibernateUtil.getSessionFactory();
            transaction = session.beginTransaction();
            session.delete(employeeEntity);
            transaction.commit();
            System.out.println("Deleted employee: " + employeeEntity.getFirstName());
        } catch (Exception ex) {
            ex.printStackTrace();
            if (transaction != null) transaction.rollback();
        }finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {}
        }
    }
}

