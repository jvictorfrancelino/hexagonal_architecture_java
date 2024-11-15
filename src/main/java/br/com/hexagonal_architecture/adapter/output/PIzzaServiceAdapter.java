package br.com.hexagonal_architecture.adapter.output;

import br.com.hexagonal_architecture.port.output.PizzaServicePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PIzzaServiceAdapter implements PizzaServicePort {

    private final RestTemplate restTemplate;
    private final String pizzaServiceUrl;

    public PizzaServiceAdapter(RestTemplate restTemplate, @Value("${external.pizza.api.url}") String pizzaServiceUrl){
        this.restTemplate = restTemplate;
        this.pizzaServiceUrl = pizzaServiceUrl;
    }

    @Override
    public PizzaResponse createPizza(Pizza pizza){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Pizza> request = new HttpEntity<>(pizza, headers);
        ResponseEntity<PizzaResponse> response = restTempalte.postForEntity(pizzaServiceUrl, request, PizzaResponse.class);

        return response.getBody();
    }

}
