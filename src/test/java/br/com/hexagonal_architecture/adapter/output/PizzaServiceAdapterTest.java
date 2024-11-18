package br.com.hexagonal_architecture.adapter.output;

import br.com.hexagonal_architecture.domain.model.Pizza;
import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import br.com.hexagonal_architecture.ports.output.HttpClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceAdapterTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private PizzaServiceAdapter pizzaAdapter;

    @Value("${api.pizza.url}")
    private String apiUrl;

    @Test
    @DisplayName("Deve retornar lista de pizzas quando chamada é bem sucedida")
    void shouldReturnPizzasWhenCallIsSuccessful(){
        // Arrange
        PizzaResponse mockResponse = createMockPizzaResponse();
        when(httpClient.get(apiUrl, PizzaResponse.class)).thenReturn(mockResponse);

        // Act
        PizzaResponse result = pizzaAdapter.getPizza();

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getReturnCode());
        assertEquals(2, result.getPizzasList().size());

        Pizza firstPizza = result.getPizzasList().get(0);
        assertEquals("Margherita", firstPizza.getFlavor());
        assertEquals("Traditional", firstPizza.getBorder());
        assertEquals(29.90, firstPizza.getPrice());
    }

    @Test
    @DisplayName("Deve propagar exceção quando API retorna erro")
    void shouldPropagateExceptionWhenApiFails() {
        // Arrange
        when(httpClient.get(apiUrl, PizzaResponse.class))
                .thenThrow(new RestClientException("API Error"));

        // Act & Assert
        assertThrows(RestClientException.class, () -> {
            pizzaAdapter.getPizza();
        });
    }

    private PizzaResponse createMockPizzaResponse() {
        PizzaResponse response = new PizzaResponse();
        response.setReturnCode(200);
        response.setMessageCode("Success");

        List<Pizza> pizzas = Arrays.asList(
                createPizza(1, "Margherita", "Traditional", 29.90, "margherita.jpg"),
                createPizza(2, "Pepperoni", "Cheese", 34.90, "pepperoni.jpg")
        );

        response.setPizzasList(pizzas);
        return response;
    }

    private Pizza createPizza(int id, String flavor, String border, double price, String img) {
        Pizza pizza = new Pizza();
        pizza.setId(id);
        pizza.setFlavor(flavor);
        pizza.setBorder(border);
        pizza.setPrice(price);
        pizza.setImg(img);
        return pizza;
    }
}
