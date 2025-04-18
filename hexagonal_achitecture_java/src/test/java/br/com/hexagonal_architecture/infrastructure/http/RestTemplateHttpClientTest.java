package br.com.hexagonal_architecture.infrastructure.http;

import br.com.hexagonal_architecture.domain.model.Pizza;
import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestTemplateHttpClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RestTemplateHttpClient httpClient;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(httpClient, "accessToken", "test-token");
    }

    @Test
    @DisplayName("Deve buscar lista de pizzas com sucesso")
    void shouldFetchPizzasSuccessfully() {
        // Arrange
        String url = "http://api.example.com/pizzas";
        PizzaResponse expectedResponse = createMockPizzaResponse();

        ResponseEntity<PizzaResponse> responseEntity = new ResponseEntity<>(
                expectedResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                eq(url),
        eq(HttpMethod.GET),
        any(HttpEntity.class),
        eq(PizzaResponse.class)
        )).thenReturn(responseEntity);

        // Act
        PizzaResponse result = httpClient.get(url, PizzaResponse.class);

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getReturnCode());
        assertEquals(2, result.getPizzasList().size());
        assertEquals("Margherita", result.getPizzasList().get(0).getFlavor());

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
