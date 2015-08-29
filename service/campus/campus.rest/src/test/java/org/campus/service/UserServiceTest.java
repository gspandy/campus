package org.campus.service;

import java.util.List;

import org.campus.BaseTest;
import org.campus.model.Comment;
import org.campus.model.FreshNews;
import org.campus.model.User;
import org.campus.model.enums.DisplayModel;
import org.campus.model.enums.InteractType;
import org.campus.repository.AttentionUserMapper;
import org.campus.repository.CommentMapper;
import org.campus.repository.FreshNewsMapper;
import org.campus.repository.NotSupportMapper;
import org.campus.repository.ShareMapper;
import org.campus.repository.SupportMapper;
import org.campus.vo.CommentAddVO;
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

    @Autowired
    private SupportMapper supportMapper;

    @Autowired
    private NotSupportMapper notSupportMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private FreshNewsMapper freshNewsMapper;

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private AttentionUserMapper attentionUserMapper;

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/user/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/user/save.xml")
    public void testFindByUserId() {
        User user = userService.findByUserId("123");
        Assert.assertEquals("测试", user.getNickname());
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

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/comment/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/comment/save.xml")
    public void testFindComments() {
        Pageable pageable = new PageRequest(0, 10);
        Page<Comment> photos = userService.findComments("123", pageable);
        Assert.assertTrue(photos.getTotalElements() == 3);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/support/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/support/save.xml")
    public void testGetUserCommentSupport() {
        int supportNum = userService.getUserCommentSupport("123");
        Assert.assertTrue(supportNum == 3);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/fresh/save.xml")
    public void testPhotoSupport() {
        userService.photoSupport("1", "123", "Test", InteractType.SUPPORT);
        supportMapper.deleteAll();
        userService.photoSupport("1", "123", "Test", InteractType.NOT_SUPPORT);
        notSupportMapper.deleteAll();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/comment/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/comment/save.xml")
    public void testCommentSupport() {
        userService.commentSupport("1", "123", "Test", InteractType.SUPPORT);
        userService.commentSupport("2", "123", "Test", InteractType.NOT_SUPPORT);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save.xml")
    public void testComment() {
        CommentAddVO commentAddVO = new CommentAddVO();
        commentAddVO.setContent("测试");
        commentAddVO.setTrans(true);
        userService.comment("1", "123", "Test", "127.0.0.1", DisplayModel.MOON, commentAddVO);
        shareMapper.deleteAll();
        freshNewsMapper.deleteAll();
        commentMapper.deleteAll();
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/attention/save1.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/attention/save1.xml")
    public void testAttention() {
        userService.attention("311", "123");
        attentionUserMapper.deleteByMyUserId("311");
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/attention/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/attention/save.xml")
    public void testRemoveAttention() {
        userService.removeAttention("123", "345");
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/user/save1.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/user/save1.xml")
    public void testFindMyFriends() {
        List<User> user1 = userService.findMyFriends("123", null);
        Assert.assertEquals(3, user1.size());
        List<User> user2 = userService.findMyFriends("123", "123");
        Assert.assertEquals(0, user2.size());
        List<User> user3 = userService.findMyFriends("123", "124");
        Assert.assertEquals(1, user3.size());
        List<User> user4 = userService.findMyFriends("123", "12");
        Assert.assertEquals(3, user4.size());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/user/save1.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/user/save1.xml")
    public void testFindMyFans() {
        List<User> user1 = userService.findMyFans("124", null);
        Assert.assertEquals(1, user1.size());
        List<User> user2 = userService.findMyFans("124", "128");
        Assert.assertEquals(0, user2.size());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save1.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/fresh/save1.xml")
    public void testFindMyCommentPosts() {
        Pageable pageable = new PageRequest(0, 10);
        Page<FreshNews> posts = userService.findMyCommentPosts("123", pageable);
        Assert.assertEquals(2, posts.getContent().size());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/comment/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/comment/save.xml")
    public void testFindMyComments() {
        List<Comment> comments = userService.findMyComments("123", "123");
        Assert.assertEquals(1, comments.size());
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/support/save.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/support/save.xml")
    public void testCountMyCommentSupport() {
        int count = userService.countMyCommentSupport("123");
        Assert.assertTrue(count == 3);
    }

    @Test
    @DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/dataset/fresh/save2.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/fresh/save2.xml")
    public void testFindMySupportPosts() {
        Pageable pageable = new PageRequest(0, 10);
        Page<FreshNews> page = userService.findMySupportPosts("123", pageable);
        Assert.assertEquals(2, page.getContent().size());
    }

}
