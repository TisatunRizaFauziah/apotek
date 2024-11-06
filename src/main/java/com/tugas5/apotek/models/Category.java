package com.tugas5.apotek.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data //mewakili getter & setter
@Entity //bagian dari JPA (untuk menyambung ke DB)
public class Category {
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
}
