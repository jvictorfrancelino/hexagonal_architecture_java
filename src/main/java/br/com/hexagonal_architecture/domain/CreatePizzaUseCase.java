package br.com.hexagonal_architecture.domain;

public interface CreatePizzaUseCase {
    PizzaResponse createPizza(Pizza pizza);
}
