package io.sudhan.foodserviceapi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    @NotNull
    @ManyToOne(optional = false)
    @JoinTable(name = "product_category_id")
    private ProductCategory category;

    public Product(UUID id, String name, String description, BigDecimal price, int quantity, String url, ProductCategory category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = url;
        this.category = category;
    }

    public Product(){}

    @JsonIgnoreProperties
    public boolean isValid(){
        return !this.name.isBlank() && this.price != null;
    }
}
