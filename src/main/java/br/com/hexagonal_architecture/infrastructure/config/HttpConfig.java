package br.com.hexagonal_architecture.infrastructure.config;

import br.com.hexagonal_architecture.ports.output.HttpClient;
import br.com.hexagonal_architecture.infrastructure.http.RestTemplateHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class HttpConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }

    @Bean
    public HttpClient httpClient(RestTemplate restTemplate){
        return new RestTemplateHttpClient(restTemplate);
    }
}
