package com.aiot.web.auth.filter;

import com.aiot.web.auth.SpringSecurityConfig;
import com.aiot.web.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Frank
 * @Date: 2021/2/26
 */

/**
 * JWT过滤器
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 验证Token
     * @param request 请求
     * @param response 回应
     * @param chain 过滤器链
     * @throws IOException 输入输出异常
     * @throws ServletException 服务异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = request.getRequestURI();
        String header = request.getHeader(JwtUtil.AUTHORIZATION);

        //跳过不需要验证的路径
        if(Arrays.asList(SpringSecurityConfig.AUTH_WHITELIST).contains(url)){
            chain.doFilter(request, response);
            return;
        }
        if (StringUtils.isBlank(header) || !header.startsWith(JwtUtil.TOKEN_PREFIX)) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token为空");
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        }catch (ExpiredJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token已过期");
            logger.error("Token已过期: {} " + e);
        } catch (UnsupportedJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token格式错误");
            logger.error("Token格式错误: {} " + e);
        } catch (MalformedJwtException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token没有被正确构造");
            logger.error("Token没有被正确构造: {} " + e);
        } catch (SignatureException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token签名失败");
            logger.error("签名失败: {} " + e);
        } catch (IllegalArgumentException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token非法参数异常");
            logger.error("非法参数异常: {} " + e);
        }catch (Exception e){
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid Token");
            logger.error("Invalid Token " + e.getMessage());
        }
    }

    /**
     * 获得鉴权Token
     * @param request 请求
     * @param response 回应
     * @return 鉴权Token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response)  {
        String token = request.getHeader(JwtUtil.AUTHORIZATION);
        if (token != null) {
            String userName="";

            try {
                // 解密Token
                userName = JwtUtil.getUsername(token);
                if (StringUtils.isNotBlank(userName)) {
                    return new UsernamePasswordAuthenticationToken(userName, null, getAuthorities(JwtUtil.getRole(token)));
                }
            } catch (Exception e) {
                throw e;
            }

            return null;
        }
        return null;
    }

    /**
     * 获取权限列表
     * @param role 角色
     * @return 权限列表
     */
    private Collection<GrantedAuthority> getAuthorities(String role){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }

}
