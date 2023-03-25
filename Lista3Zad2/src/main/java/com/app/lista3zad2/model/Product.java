package com.app.lista3zad2.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(Long id, String name, BigDecimal price) {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
