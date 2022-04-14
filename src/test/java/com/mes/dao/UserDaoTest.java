package com.mes.dao;

import com.mes.entity.User;
import com.mes.exception.HashGenerationException;
import com.mes.util.HashGenerator;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    public void createUserDao(){
        try {
            User user = new User();
            String userId = "wookwangwoo";
            String password = "1q2w3e4r";
            String name = "우광우";
            String md5Password = HashGenerator.generateMD5(password);

            user.setUserId(userId);
            user.setPassword(md5Password);
            user.setName(name);

            User createdUser = userDao.create(user);
            System.out.println("createdUser : " + createdUser.toString());

            assertTrue(createdUser instanceof User);

            assertEquals(userId,createdUser.getUserId());
            assertEquals(name,createdUser.getName());
            assertEquals(md5Password,createdUser.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createUserDaoUnique(){




    }

    @Test
    public void findUser(){
        Integer id = 3;

        User user = userDao.find(id);

        assertTrue(user instanceof User);
        assertEquals(id,user.getId());


    }

    @Test
    public void updateUser(){
        Integer id = 3;
        String userId = "woo수정";
        String password = "1234";
        String name = "우광광수정";
        String Md5password = HashGenerator.generateMD5(password);

        User user = new User();
        user.setId(id);
        user.setUserId(userId);
        user.setPassword(Md5password);
        user.setName(name);

        /*User findUser = userDao.find(id);

        assertTrue(findUser instanceof User);*/
        User updateUser = userDao.update(user);

        assertTrue(updateUser instanceof User);
        assertEquals(updateUser.getId(),user.getId());
    }

    @Test
    public void deleteUser(){
        int id = 3;

        userDao.delete(id);

        User findUser = userDao.find(id);

        assertNull(findUser);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteFailedUser(){
        userDao.delete(654654);
    }

}