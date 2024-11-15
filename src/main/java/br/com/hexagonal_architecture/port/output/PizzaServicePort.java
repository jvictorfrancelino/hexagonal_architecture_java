package br.com.hexagonal_architecture.port.output;

public interface PizzaServicePort {
    PizzaResponse createPizza(Pizza pizza);
}
