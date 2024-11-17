package br.com.hexagonal_architecture.infrastructure.http;

import br.com.hexagonal_architecture.ports.output.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;


@Component
public class RestTemplateHttpClient implements HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateHttpClient.class);
    private final RestTemplate restTemplate;

    public RestTemplateHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T get(String url, Class<T> responseType){
        logger.info("Iniciando requisição GET para URL: {}", url);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<?> entity = new HttpEntity<>(headers);

            logger.debug("Headers configurados: {}", headers);

            long startTime = System.currentTimeMillis();

            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    responseType
            );

            long endTime = System.currentTimeMillis();
            logger.info("Requisição GET concluída em {}ms", endTime - startTime);

            HttpStatusCode statusCodeResult = response.getStatusCode();

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Requisição bem-sucedida - Status: {}", statusCodeResult);
                return response.getBody();
            } else {
                logger.warn("Resposta não esperada da API - Status: {}", statusCodeResult);
                throw new HttpServerErrorException(statusCodeResult, "Resposta não esperada da API: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException e) {
            logger.error("Erro do cliente HTTP - Status: {} - URL: {} - Resposta: {}", e.getStatusCode(), url, e.getResponseBodyAsString());
            throw new RestClientException("Erro ao processar requisição: " + e.getMessage(), e);
        } catch (HttpServerErrorException e){
            logger.error("Erro do servidor - Status: {} - URL: {}", e.getStatusCode(), url);
            throw new RestClientException("Erro no servidor remoto: " + e.getMessage(), e);
        } catch (ResourceAccessException e) {
            logger.error("Erro de conexão ao acessar a URL: {} - Erro: {}", url, e.getMessage());
            throw new RestClientException("Erro de conexão com o servidor: " + e.getMessage(), e);
        } catch (Exception e){
            logger.error("Erro inesperado ao realizar requisição GET - URL: {}", url, e);
            throw new RestClientException("Erro inesperado ao processar requisição: " + e.getMessage(), e);
        }

    }
}
