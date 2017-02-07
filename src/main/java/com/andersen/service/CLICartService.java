package com.andersen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.andersen.domain.Cart;
import com.andersen.domain.Client;
import com.andersen.domain.Product;

@Component
public class CLICartService implements CLICrudService {
	private static final Logger logger = Logger.getLogger(CartService.class);

	@Autowired
	private CartService cartService;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private String userInput;
	@Autowired
	private CLIClientService cliClientService;
	@Autowired
	private CLIProductService cliProductService;

	public CLICartService() {
	}

	public void create() {
		logger.info("Start creating cart.");
		Client client = cliClientService.clientIdInput();
		List<Product> products = new ArrayList<Product>();
		Cart cart = new Cart();
		cart.setClient(client);
		try {
			while (true) {
				logger.info("Add product? Enter yes/no");
				logger.info("For exit enter - exit.");
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (userInput.equals("no"))
					break;
				if (!userInput.equals("yes") && !userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
				}
				products.add(cliProductService.productIdInput());
				logger.info("Product added.");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		cart.setProducts(products);
		cartService.create(cart);
		logger.info("Cart is added");
		StoreUtil.contOrExit();
	}

	public void findById() {
		logger.info("Start finding by id cart.");
		Cart cart = cartIdInput();
		logger.info("Cart id - " + cart.getId() + " client id - " + cart.getClient().getId() + " order date - "
				+ cart.getDateOfCreation());
		List<Product> products = cart.getProducts();
		logger.info("Products in cart.");
		for (Product product : products) {
			logger.info("Product id - " + product.getId() + ", product login - " + product.getProductName()
					+ ", product price - " + product.getProductPrice());
		}
		StoreUtil.contOrExit();
	}

	public void findAll() {
		logger.info("Start finding all carts.");
		List<Cart> carts = cartService.findAll();
		if (carts.isEmpty()) {
			logger.info("No carts.");
		}
		for (Cart cart : carts) {
			logger.info("Cart id - " + cart.getId() + " client id - " + cart.getClient().getId() + " order date - "
					+ cart.getDateOfCreation());
			List<Product> products = cart.getProducts();
			logger.info("Products in cart.");
			if (products.isEmpty()) {
				logger.info("Cart is empty.");
			}
			for (Product product : products) {
				logger.info("Product id - " + product.getId() + ", product login - " + product.getProductName()
						+ ", product price - " + product.getProductPrice());
			}
			logger.info("\n");
		}
		StoreUtil.contOrExit();
	}

	public void update() {
		logger.info("Start updating cart.");
		Cart cart = cartIdInput();
		Client newClient;
		try {
			while (true) {
				logger.info("Change client? yes/no.");
				logger.info("For exit enter - exit");

				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (!userInput.equals("yes") && !userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
					logger.info("For exit enter - exit");
					continue;
				}
				if (userInput.equals("yes")) {
					newClient = cliClientService.clientIdInput();
					cart.setClient(newClient);
					break;
				}
				if (userInput.equals("no")) {
					break;
				}
			}
			while (true) {
				logger.info("Change products? yes/no.");
				logger.info("For exit enter - exit");
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (!userInput.equals("yes") && !userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
					logger.info("For exit enter - exit");
					continue;
				}
				if (userInput.equals("yes")) {
					cart = productsEditor(cart);
					continue;
				}
				if (userInput.equals("no")) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		cartService.update(cart);
		logger.info("Cart updated.");
		StoreUtil.contOrExit();
	}

	public void deleteById() {
		logger.info("Start deleting by id cart.");
		Cart cart = cartIdInput();
		cartService.delete(cart);
		logger.info("Cart deleted.");
		StoreUtil.contOrExit();
	}

	public void deleteAll() {
		logger.info("Start deleting all carts.");

		List<Cart> cartList = cartService.findAll();
		for (Cart cart : cartList) {
			try {
				cartService.delete(cart);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		logger.info("Carts are deleted.");
		StoreUtil.contOrExit();
	}

	private Cart cartIdInput() {
		Cart cart = null;
		int id;
		while (true) {
			logger.info("Enter cart id.\nFor exit enter - exit.");
			try {
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				id = Integer.parseInt(userInput);
				cart = cartService.findById(id);
				if (cart == null) {
					logger.info("Cart with this id does not exist");
					continue;
				}
			} catch (NumberFormatException e) {
				logger.error("Incorect input. Need to enter number.");
				logger.info("Incorrect input. Please enter number");
				continue;
			} catch (IOException e) {
				logger.error(e);
			}
			break;
		}
		return cart;
	}

	private Cart productsEditor(Cart cart) {
		List<Product> products = cart.getProducts();
		try {
			while (true) {
				logger.info("Add product? yes/no.");
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (!userInput.equals("yes") && !userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
					logger.info("For exit enter - exit");
					continue;
				}
				if (userInput.equals("yes")) {
					products.add(cliProductService.productIdInput());
					logger.info("Product added.");
					continue;
				}
				if (userInput.equals("no")) {
					break;
				}
			}
			while (true) {
				logger.info("Remove product? yes/no.");
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (!userInput.equals("yes") && !userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
					logger.info("For exit enter - exit");
					continue;
				}
				if (userInput.equals("yes")) {
					products = removeProduct(products);
					logger.info("Products removed.");
					continue;
				}
				if (userInput.equals("no")) {
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		cart.setProducts(products);
		return cart;
	}

	private List<Product> removeProduct(List<Product> products) {
		try {
			for (int i = 0; i < products.size(); i++) {
				logger.info("Are you want to delete?");
				logger.info("Product id - " + products.get(i).getId() + ", product login - "
						+ products.get(i).getProductName() + ", product price - " + products.get(i).getProductPrice());
				logger.info("yes/no");
				logger.info("For exit enter - exit");
				userInput = reader.readLine();
				StoreUtil.isExit(userInput);
				if (userInput.equals("yes")) {
					products.remove(i);
				}
				if (userInput.equals("no")) {
					continue;
				}
				if (!userInput.equals("yes") && userInput.equals("no")) {
					logger.info("Enter correct command: yes/no.");
					logger.info("For exit enter - exit");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return products;
	}
}
