package org.motechproject.security.service;

import org.motechproject.security.domain.MotechPermission;
import org.motechproject.security.domain.MotechPermissionCouchdbImpl;
import org.motechproject.security.model.PermissionDto;
import org.motechproject.security.repository.AllMotechPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link MotechPermissionService} interface. Uses {@link AllMotechPermissions} in order
 * to retrieve and persist permissions.
 */
@Service("motechPermissionService")
public class MotechPermissionServiceImpl implements MotechPermissionService {

    @Autowired
    private AllMotechPermissions allMotechPermissions;

    @Override
    public List<PermissionDto> getPermissions() {
        List<PermissionDto> permissions = new ArrayList<>();
        for (MotechPermission permission : allMotechPermissions.getPermissions()) {
            permissions.add(new PermissionDto(permission));
        }
        return permissions;
    }

    @Override
    public void addPermission(PermissionDto permission) {
        allMotechPermissions.add(new MotechPermissionCouchdbImpl(permission.getPermissionName(),
                permission.getBundleName()));
    }

    @Override
    public void deletePermission(String permissionName) {
        MotechPermission permission = allMotechPermissions.findByPermissionName(permissionName);
        if (permission != null) {
            allMotechPermissions.delete(permission);
        }
    }

}
