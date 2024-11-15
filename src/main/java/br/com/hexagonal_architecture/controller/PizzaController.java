package br.com.hexagonal_architecture.controller;

import br.com.hexagonal_architecture.domain.CreatePizzaUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local/pizzas")
public class PizzaController {

    private final CreatePizzaUseCase createPizzaUseCase;

    public PizzaController(CreatePizzaUseCase createPizzaUseCase) {
        this.createPizzaUseCase = createPizzaUseCase;

    }
}
