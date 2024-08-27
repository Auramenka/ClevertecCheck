package org.clevertec.checkcrud.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clevertec.checkcrud.mapper.ProductMapper;
import org.clevertec.checkcrud.mapper.ProductMapperImpl;
import org.clevertec.checkcrud.model.Product;
import org.clevertec.checkcrud.repository.ProductRepositoryImpl;
import org.clevertec.checkcrud.service.ProductService;
import org.clevertec.checkcrud.service.ProductServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ProductServlet extends HttpServlet {

    private final ProductService productService;
    private final ObjectMapper objectMapper;
    private final ProductMapper productMapper;

    public ProductServlet() {
        this.productService = new ProductServiceImpl(new ProductMapperImpl(), new ProductRepositoryImpl());
        this.objectMapper = new ObjectMapper();
        this.productMapper = new ProductMapperImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        Product product = productMapper.toEntity(productService.getProductById(Integer.parseInt(id)));
        product.setId(Integer.parseInt(id));
        printWriter.println(objectMapper.writeValueAsString(product));
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product = objectMapper.readValue(reqBody, Product.class);
        productService.saveProduct(productMapper.toDto(product));
        PrintWriter output = resp.getWriter();
        output.print("The product with id " + product.getId() + " created");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter output = resp.getWriter();
        String id = req.getParameter("id");
        productService.deleteProduct(Integer.parseInt(id));
        output.print("The product with id " + Integer.parseInt(id) + " deleted");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int paramId = Integer.parseInt(req.getParameter("id"));
        Product product = objectMapper.readValue(reqBody, Product.class);
        product.setId(paramId);
        productService.updateProduct(productMapper.toDto(product));
    }
}
