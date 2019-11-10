package com.choco.ithink.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        //userService = new UserService();
    }

    @After
    public void tearDown() throws Exception {
       // userService = null;
    }

    @Test
    public void getEmailPattern() {
        // 预计输入输出
        String expected = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}(\\.[a-zA-Z0-9]{1,}){1,}$";

        // 实际输出
        String actual = userService.getEmailPattern();

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void getPwdPattern() {
        // 预计输入输出
        String expected = "^[a-zA-Z0-9]{6,20}$";

        // 实际输出
        String actual = userService.getPwdPattern();

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void getNamePattern() {
        // 预计输入输出
        String expected = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,20}$";

        // 实际输出
        String actual = userService.getNamePattern();

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkMatching1() {
        // 预计输入输出
        String inputLoginKey = "example@qq.com";
        String inputPwd = "123456";
        Integer expected = 1;

        // 实际输出
        Integer actual = userService.checkMatching(inputLoginKey, inputPwd);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkMatching2() {
        // 预计输入输出
        String inputLoginKey = "example@qq.com";
        String inputPwd = "wrongPassword";
        Integer expected = 0;

        // 实际输出
        Integer actual = userService.checkMatching(inputLoginKey, inputPwd);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkMatching3() {
        // 预计输入输出
        String inputLoginKey = "wrongUser";
        String inputPwd = "wrongPassword";
        Integer expected = -1;

        // 实际输出
        Integer actual = userService.checkMatching(inputLoginKey, inputPwd);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString1() {
        // 预计输入输出
        String inputS = "@qq.com";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString2() {
        // 预计输入输出
        String inputS = "1225";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString3() {
        // 预计输入输出
        String inputS = "1225@";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString4() {
        // 预计输入输出
        String inputS = "1225qq.com";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString5() {
        // 预计输入输出
        String inputS = "1225@qq";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString6() {
        // 预计输入输出
        String inputS = "1225@qq.com.cn";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = true;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString7() {
        // 预计输入输出
        String inputS = "1228775831@qq.com";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = true;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString8() {
        // 预计输入输出
        String inputS = "peppet@qq.com";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = true;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString9() {
        // 预计输入输出
        String inputS = "peppet@qq.";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString10() {
        // 预计输入输出
        String inputS = "人@qq.com";
        String inputPattern = userService.getEmailPattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString11() {
        // 预计输入输出
        String inputS = "人";
        String inputPattern = userService.getNamePattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString12() {
        // 预计输入输出
        String inputS = "合法昵称";
        String inputPattern = userService.getNamePattern();
        Boolean expected = true;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString13() {
        // 预计输入输出
        String inputS = "一二三四五六七八九十十九八七六五四三二一零";
        String inputPattern = userService.getNamePattern();
        Boolean expected = false;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void checkString14() {
        // 预计输入输出
        String inputS = "一二三四五六七八九十十九八七六五四三二一";
        String inputPattern = userService.getNamePattern();
        Boolean expected = true;

        // 实际输出
        Boolean actual = userService.checkString(inputS, inputPattern);

        // 检验
        assertEquals(expected, actual);
    }

    @Test
    public void userRegister() {
    }

    @Test
    public void getFansById() {
    }

    @Test
    public void updateInfoById() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void list2JSON() {
    }

    @Test
    public void getCollectTopicById() {
    }

    @Test
    public void getCollectAchievementById() {
    }
}