package br.com.hexagonal_architecture.domain.model;

import java.util.List;

public class PizzaResponse {
    private int returnCode;
    private String messageCode;
    private List<Pizza> pizzasList;

    public PizzaResponse(int returnCode, String messageCode, List<Pizza> pizzasList) {
        this.returnCode = returnCode;
        this.messageCode = messageCode;
        this.pizzasList = pizzasList;
    }

    public PizzaResponse(){}

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public List<Pizza> getPizzasList() {
        return pizzasList;
    }

    public void setPizzasList(List<Pizza> pizzasList) {
        this.pizzasList = pizzasList;
    }



}
