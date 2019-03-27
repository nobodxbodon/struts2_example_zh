package com.mkyong.customer.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mkyong.customer.model.Customer;
import com.mkyong.listener.HibernateListener;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
 
public class CustomerAction extends ActionSupport
	implements ModelDriven{

  static Logger log = Logger.getLogger(CustomerAction.class.getName());
	Customer customer = new Customer();
	List<Customer> customerList = new ArrayList<Customer>();
	
	@Override
  public String execute() throws Exception {
		return SUCCESS;
	}

	public Object getModel() {
		return customer;
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	//save customer
	public String addCustomer() throws Exception{
		
		//get hibernate session from the servlet context
		SessionFactory sessionFactory =
	         (SessionFactory) ServletActionContext.getServletContext()
                     .getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		//save it
		log.info("添加记录 姓名: " + customer.get姓名());
		
		customer.setCreatedDate(new Date());
	 
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
	 
		//reload the customer list
		customerList = null;
		customerList = session.createQuery("from Customer").list();
		
		return SUCCESS;
	
	}
	
	//list all customers
	public String listCustomer() throws Exception{

      log.info("列举记录: " + customerList.size());
		//get hibernate session from the servlet context
		SessionFactory sessionFactory =
	         (SessionFactory) ServletActionContext.getServletContext()
                     .getAttribute(HibernateListener.KEY_NAME);

		Session session = sessionFactory.openSession();

		customerList = session.createQuery("from Customer").list();
		
		return SUCCESS;
	
	}
	
}