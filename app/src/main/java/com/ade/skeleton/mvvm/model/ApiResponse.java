package com.ade.skeleton.mvvm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Hizkia on 16/01/2017.
 */
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    private T data, error;
}
