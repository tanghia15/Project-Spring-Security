package com.example.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cm_user_role")
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cm_user_role_id_seq")
    @SequenceGenerator(
            name = "cm_user_role_id_seq",
            sequenceName = "cm_user_role_id_seq",
            allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "role_id")
//    private Long roleId;
}
