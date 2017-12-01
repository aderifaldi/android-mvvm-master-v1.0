package com.ade.skeleton.mvvm.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by RadyaLabs PC on 11/10/2017.
 */

@NoArgsConstructor
@Data
public class Product implements Serializable{

    private String status;
    private List<Products> products;

    @NoArgsConstructor
    @Data
    public static class Products implements Serializable {

        private long price;
        private String id;
        private String name;
        private String desc;
        private List<String> images;

    }

}
