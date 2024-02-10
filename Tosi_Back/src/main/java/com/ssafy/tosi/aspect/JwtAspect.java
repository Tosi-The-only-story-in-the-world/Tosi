package com.ssafy.tosi.aspect;

import com.ssafy.tosi.cookieUtil.CookieUtil;
import com.ssafy.tosi.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class JwtAspect {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Before("execution(* com.ssafy.tosi.*..*Controller.*(..)) && !execution(* com.ssafy.tosi.user.UserController.postUser(..)) " +
            "&& !execution(* com.ssafy.tosi.user.UserController.post*Login(..)) " +
            "&& !execution(* com.ssafy.tosi.user.UserController.get*Logout(..)) " +
            "&& !execution(* com.ssafy.tosi.user.UserController.getEmailCheck(..)) " +
            "&& !execution(* com.ssafy.tosi.jwt.TokenController.postNewAccessToken(..)) && args(request, ..)")
    public void beforeControllerMethod(HttpServletRequest request) throws Exception {

        String accessToken = cookieUtil.getTokenFromCookie(request, "access-token");

        System.out.println(accessToken);
        System.out.println(request.getCookies());

        if (accessToken == null) {
            throw new Exception("토큰이 없습니다.");
        }

        boolean tokenValidity = jwtUtil.validateToken(accessToken);

        if (!tokenValidity) {
            throw new Exception("토큰이 유효하지 않습니다.");
        }

        Integer userId = jwtUtil.getUserId(accessToken);

        System.out.println("userId: " + userId);
        request.setAttribute("userId", userId);
    }
}
