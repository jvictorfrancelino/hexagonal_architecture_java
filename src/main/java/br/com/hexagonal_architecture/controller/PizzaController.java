package br.com.hexagonal_architecture.controller;

import br.com.hexagonal_architecture.domain.usecase.GetPizzaUseCase;
import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/local/pizzas")
public class PizzaController {

    private final GetPizzaUseCase getPizzaUseCase;

    public PizzaController(GetPizzaUseCase getPizzaUseCase) {
        this.getPizzaUseCase = getPizzaUseCase;
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PizzaResponse> getPizza(){
        PizzaResponse response = getPizzaUseCase.getPizza();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
