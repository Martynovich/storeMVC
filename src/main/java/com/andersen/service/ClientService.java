package com.andersen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Client;
import com.andersen.persistence.ClientDao;
import com.andersen.persistence.ClientDaoImpl;

@Component
public class ClientService implements CrudServise<Client> {

	private static final Logger logger = Logger.getLogger(ClientService.class);

	@Autowired
	private ClientDao clientDao;

	public ClientService() {
	}

	public void create(Client client) {
		logger.debug("ClientService create");
		clientDao.persist(client);
	}

	public Client findById(int id) {
		logger.debug("ClientService findById");
		return clientDao.find(id);
	}

	public List<Client> findAll() {
		logger.debug("ClientService findAll");
		return clientDao.findAll();
	}

	public void update(Client client) {
		logger.debug("ClientService update");
		clientDao.update(client);
	}
	
	public void delete(Client client) {
		logger.debug("ClientService delete");
		clientDao.delete(client);
	}

	public void deleteById(int id) {
		logger.debug("ClientService deleteById");
		clientDao.deleteById(id);
	}

	public void deleteAll() {
		logger.debug("ClientService deleteAll");
		clientDao.deleteAll();
	}
}
