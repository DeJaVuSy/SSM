package com.zjp.generatemysql.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zjp.generatemysql.service.ISysUserService;
import com.zjp.generatemysql.util.JwtUtil;
import com.zjp.generatemysql.util.PassToken;
import com.zjp.generatemysql.util.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * 200 - 服务器成功返回网页
 * 404 - 请求的网页不存在
 * 503 - 服务不可用
 * 详细分解：
 *
 * 1xx（临时响应）
 * 表示临时响应并需要请求者继续执行操作的状态代码。
 *
 * 代码 说明
 * 100 （继续） 请求者应当继续提出请求。服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。
 * 101 （切换协议） 请求者已要求服务器切换协议，服务器已确认并准备切换。
 *
 * 2xx （成功）
 * 表示成功处理了请求的状态代码。
 *
 * 代码 说明
 * 200 （成功） 服务器已成功处理了请求。通常，这表示服务器提供了请求的网页。
 * 201 （已创建） 请求成功并且服务器创建了新的资源。
 * 202 （已接受） 服务器已接受请求，但尚未处理。
 * 203 （非授权信息） 服务器已成功处理了请求，但返回的信息可能来自另一来源。
 * 204 （无内容） 服务器成功处理了请求，但没有返回任何内容。
 * 205 （重置内容） 服务器成功处理了请求，但没有返回任何内容。
 * 206 （部分内容） 服务器成功处理了部分 GET 请求。
 *
 * 3xx （重定向）
 * 表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
 *
 * 代码 说明
 * 300 （多种选择） 针对请求，服务器可执行多种操作。服务器可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择。
 * 301 （永久移动） 请求的网页已永久移动到新位置。服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。
 * 302 （临时移动） 服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
 * 303 （查看其他位置） 请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。
 * 304 （未修改） 自从上次请求后，请求的网页未修改过。服务器返回此响应时，不会返回网页内容。
 * 305 （使用代理） 请求者只能使用代理访问请求的网页。如果服务器返回此响应，还表示请求者应使用代理。
 * 307 （临时重定向） 服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
 *
 * 4xx（请求错误）
 * 这些状态代码表示请求可能出错，妨碍了服务器的处理。
 *
 * 代码 说明
 * 400 （错误请求） 服务器不理解请求的语法。
 * 401 （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
 * 403 （禁止） 服务器拒绝请求。
 * 404 （未找到） 服务器找不到请求的网页。
 * 405 （方法禁用） 禁用请求中指定的方法。
 * 406 （不接受） 无法使用请求的内容特性响应请求的网页。
 * 407 （需要代理授权） 此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。
 * 408 （请求超时） 服务器等候请求时发生超时。
 * 409 （冲突） 服务器在完成请求时发生冲突。服务器必须在响应中包含有关冲突的信息。
 * 410 （已删除） 如果请求的资源已永久删除，服务器就会返回此响应。
 * 411 （需要有效长度） 服务器不接受不含有效内容长度标头字段的请求。
 * 412 （未满足前提条件） 服务器未满足请求者在请求中设置的其中一个前提条件。
 * 413 （请求实体过大） 服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。
 * 414 （请求的 URI 过长） 请求的 URI（通常为网址）过长，服务器无法处理。
 * 415 （不支持的媒体类型） 请求的格式不受请求页面的支持。
 * 416 （请求范围不符合要求） 如果页面无法提供请求的范围，则服务器会返回此状态代码。
 * 417 （未满足期望值） 服务器未满足”期望”请求标头字段的要求。
 *
 * 5xx（服务器错误）
 * 这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
 *
 * 代码 说明
 * 500 （服务器内部错误） 服务器遇到错误，无法完成请求。
 * 501 （尚未实施） 服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码。
 * 502 （错误网关） 服务器作为网关或代理，从上游服务器收到无效响应。
 * 503 （服务不可用） 服务器目前无法使用（由于超载或停机维护）。通常，这只是暂时状态。
 * 504 （网关超时） 服务器作为网关或代理，但是没有及时从上游服务器收到请求。
 * 505 （HTTP 版本不受支持） 服务器不支持请求中所用的 HTTP 协议版本。
 *
 * HttpWatch状态码Result is
 *
 * 200 - 服务器成功返回网页，客户端请求已成功。
 * 302 - 对象临时移动。服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
 * 304 - 属于重定向。自上次请求后，请求的网页未修改过。服务器返回此响应时，不会返回网页内容。
 * 401 - 未授权。请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
 * 404 - 未找到。服务器找不到请求的网页。
 * 2xx - 成功。表示服务器成功地接受了客户端请求。
 * 3xx - 重定向。表示要完成请求，需要进一步操作。客户端浏览器必须采取更多操作来实现请求。例如，浏览器可能不得不请求服务器上的不同的页面，或通过代理服务器重复该请求。
 * 4xx - 请求错误。这些状态代码表示请求可能出错，妨碍了服务器的处理。
 * 5xx - 服务器错误。表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
 */

/**
 * @author zjp
 * @ClassName: AuthenticationInterceptor
 * @Description: TODO
 * @date 2021\1\13 0027
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        //System.out.println("object:"+object);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    System.out.println("无token，请重新登录");
                    //throw new RuntimeException("无token，请重新登录");
                    httpServletResponse.setStatus(401);
                    return false;
                }
                // 获取 token 中的 user name
                String userName = JwtUtil.getUserNameByToken(token);
                String userPwd = sysUserService.getPassword(userName);
//                String userName;
//                String reflushMillis;
                try {
                    //判断是否有切换登录，未切换则无需从数据库查询
                    //userName = JWT.decode(token).getAudience().get(0);
                    //reflushMillis = JWT.decode(token).getAudience().get(1);
//                    if (userLoginName == null || !userLoginName.equals(userName)){
//                        System.out.println(userLoginName);
//                        System.out.println(userName);
//                        userLoginName = userName;
//                        userPwd = sysUserService.getPassword(userName);
//                    }
                    //reflushMillis = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    httpServletResponse.setStatus(401);
                    return false;
                }
//                String current = String.valueOf(System.currentTimeMillis());
//                if(current.compareTo(reflushMillis)>0){
//                    throw new RuntimeException("用户登录凭证超时，请重新登录");
//                }
                if (userPwd == null) {
                    System.out.println("用户不存在，请重新登录");
                    httpServletResponse.setStatus(401);
                    //throw new RuntimeException("用户不存在，请重新登录");
                    return false;
                }
                // 验证 token
                boolean result = JwtUtil.verify(token,userName,userPwd);
                if(!result){
                    System.out.println("认证错误");
//                    JSONObject json = new JSONObject();
//                    json.put("msg","认证错误");
//                    json.put("code","401");
//                    httpServletResponse.getWriter().append(json.toString());
                    //httpServletResponse.sendError(401);
                    //响应401，
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    //throw new RuntimeException("401");
                    return false;
                }
                return true;
            }
        }
//                try {
////                    jwtVerifier.verify(token);
////                    httpServletRequest.setAttribute("user", user);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
//                }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
