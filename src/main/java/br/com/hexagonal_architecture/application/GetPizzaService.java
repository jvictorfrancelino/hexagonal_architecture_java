package br.com.hexagonal_architecture.application;

import br.com.hexagonal_architecture.domain.usecase.GetPizzaUseCase;
import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import br.com.hexagonal_architecture.ports.output.PizzaServicePort;
import org.springframework.stereotype.Service;

@Service
public class GetPizzaService implements GetPizzaUseCase {

    private final PizzaServicePort pizzaServicePort;

    public GetPizzaService(PizzaServicePort pizzaServicePort){
        this.pizzaServicePort = pizzaServicePort;
    }

    @Override
    public PizzaResponse getPizza() {
        return pizzaServicePort.getPizza();
    }
}
