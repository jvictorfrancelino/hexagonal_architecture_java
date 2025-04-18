package br.com.hexagonal_architecture.adapter.output;

import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import br.com.hexagonal_architecture.ports.output.HttpClient;
import br.com.hexagonal_architecture.ports.output.PizzaServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PizzaServiceAdapter implements PizzaServicePort {

    private final HttpClient httpClient;
    private final String pizzaServiceUrl;

    public PizzaServiceAdapter(HttpClient httpClient, @Value("${external.pizza.api.url}") String pizzaServiceUrl){
        this.httpClient = httpClient;
        this.pizzaServiceUrl = pizzaServiceUrl;
    }

    @Override
    public PizzaResponse getPizza(){
        return httpClient.get(pizzaServiceUrl, PizzaResponse.class);
    }

}
