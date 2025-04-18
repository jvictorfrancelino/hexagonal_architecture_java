package br.com.hexagonal_architecture.integration;


import br.com.hexagonal_architecture.domain.model.PizzaResponse;
import br.com.hexagonal_architecture.domain.usecase.GetPizzaUseCase;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;


//Teste de integração usando WireMock
@SpringBootTest
public class PizzaIntegrationTest {

    @Autowired
    private GetPizzaUseCase getPizzaUseCase;

    private WireMockServer wireMockServer;

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Deve integrar com API de pizzas corretamente")
    void shouldIntegrateWithPizzaApi() {
        // Arrange
        stubFor(get(urlEqualTo("/api/pizzas"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(createMockJsonResponse())
                        .withStatus(200)
                )
        );

        // Act
        PizzaResponse response = getPizzaUseCase.getPizza();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getReturnCode());
        assertFalse(response.getPizzasList().isEmpty());

        // Verify if the request was made with correct headers
        verify(getRequestedFor(urlEqualTo("/api/pizzas"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withHeader("Authorization", matching("Bearer.*"))
        );
    }

    private String createMockJsonResponse() {
        return """
            {
                "returnCode": 200,
                "messageCode": "success",
                "pizzasList": [
                    {
                        "id": 1,
                        "flavor": "Margherita",
                        "border": "Traditional,
                        "price": 29.90,
                        "img": "margherita.jpg"
                    },
                    {
                        "id": 2,
                        "flavor": "Pepperoni",
                        "border": "Cheese",
                        "price": 34.90,
                        "img": "pepperoni.jpg"
                    }
                ]
            }
            """;
    }
}
