package com.example.exam.repository;

import com.example.exam.entity.Permission;
import com.example.exam.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepo extends JpaRepository<RolePermission, Long> {
    @Query("SELECT p FROM Permission p JOIN RolePermission rp ON p.id = rp.permissionId WHERE rp.roleId IN :roleIds")
    List<Permission> findPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);
}

