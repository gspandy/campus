package org.campus.service;

import org.campus.BaseTest;
import org.campus.model.FreshNews;
import org.campus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/user/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/user/save.xml")
    public void testFindByUserId() {
        User user = userService.findByUserId("123");
        Assert.assertEquals(user.getNickname(), "测试");
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/attention/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/attention/save.xml")
    public void testCountAttention() {
        int countAttention = userService.countAttention("123");
        Assert.assertTrue(countAttention == 2);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/attention/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/attention/save.xml")
    public void testCountFans() {
        int countFans = userService.countFans("123");
        Assert.assertTrue(countFans == 1);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/fresh/save.xml")
    public void testCountPost() {
        int countAttention = userService.countPost("123");
        Assert.assertTrue(countAttention == 1);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/fresh/save.xml")
    public void testFindUserPhotos() {
        Pageable pageable = new PageRequest(0, 10);
        Page<FreshNews> photos = userService.findUserPhotos("123", pageable);
        Assert.assertTrue(photos.getTotalElements() == 1);
    }

}
