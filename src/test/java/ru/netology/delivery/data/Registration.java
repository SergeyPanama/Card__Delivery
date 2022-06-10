package ru.netology.delivery.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Registration {
    private  String city;
    private  String name;
    private  String phone;

}
