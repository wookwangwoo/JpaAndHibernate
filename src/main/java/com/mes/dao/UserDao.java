package com.mes.dao;

import com.mes.entity.User;

import java.sql.SQLException;
import java.util.Date;

public class UserDao extends JpaDao<User> implements GenericDao<User>{
    public UserDao() {
    }

    @Override
    public User create(User user) {
        user.setCreated_at(new Date());
        user.setUpdated_at(new Date());
        return super.create(user);
    }

    @Override
    public User find(Object id) {
        User user = new User();
        try {
            user =  super.find(User.class,id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    return user;

    }

    @Override
    public User update(User user) {
        return super.update(user);
    }

    @Override
    public void delete(Object id) {
        super.delete(User.class,id);
    }
}
