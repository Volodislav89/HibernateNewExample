package test;

import org.hibernate.Session;
import test.dto.EmployeeEntity;

public class TestHibernate {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		EmployeesDao employeesDao = new EmployeesDao();
       
		//Add new Employee object
//		EmployeeEntity emp = new EmployeeEntity();
//		emp.setEmail("demo-user@mail.com");
//		emp.setFirstName("demo");
//		emp.setLastName("user");
//
//		session.save(emp);
		EmployeeEntity employeeEntity = (EmployeeEntity) session.get(EmployeeEntity.class, 1);
		System.out.println(employeeEntity);
		System.out.println(employeeEntity.getFirstName());
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

}
