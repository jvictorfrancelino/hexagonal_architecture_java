package br.com.hexagonal_architecture.ports.output;

public interface HttpClient {
    <T> T get(String url, Class<T> responseType);
//    <T> T post(String url, Object body, Class<T> responseType);

}
