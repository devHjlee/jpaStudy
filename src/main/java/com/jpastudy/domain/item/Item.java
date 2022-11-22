package com.jpastudy.domain.item;

import com.jpastudy.domain.Category;
import com.jpastudy.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//상속관계 전략
@DiscriminatorColumn(name = "itemType") // 싱글테이블 book album movie 구분값
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}