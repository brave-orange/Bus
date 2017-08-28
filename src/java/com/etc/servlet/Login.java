/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yongcheng
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=utf-8");   
          response.setCharacterEncoding("UTF-8"); 
           request.setCharacterEncoding("UTF-8"); 
           String pwd = "";
           pwd =request.getParameter("checkPwd");
           if(pwd.equals("iamreal"))
           {
               HttpSession session = request.getSession();
                session.setAttribute("admin", "1");
               response.sendRedirect("admin/admin.jsp");
           }
           else
           {
           try {
               PrintWriter out = response.getWriter();
               out.println("<a href=\"check.jsp\">验证码出错，点击这里返回前页！</a>");
               Thread.sleep(2000);
               out.close();
              // response.sendRedirect("check.jsp");
           } catch (InterruptedException ex) {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
           }
           }
           
           
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
