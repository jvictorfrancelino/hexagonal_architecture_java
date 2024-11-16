package br.com.hexagonal_architecture.ports.output;

import br.com.hexagonal_architecture.domain.model.PizzaResponse;

public interface PizzaServicePort {
    PizzaResponse getPizza();
}
