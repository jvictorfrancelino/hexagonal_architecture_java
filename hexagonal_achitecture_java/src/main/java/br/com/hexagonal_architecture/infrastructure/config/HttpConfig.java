package br.com.hexagonal_architecture.infrastructure.config;

import br.com.hexagonal_architecture.ports.output.HttpClient;
import br.com.hexagonal_architecture.infrastructure.http.RestTemplateHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


public class HttpConfig {

    @Bean
    public HttpClient httpClient(RestTemplate restTemplate){
        return new RestTemplateHttpClient(restTemplate);
    }
}
