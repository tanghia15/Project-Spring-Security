package com.example.exam.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cm_permission")
public class Permission {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cm_permission_id_seq")
  @SequenceGenerator(
      name = "cm_permission_id_seq",
      sequenceName = "cm_permission_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;
}
