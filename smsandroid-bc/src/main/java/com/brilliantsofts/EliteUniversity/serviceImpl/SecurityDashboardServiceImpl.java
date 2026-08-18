package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.repository.LoginSessionRepository;
import com.brilliantsofts.EliteUniversity.repository.PermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.RoleRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.SecurityDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityDashboardServiceImpl implements SecurityDashboardService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private LoginSessionRepository loginSessionRepository;

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("activeUsers", userRepository.countByEnabledTrue());
        stats.put("inactiveUsers", userRepository.countByEnabledFalse());
        stats.put("onlineUsers", loginSessionRepository.countByIsActiveTrue());
        stats.put("totalRoles", roleRepository.count());
        stats.put("totalPermissions", permissionRepository.count());
        stats.put("activeSessions", loginSessionRepository.countByIsActiveTrue());
        return stats;
    }

    @Override
    public Map<String, Object> getLoginStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeSessions", loginSessionRepository.countByIsActiveTrue());
        stats.put("totalSessions", loginSessionRepository.count());
        return stats;
    }
}
