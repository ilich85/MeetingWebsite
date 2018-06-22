package com.ilich.service;

import com.ilich.model.Gender;
import com.ilich.model.SearchRequest;
import com.ilich.model.User;
import com.ilich.model.UserInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchServiceImplTest {

    private SearchService searchService;
    private SearchRequest searchRequest;

    /*
                age    |     city        |    gender
          -----------------------------------------------
                16     |     Киев        |    Woman
                17     |     Киев        |    Woman
                18     |     Киев        |    Woman
                19     |     Львов       |    Woman
                20     |     Львов       |    Woman
                21     |     Львов       |    Man
                22     |     Харьков     |    Man
                23     |     Харьков     |    Man
                24     |     Одесса      |    Man
                25     |     Одесса      |    Man
          -----------------------------------------------
    */
    static {
        UserService userService = new UserServiceImpl();
        UserInfoService userInfoService = new UserInfoServiceImpl();
        User user = new User();
        user.setUsername("user1");
        user.setPassword("password1");
        userService.register(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("user1");
        userInfo.setAge(16);
        userInfo.setCity("Киев");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user2");
        user.setPassword("password2");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user2");
        userInfo.setAge(17);
        userInfo.setCity("Киев");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user3");
        user.setPassword("password3");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user3");
        userInfo.setAge(18);
        userInfo.setCity("Киев");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user4");
        user.setPassword("password4");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user4");
        userInfo.setAge(19);
        userInfo.setCity("Львов");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user5");
        user.setPassword("password5");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user5");
        userInfo.setAge(20);
        userInfo.setCity("Львов");
        userInfo.setGender(Gender.WOMAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user6");
        user.setPassword("password6");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user6");
        userInfo.setAge(21);
        userInfo.setCity("Львов");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user7");
        user.setPassword("password7");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user7");
        userInfo.setAge(22);
        userInfo.setCity("Харьков");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user8");
        user.setPassword("password8");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user8");
        userInfo.setAge(23);
        userInfo.setCity("Харьков");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user9");
        user.setPassword("password9");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user9");
        userInfo.setAge(24);
        userInfo.setCity("Одесса");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);

        user = new User();
        user.setUsername("user10");
        user.setPassword("password10");
        userService.register(user);
        userInfo = new UserInfo();
        userInfo.setName("user10");
        userInfo.setAge(25);
        userInfo.setCity("Одесса");
        userInfo.setGender(Gender.MAN.getDescription());
        userInfo.setUserId(user.getId());
        userInfoService.setInfo(userInfo);
    }

    @Before
    public void setUp() throws Exception {
        searchService = new SearchServiceImpl();
    }

    @Test
    public void getUsersByGender() throws Exception {
        int expectResult = 5;
        int actualResult = searchService.getUsersByGender("Man").size();
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void getUsersByAllFields() throws Exception {
        searchRequest = new SearchRequest();
        searchRequest.setCity("Киев");
        searchRequest.setGender("Woman");
        searchRequest.setMinAge(16);
        searchRequest.setMaxAge(17);
        int expectResult = 2;
        int actualResult = searchService.getUsersByAllFields(searchRequest).size();
        assertEquals(expectResult, actualResult);
    }

    @Test
    public void getUsersByCity() throws Exception {
        int expectedResult = 3;
        int actualResult = searchService.getUsersByCity("Львов").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMinAge() throws Exception {
        int expectedResult = 7;
        int actualResult = searchService.getUsersByMinAge(19).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMinAgeAndCity() throws Exception {
        int expectedResult = 2;
        int actualResult = searchService.getUsersByMinAgeAndCity(20, "Львов").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMinAgeAndGender() throws Exception {
        int expectedResult = 3;
        int actualResult = searchService.getUsersByMinAgeAndGender(23, "Man").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMinAgeAndGenderAndCity() throws Exception {
        searchRequest = new SearchRequest();
        searchRequest.setMinAge(20);
        searchRequest.setGender("Woman");
        searchRequest.setCity("Львов");
        int expectedResult = 1;
        int actualResult = searchService.getUsersByMinAgeAndGenderAndCity(searchRequest).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMaxAge() throws Exception {
        int expectedResult = 5;
        int actualResult = searchService.getUsersByMaxAge(20).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMaxAgeAndCity() throws Exception {
        int expectedResult = 2;
        int actualResult = searchService.getUsersByMaxAgeAndCity(17, "Киев").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMaxAgeAndGender() throws Exception {
        int expectedResult = 3;
        int actualResult = searchService.getUsersByMaxAgeAndGender(23, "Man").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByMaxAgeAndGenderAndCity() throws Exception {
        searchRequest = new SearchRequest();
        searchRequest.setMaxAge(20);
        searchRequest.setGender("Woman");
        searchRequest.setCity("Львов");
        int expectedResult = 2;
        int actualResult = searchService.getUsersByMaxAgeAndGenderAndCity(searchRequest).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByAge() throws Exception {
        int expectedResult = 6;
        int actualResult = searchService.getUsersByAge(18, 23).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByCityAndGender() throws Exception {
        int expectedResult = 1;
        int actualResult = searchService.getUsersByCityAndGender("Львов", "Man").size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByAgeAndGender() throws Exception {
        searchRequest = new SearchRequest();
        searchRequest.setMinAge(18);
        searchRequest.setMaxAge(23);
        searchRequest.setGender("Woman");
        int expectedResult = 3;
        int actualResult = searchService.getUsersByAgeAndGender(searchRequest).size();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getUsersByAgeAndCity() throws Exception {
        searchRequest = new SearchRequest();
        searchRequest.setMinAge(20);
        searchRequest.setMaxAge(24);
        searchRequest.setCity("Харьков");
        int expectedResult = 2;
        int actualResult = searchService.getUsersByAgeAndCity(searchRequest).size();
        assertEquals(expectedResult, actualResult);
    }
}