package trillionaire.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by michaeltan on 2017/5/14.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("前置拦截器" + "      " + request.getHeader("x-requested-with"));
        String username = (String) session.getAttribute("username");
        System.out.println(username + " " + request.getContextPath());
        //用户掉线或被挤掉，保存当前链接并重定向到登录页面
        if (request.getHeader("x-requested-with") == null) {//非ajax(异步)请求，则保存当前访问链接
            String queryUrl = request.getQueryString() == null ? "" : ("?" + request.getQueryString());//获取参数
            String requestUrl = request.getServletPath() + queryUrl;//httpRequest.getServletPath(),获取链接
            if (session.getAttribute("redirect_link") == null) {
                session.setAttribute("redirect_link", requestUrl);
            }
            if (username == null || username == "") {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return false;
            }
        } else if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {//如果是ajax类型，响应logout给前台
            response.setHeader("sessionstatus", "logout");
            return false;
        }

        /*

        //多用户登录限制判断,并给出提示信息
        boolean isLogin = false;
        if (user != null) {
            Map<String, String> loginUserMap = (Map<String, String>) session.getServletContext().getAttribute("loginUserMap");
            String sessionId = session.getId();
            for (String key : loginUserMap.keySet()) {
                //用户已在另一处登录
                if (key.equals(user.getUserName()) && !loginUserMap.containsValue(sessionId)) {
                    isLogin = true;
                    break;
                }
            }
        }
        if (isLogin) {
            Map<String, String> loginOutTime = (Map<String, String>) session.getServletContext().getAttribute("loginOutTime");
            session.setAttribute("mess", "用户：" + user.getUserName() + "，于 " + loginOutTime.get(user.getUserName()) + " 已在别处登录!");
            loginOutTime.remove(user.getUserName());
            session.getServletContext().setAttribute("loginUserMap", loginOutTime);
            response.sendRedirect(request.getContextPath() + "/other/toLogin");
            return false;
        }

*/
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
