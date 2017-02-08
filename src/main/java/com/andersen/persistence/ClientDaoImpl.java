package com.andersen.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.domain.Client;

@Component
public class ClientDaoImpl implements ClientDao {

	private static final Logger logger = Logger.getLogger(ClientDaoImpl.class);
	
	public ClientDaoImpl(){
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void persist(Client entity) {
		logger.info("Start creating client.");
		getCurrentSession().save(entity);
	}

	@Transactional
	public Client find(int id) {
		logger.info("Start finding client.");
		return getCurrentSession().get(Client.class, id);
	}

	@Transactional
	public List<Client> findAll() {
		logger.info("Start finding all clients.");
		System.out.println();
		return (List<Client>) getCurrentSession().createCriteria(Client.class).list();
	}

	@Transactional
	public void update(Client entity) {
		logger.info("Start updating client.");
		getCurrentSession().update(entity);
	}

	@Transactional
	public void delete(Client entity) {
		logger.info("Start deleting client.");
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(int id) {
		logger.info("Start deleting client by id.");
		getCurrentSession().delete(this.find(id));
	}

	@Transactional
	public void deleteAll() {
		logger.info("Start deleting all clients.");
		List<Client> entityList = findAll();
		for (Client entity : entityList) {
			delete(entity);
		}
	}
}
