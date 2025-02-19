package com.example.exam.sevice;
import com.example.exam.entity.Permission;
import com.example.exam.entity.Role;
import com.example.exam.entity.User;
import com.example.exam.repository.RolePermissionRepo;
import com.example.exam.repository.UserRepo;
import com.example.exam.repository.UserRoleRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AccountService {
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final RolePermissionRepo rolePermissionRepo;
    public AccountService(UserRepo userRepo, UserRoleRepo userRoleRepo, RolePermissionRepo rolePermissionRepo) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.rolePermissionRepo = rolePermissionRepo;
    }
    public Map<String, Object> getUserInfo(UserDetails userDetails) {
        if (userDetails == null) {
            return Map.of("error", "No user is currently logged in.");
        }
        // Tìm user theo usernames
        Optional<User> user = userRepo.findByUsername(userDetails.getUsername());
        // Lấy danh sách roles từ bảng UserRole
        List<Role> roles = userRoleRepo.findRolesByUserId(user.stream().findFirst().get().getId());
        // Nếu không có roles, trả về thông tin mặc định
        if (roles == null || roles.isEmpty()) {
            return Map.of(
                    "username", user.stream().findFirst().get().getUsername(),
                    "roles", "No roles found",
                    "permissions", "No permissions found"
            );
        }
        // Lấy danh sách tên roles
        List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());
        // Lấy danh sách permissions từ RolePermission
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissions = rolePermissionRepo.findPermissionsByRoleIds(roleIds);
        // Kiểm tra nếu permissions là null
        List<String> permissionNames = (permissions != null)
                ? permissions.stream().map(Permission::getName).collect(Collectors.toList())
                : List.of();
        // Trả về thông tin user
        return Map.of(
                "username", user.stream().findFirst().get().getUsername(),
                "roles", roleNames,
                "permissions", permissionNames
        );
    }
}
