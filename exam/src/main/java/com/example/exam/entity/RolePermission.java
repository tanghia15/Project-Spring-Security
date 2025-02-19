package com.example.exam.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "cm_role_permission")
public class RolePermission {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cm_role_permission_id_seq")
  @SequenceGenerator(
      name = "cm_role_permission_id_seq",
      sequenceName = "cm_role_permission_id_seq",
      allocationSize = 1)
  private Long id;

  @Column(name = "role_id")
  private Long roleId;

  @Column(name = "permission_id")
  private Long permissionId;
}
