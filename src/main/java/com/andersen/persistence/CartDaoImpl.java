package com.andersen.persistence;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.andersen.domain.Cart;

@Component
public class CartDaoImpl implements CartDao {

	private static final Logger logger = Logger.getLogger(CartDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public CartDaoImpl(){
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void persist(Cart entity) {
		logger.info("Start persist cart.");
		getCurrentSession().save(entity);
	}

	@Transactional
	public Cart find(int id) {
		logger.info("Start find cart.");
		return getCurrentSession().get(Cart.class, id);
	}

	@Transactional
	public List<Cart> findAll() {
		logger.info("Start finding all carts");
		return getCurrentSession().createCriteria(Cart.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Transactional
	public void update(Cart entity) {
		logger.info("Start updating cart.");
		getCurrentSession().update(entity);
	}

	@Transactional
	public void delete(Cart entity) {
		logger.info("Start deleting cart.");
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(int id) {
		logger.info("Start deleting cart by id.");
		getCurrentSession().delete(this.find(id));
	}

	@Transactional
	public void deleteAll() {
		logger.info("Start deleting all carts.");
		List<Cart> entityList = findAll();
		for (Cart entity : entityList) {
			delete(entity);
		}
	}
}
