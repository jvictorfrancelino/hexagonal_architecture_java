package br.com.hexagonal_architecture.domain.model;

import lombok.Data;
import java.util.List;

@Data
public class PizzaResponse {
    private int returnCode;
    private String messageCode;
    private List<Pizza> pizzasList;
}
