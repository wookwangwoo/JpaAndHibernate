package com.mes.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

    HashMap<String,Controller> router = new HashMap<>();

    public FrontController() {
        super();
    }

    public void init() throws ServletException {
        router.put("/login.do",new LoginController());
    }

    public void destroy() {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = url.substring(contextPath.length());

        if (router.containsKey(path)) {
            Controller frontController = router.get(path);

            String method = req.getMethod();
            if (frontController.getMethod().equals(method)) {
                String viewFilePath = frontController.execute(req, resp);

                if (viewFilePath != null) {
                    req.getRequestDispatcher(viewFilePath).forward(req, resp);
                }
            } else {
                System.out.println("403error");
                req.getRequestDispatcher("/error/403.jsp").forward(req, resp);
            }
        } else {
            System.out.println("404error");
            req.getRequestDispatcher("/error/404.jsp").forward(req, resp);
        }
    }
}
