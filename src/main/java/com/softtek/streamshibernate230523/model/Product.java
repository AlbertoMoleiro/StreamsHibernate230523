package com.softtek.streamshibernate230523.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    private Short productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "supplier_id")
    private Short supplierId;

    @Column(name = "category_id")
    private Short categoryId;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "units_in_stock")
    private Short unitsInStock;

    @Column(name = "units_on_order")
    private Short unitsOnOrder;

    @Column(name = "reorder_level")
    private Short reorderLevel;

    @Column(name = "discontinued")
    private Integer discontinued;

}
