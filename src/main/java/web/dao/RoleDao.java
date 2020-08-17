package web.dao;

import web.model.Role;

import java.sql.SQLException;

public interface RoleDao {
    public Role getOne(Long id) throws SQLException;
}
