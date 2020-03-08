package jygl.controller;

import jygl.common.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LayoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);//防止创建Session
        if (session == null) {
            resp.sendRedirect("/jygl/public/login.jsp");
            return;
        }
        session.removeAttribute("user");
        resp.sendRedirect("/jygl/public/login.jsp");
    }
}
