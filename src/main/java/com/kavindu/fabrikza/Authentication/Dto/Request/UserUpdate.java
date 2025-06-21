package com.kavindu.fabrikza.Authentication.Dto.Request;

import com.kavindu.fabrikza.Authentication.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {
    private Integer id;
    private AppUser appUser;
}
