package org.dawn.backend.entity;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Hidden
public class Demo extends AbstractMappedEntity{
    @Column(name = "name")
    private String name;
}
