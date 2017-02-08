package com.andersen.controllers;

import com.andersen.domain.Cart;
import com.andersen.domain.Client;
import com.andersen.domain.Product;
import com.andersen.service.CartService;
import com.andersen.service.ClientService;
import com.andersen.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ControllerClass {

    private Logger logger = Logger.getLogger(ControllerClass.class);

    @RequestMapping("/")
    public ModelAndView start() {
        return new ModelAndView("index");
    }

    @RequestMapping("/client")
    public ModelAndView clientPage() {
        return new ModelAndView("clientPage");
    }

    @RequestMapping("/product")
    public ModelAndView productPage() {
        return new ModelAndView("productPage");
    }

    @RequestMapping("/cart")
    public ModelAndView cartPage() {
        return new ModelAndView("cartPage");
    }

    @RequestMapping("/createClient")
    public ModelAndView createClient(HttpServletRequest req) {
        logger.info("Start CreateClientServlet doPost.");
        String clientLogin = req.getParameter("clientName");
        String message = "\nClient " + clientLogin + " is added.";
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        List<Client> clientList = clientService.findAll();
        for (Client client : clientList) {
            if (clientLogin.equals("")) {
                message = "\nLogin is empty.";
                return new ModelAndView("answerPage", "message", message);
            }
            if (client.getLogin().equals(clientLogin)) {
                message = "\nLogin is already exist.";
                return new ModelAndView("answerPage", "message", message);
            }
        }
        Client newClient = new Client(clientLogin);
        clientService.create(newClient);
        return new ModelAndView("answerPage", "message", message);
    }

    @RequestMapping("/findClient")
    public ModelAndView findClient(HttpServletRequest req) {
        logger.info("Start finding client.");
        Integer id ;
        try {
            id = Integer.parseInt(req.getParameter("clientIdToFind"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect input please enter number.");
        }
        Client client = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        List<Client> clients = clientService.findAll();
        for(Client c : clients) {
            if (c.getId() == id) {
                client = c;
            }
        }
        if (client == null) {
            return new ModelAndView("answerPage", "message", "Client with this id does not exist");
        }
        return new ModelAndView("answerPage", "message", "Client id - " + id + " client login - " + client.getLogin());
    }

    @RequestMapping("/findAllClients")
    public ModelAndView findAllClients() {
        logger.info("Start finding all clients.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        List<Client> clients = clientService.findAll();
        if (clients == null) {
            return new ModelAndView("findAllClientsPage", "message", null);
        }
        return new ModelAndView("findAllClientsPage", "message", clients);
    }

    @RequestMapping("/updateClient")
    public ModelAndView updateClient(HttpServletRequest req){
        logger.info("Start updating client.");
        Integer id;
        try {
            id = Integer.parseInt(req.getParameter("clientIdToUpdate"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number.");
        }
        String newLogin = req.getParameter("clientLoginToUpdate");
        if (newLogin.equals("")) {
            return new ModelAndView("answerPage", "message", "Please enter login.");
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        List<Client> clients = clientService.findAll();
        for (Client c : clients) {
            if (c.getId() == id) {
                c.setLogin(newLogin);
                clientService.update(c);
                return new ModelAndView("answerPage", "message", "Client updated");
            }
        }
        return new ModelAndView("answerPage", "message", "Client with this id does not exist");
    }

    @RequestMapping("/deleteClient")
    public ModelAndView deleteClient(HttpServletRequest req) {
        logger.info("Start deleting client.");
        Integer id;
        try {
            id = Integer.parseInt(req.getParameter("clientIdToDel"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number.");
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        List<Client> clients = clientService.findAll();
        for(Client c : clients) {
            if (c.getId() == id) {
                clientService.delete(c);
                return new ModelAndView("answerPage", "message", ("Client " + c.getLogin() + " deleted"));
            }
        }
        return new ModelAndView("answerPage", "message", "Client with this id does not exist");
    }

    @RequestMapping("/createProduct")
    public ModelAndView createProduct(HttpServletRequest req) {
        logger.info("Start creating product.");
        String productName = req.getParameter("productNameCr");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        Integer price;
        try {
            price = Integer.parseInt(req.getParameter("productPriceCr"));
        } catch (NumberFormatException e ) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "The products price must by a number.");
        }
        if ((productName == null)||(productName.equals(""))) {
            return new ModelAndView("answerPage", "message", "The product mast have a name.");
        }
        if(price == 0) {
            return new ModelAndView("answerPage", "message", "The product mast have prise bigger then 0.");
        }
        List<Product> products = service.findAll();
        for (Product p : products) {
            if (p.getProductName().equals(productName)) {
                return new ModelAndView("answerPage", "message", "Product with this name is already exist.");
            }
        }
        Product newProduct = new Product(productName, price);
        service.create(newProduct);
        return new ModelAndView("answerPage", "message", "Product is added.");
    }

    @RequestMapping("/findProduct")
    public ModelAndView findProduct(HttpServletRequest req) {
        logger.info("Start finding product.");
        Integer id ;
        try {
            id = Integer.parseInt(req.getParameter("productIdToFind"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number.");
        }
        Product product = null;
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        List<Product> products = service.findAll();
        for(Product p : products) {
            if (p.getId() == id) {
                product = p;
            }
        }
        if (product == null) {
            return new ModelAndView("answerPage", "message", "Product with this id does not exist");
        }
        return new ModelAndView("answerPage", "message", ("Product id: " + product.getId() + ". Product name: " + product.getProductName()
                + ". Product price: " + product.getProductPrice()));
    }

    @RequestMapping("/findAllProducts")
    public ModelAndView findAllProducts() {
        logger.info("Start finding all clients.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        List<Product> products = service.findAll();
        return new ModelAndView("findAllProductsPage", "message", products);
    }

    @RequestMapping("/updateProduct")
    public ModelAndView updateProduct(HttpServletRequest req) {
        logger.info("Start updating product.");
        Integer id ;
        Integer newPrice ;
        try{
            id = Integer.parseInt(req.getParameter("productIdToUpdate"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number.");
        }
        String newName = req.getParameter("productNameToUpdate");
        if (newName.equals("")) {
            return new ModelAndView("answerPage", "message", "Please, enter product name.");
        }
        try{
            newPrice = Integer.parseInt(req.getParameter("productPriceToUpdate"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect prise input. Please enter number.");
        }
        if (newPrice == 0) {
            return new ModelAndView("answerPage", "message", "Please enter not zero price.");
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        List<Product> products = service.findAll();
        for (Product p : products) {
            if (p.getId() == id) {
                p.setProductName(newName);
                p.setProductPrice(newPrice);
                service.update(p);
                return new ModelAndView("answerPage", "message", "Product updated");
            }
        }
        return new ModelAndView("answerPage", "message", "Product with this id does not exist");
    }

    @RequestMapping("/deleteProduct")
    public ModelAndView deleteProduct(HttpServletRequest req) {
        logger.info("Start deleting product.");
        Integer id ;
        try {
            id = Integer.parseInt(req.getParameter("productIdToDel"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number");
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ProductService service = (ProductService) context.getBean("productService");
        List<Product> products = service.findAll();
        for(Product p : products) {
            if (p.getId() == id) {
                service.delete(p);
                return new ModelAndView("answerPage", "message", "Product " + p.getProductName() + " deleted");
            }
        }
        return new ModelAndView("answerPage", "message", "Product with this id does not exist");
    }

    @RequestMapping("/createCart")
    public ModelAndView createCart(HttpServletRequest req) {
        logger.info("Start creating cart.");
        ModelAndView modelAndView = new ModelAndView("createCartPage");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService)context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        Integer clientId ;
        try {
            clientId = Integer.parseInt(req.getParameter("clientIdToCr"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect id input. Please enter number");
        }
        List<Client> clients = clientService.findAll();
        List<Product> products = productService.findAll();
        boolean isClientExist = false;
        for (Client c : clients) {
            if (c.getId() == clientId) {
                isClientExist = true;
            }
        }
        if(!isClientExist) {
            return new ModelAndView("answerPage", "message", "Client with this id does not exist.");
        }
        modelAndView.addObject("clientId", clientId);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("/newCart")
    public ModelAndView createdCart(HttpServletRequest req) {
        logger.info("Creating cart");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService)context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        CartService cartService = (CartService)context.getBean("cartService");
        Integer clientId = Integer.parseInt(req.getParameter("clientId"));
        String selectedProducts = req.getParameter("allProducts");
        Client client = clientService.findById(clientId);
        List<Product> allProducts = productService.findAll();
        Cart cart = new Cart();
        if(selectedProducts.equals(""))  {
            return new ModelAndView("answerPage", "message", "Cant create cart without products.");
        }
        cart.setClient(client);
        List<String> products = Arrays.asList(selectedProducts.split(","));
        List<Product> cartProducts = new ArrayList<Product>();
        for (String productName : products) {
            for (Product p : allProducts) {
                if(p.getProductName().equals(productName)) {
                    cartProducts.add(p);
                }
            }
        }
        cart.setProducts(cartProducts);
        cartService.create(cart);
        return new ModelAndView("answerPage", "message", "Cart is added.");
    }

    @RequestMapping("/findCart")
    public ModelAndView findCart(HttpServletRequest req) {
        logger.info("Start finding cart.");
        ModelAndView modelAndView = new ModelAndView("findCartPage");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService)context.getBean("cartService");
        Integer cartId ;
        try {
            cartId = Integer.parseInt(req.getParameter("cartIdToFind"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect input. Please enter number.");
        }
        Cart cart = cartService.findById(cartId);
        if (cart == null) {
            return new ModelAndView("answerPage", "message", "Cart with this id does not exist.");
        }
        String client = "Cart id: " + cart.getId() + " Client id: " + cart.getClient().getId() + " Order date: "
                + cart.getDateOfCreation();
        modelAndView.addObject("client", client);
        List<Product> products = cart.getProducts();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @RequestMapping("/findAllCarts")
    public ModelAndView findAllCarts() {
        logger.info("Start finding all carts.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService service = (CartService)context.getBean("cartService");
        List<Cart> carts = service.findAll();
        if(carts.isEmpty()) {
            return new ModelAndView("answerPage", "message", "No current carts.");
        }
        return new ModelAndView("findAllCartsPage", "carts", carts);
    }

    @RequestMapping("/updateCart")
    public ModelAndView updateCart(HttpServletRequest req) {
        logger.info("Start updating cart.");
        ModelAndView modelAndView = new ModelAndView("updateCartPage");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService)context.getBean("cartService");
        ProductService productService = (ProductService)context.getBean("productService");
        Integer cartId;
        try {
            cartId = Integer.parseInt(req.getParameter("cartIdToUpdate"));
        } catch (Exception e ) {
            return new ModelAndView("answerPage", "massage", "Incorrect input.Please enter number.");
        }
        List<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        Cart cart = cartService.findById(cartId);
        if(cart == null) {
            return new ModelAndView("answerPage", "massage", "Cart with this id does not exist.");
        }
        modelAndView.addObject("cart", cart);
        StringBuilder cartProductsStringBuilder = new StringBuilder();
        for(Product p : cart.getProducts()) {
            cartProductsStringBuilder.append(p.getProductName());
            cartProductsStringBuilder.append(",");
        }
        String cartProductsString = cartProductsStringBuilder.toString();
        modelAndView.addObject("cartProducts", cartProductsString);
        return modelAndView;
    }

    @RequestMapping("/updatedCart")
    public ModelAndView updatedCart(HttpServletRequest req) {
        logger.info("Updating cart.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        ClientService clientService = (ClientService) context.getBean("clientService");
        ProductService productService = (ProductService)context.getBean("productService");
        CartService cartService = (CartService) context.getBean("cartService");
        Integer cartId = Integer.parseInt(req.getParameter("cartId"));
        String selectedProducts = req.getParameter("allProducts");
        List<Product> allProducts = productService.findAll();
        Integer newClientId;
        try {
            newClientId = Integer.parseInt(req.getParameter("newClientId"));
        } catch (Exception e ) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "massage", "Incorrect client id input.Please enter number.");
        }
        Client newOwner = clientService.findById(newClientId);
        if(newOwner == null) {
            return new ModelAndView("answerPage", "massage", "Client with this id does not exist.");
        }
        if(selectedProducts.equals(""))  {
            return new ModelAndView("answerPage", "massage", "Can't create cart without products.");
        }
        Cart cart = cartService.findById(cartId);
        cart.setClient(newOwner);
        List<String> products = Arrays.asList(selectedProducts.split(","));
        List<Product> cartProducts = new ArrayList<Product>();
        for (String productName : products) {
            for (Product p : allProducts) {
                if(p.getProductName().equals(productName)) {
                    cartProducts.add(p);
                }
            }
        }
        cart.setProducts(cartProducts);
        cartService.update(cart);
        return new ModelAndView("answerPage", "message", "Cart is updated.");
    }

    @RequestMapping("/deleteCart")
    public ModelAndView deleteCart(HttpServletRequest req) {
        logger.info("Start deleting cart.");
        ApplicationContext context = new ClassPathXmlApplicationContext("store-spring.xml");
        CartService cartService = (CartService) context.getBean("cartService");
        Integer cartId ;
        try{
            cartId = Integer.parseInt(req.getParameter("cartIdToDel"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new ModelAndView("answerPage", "message", "Incorrect cart id input.Please enter number.");
        }
        Cart cart = cartService.findById(cartId);
        if (cart == null) {
            return new ModelAndView("answerPage", "message", "Cart with this id does not exist.");
        }
        cartService.delete(cart);
        return new ModelAndView("answerPage", "message", "Cart deleted.");
    }
}
