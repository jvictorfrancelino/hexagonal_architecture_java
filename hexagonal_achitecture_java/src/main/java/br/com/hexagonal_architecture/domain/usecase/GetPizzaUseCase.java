package br.com.hexagonal_architecture.domain.usecase;

import br.com.hexagonal_architecture.domain.model.PizzaResponse;

public interface GetPizzaUseCase {
    PizzaResponse getPizza();
}
