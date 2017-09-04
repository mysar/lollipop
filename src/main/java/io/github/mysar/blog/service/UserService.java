package io.github.mysar.blog.service;


import io.github.mysar.blog.modal.vo.User;
import io.github.mysar.blog.modal.vo.UserInfo;

/**
* Created by GeneratorFx on 2017-04-11.
*/
public interface UserService {


    User loadUserByUsername(String username);

    UserInfo getUserInfo();

    void updateAvatar(String url, String username);

    void updatePassword(User user);

    void updateUserInfo(UserInfo userInfo);

    User getCurrentUser();

}
