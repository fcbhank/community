package com.fcb.community.controller;

import com.fcb.community.dto.AccessTokenDto;
import com.fcb.community.dto.GithubUserDto;
import com.fcb.community.model.User;
import com.fcb.community.provider.GithubProvider;
import com.fcb.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by hank on 19-5-4
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUserDto githubUserDto = githubProvider.getUser(accessToken);

        if (githubUserDto != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUserDto.getId()));
            user.setName(githubUserDto.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUserDto.getAvatarUrl());
            user.setBio(githubUserDto.getBio());

            userService.insertOrUpdate(user);
            // 手动把token写入cookie中
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            // 登录失败
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null)
            // 清除session
            request.getSession().removeAttribute("user");

        // 清除所有cookies
        /*Cookie[] cookies = request.getCookies();
        if (cookies.length != 0)
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        */

        // 清除名为token的cookie
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }
}