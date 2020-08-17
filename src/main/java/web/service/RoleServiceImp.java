package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.sql.SQLException;

@Service
public class RoleServiceImp implements RoleService {

    protected RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getOne(Long id) {
        Role role = null;
        try {
            role = roleDao.getOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
