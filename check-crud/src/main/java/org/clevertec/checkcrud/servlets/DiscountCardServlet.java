package org.clevertec.checkcrud.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clevertec.checkcrud.mapper.DiscountCardMapper;
import org.clevertec.checkcrud.mapper.DiscountCardMapperImpl;
import org.clevertec.checkcrud.model.DiscountCard;
import org.clevertec.checkcrud.repository.DiscountCardRepositoryImpl;
import org.clevertec.checkcrud.service.DiscountCardService;
import org.clevertec.checkcrud.service.DiscountCardServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class DiscountCardServlet extends HttpServlet {

    private final DiscountCardService discountCardService;
    private final ObjectMapper objectMapper;
    private final DiscountCardMapper discountCardMapper;

    public DiscountCardServlet() {
        this.discountCardService = new DiscountCardServiceImpl(new DiscountCardMapperImpl(), new DiscountCardRepositoryImpl());
        this.objectMapper = new ObjectMapper();
        this.discountCardMapper = new DiscountCardMapperImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        DiscountCard discountCard = discountCardMapper.toEntity(discountCardService.getDiscountCardById(Integer.parseInt(id)));
        discountCard.setId(Integer.parseInt(id));
        printWriter.println(objectMapper.writeValueAsString(discountCard));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        DiscountCard discountCard = objectMapper.readValue(reqBody, DiscountCard.class);
        discountCardService.saveDiscountCard(discountCardMapper.toDto(discountCard));
        PrintWriter output = resp.getWriter();
        output.print("The discount card with id " + discountCard.getId() + " created");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int paramId = Integer.parseInt(req.getParameter("id"));
        DiscountCard discountCard = objectMapper.readValue(reqBody, DiscountCard.class);
        discountCard.setId(paramId);
        discountCardService.updateDiscountCard(discountCardMapper.toDto(discountCard));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter output = resp.getWriter();
        String id = req.getParameter("id");
        discountCardService.deleteDiscountCard(Integer.parseInt(id));
        output.print("The discount card with id " + Integer.parseInt(id) + " deleted");
    }
}
