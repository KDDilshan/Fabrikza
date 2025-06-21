package com.kavindu.fabrikza.Authentication.Dto.Response;

import com.kavindu.fabrikza.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private int id;
    private String email;
    private List<Product> products;

    public UserProfileResponse(Integer id,String email, byte[] imageBytes, List<Product> products) {
        this.id=id;
        this.email = email;
        this.products = products;
    }
}
