package br.com.hexagonal_architecture.application;

import br.com.hexagonal_architecture.domain.CreatePizzaUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreatePizzaService implements CreatePizzaUseCase {

    private final PizzaServicePort pizzaServicePort;

    public CreatePizzaServce(PizzaServicePort pizzaServicePort){
        this.pizzaServicePort = pizzaServicePort;
    }

    @Override
    public PizzaResponse createPizza(Pizza pizza) {
        return pizzaServicePort.createPizza(pizza);
    }
}
