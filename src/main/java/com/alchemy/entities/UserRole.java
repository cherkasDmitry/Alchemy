package com.alchemy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set", builderMethodName = "anUser", toBuilder = true)
@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GenericGenerator(name = "role_id", strategy = "com.alchemy.utils.UUIDIdGenerator")
    @GeneratedValue(generator = "role_id")
    private String id;
    @Column(name = "role_name")
    private String name;
}
