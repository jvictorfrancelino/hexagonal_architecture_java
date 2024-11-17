package br.com.hexagonal_architecture.domain.model;

import lombok.Data;

@Data
public class Pizza {
    private int id;
    private String flavor;
    private String border;
    private double price;
    private String img;
}
