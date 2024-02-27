package org.example.model;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

        private Long id;
        private String name;
        private double price;
        private LocalDate register_date;

}
