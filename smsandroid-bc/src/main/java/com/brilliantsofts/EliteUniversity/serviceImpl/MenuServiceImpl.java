package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.mapper.MenuMapper;
import com.brilliantsofts.EliteUniversity.dto.request.MenuRequest;
import com.brilliantsofts.EliteUniversity.dto.response.MenuResponse;
import com.brilliantsofts.EliteUniversity.entity.Menu;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.MenuRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MenuResponse create(MenuRequest request) {
        Menu entity = MenuMapper.toEntity(request);
        if (request.getParentId() != null) {
            repository.findById(request.getParentId()).ifPresent(entity::setParent);
        }
        return MenuMapper.toResponse(repository.save(entity));
    }

    @Override
    public MenuResponse update(Long id, MenuRequest request) {
        Menu entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        entity.setTitle(request.getTitle());
        entity.setIcon(request.getIcon());
        entity.setRoute(request.getRoute());
        entity.setOrderNo(request.getOrderNo());
        entity.setPermissionCode(request.getPermissionCode());
        entity.setModule(request.getModule());
        entity.setVisible(request.isVisible());
        if (request.getParentId() != null) {
            repository.findById(request.getParentId()).ifPresent(entity::setParent);
        } else {
            entity.setParent(null);
        }
        return MenuMapper.toResponse(repository.save(entity));
    }

    @Override
    public MenuResponse getById(Long id) {
        Menu entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));
        return MenuMapper.toResponse(entity);
    }

    @Override
    public List<MenuResponse> getAll() {
        return repository.findAll().stream().map(MenuMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getRootMenus() {
        List<Menu> roots = repository.findByParentIsNullOrderByOrderNoAsc();
        return roots.stream().map(MenuMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getChildren(Long parentId) {
        return repository.findByParentIdOrderByOrderNoAsc(parentId).stream().map(MenuMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getMyMenus(Long userId) {
        User user = userId != null ? userRepository.findById(userId).orElse(null) : null;
        return filterMenusForUser(user);
    }

    @Override
    public List<MenuResponse> getMyMenusForUsername(String username) {
        User user = username != null ? userRepository.findByUsernameOrEmail(username, username).orElse(null) : null;
        return filterMenusForUser(user);
    }

    private List<MenuResponse> filterMenusForUser(User user) {
        List<Menu> roots = repository.findByParentIsNullOrderByOrderNoAsc();

        if (user == null || user.getRole() == UserRole.SUPER_ADMIN || user.getRole() == UserRole.ADMIN) {
            return roots.stream()
                    .filter(m -> !"Applicant Portal".equalsIgnoreCase(m.getTitle()))
                    .map(MenuMapper::toResponse)
                    .collect(Collectors.toList());
        }

        if (user.getRole() == UserRole.APPLICANT) {
            return roots.stream()
                    .filter(m -> "Applicant Portal".equalsIgnoreCase(m.getTitle()) || "Applicant Portal".equalsIgnoreCase(m.getModule()))
                    .map(MenuMapper::toResponse)
                    .collect(Collectors.toList());
        }

        if (user.getRole() == UserRole.STUDENT) {
            return roots.stream()
                    .filter(m -> "Student Portal".equalsIgnoreCase(m.getTitle()) || "Student Portal".equalsIgnoreCase(m.getModule()))
                    .map(MenuMapper::toResponse)
                    .collect(Collectors.toList());
        }

        if (user.getRole() == UserRole.TEACHER || user.getRole() == UserRole.DEPARTMENT_HEAD || user.getRole() == UserRole.ADVISOR) {
            return roots.stream()
                    .filter(m -> !"Applicant Portal".equalsIgnoreCase(m.getTitle()))
                    .filter(m -> "Teachers".equalsIgnoreCase(m.getTitle())
                            || "Dashboard".equalsIgnoreCase(m.getTitle())
                            || "Academic".equalsIgnoreCase(m.getTitle())
                            || "Students".equalsIgnoreCase(m.getTitle())
                            || "LMS".equalsIgnoreCase(m.getTitle())
                            || "Examination".equalsIgnoreCase(m.getTitle())
                            || "Communication".equalsIgnoreCase(m.getTitle()))
                    .map(MenuMapper::toResponse)
                    .collect(Collectors.toList());
        }

        return roots.stream()
                .filter(m -> !"Applicant Portal".equalsIgnoreCase(m.getTitle()))
                .map(MenuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
