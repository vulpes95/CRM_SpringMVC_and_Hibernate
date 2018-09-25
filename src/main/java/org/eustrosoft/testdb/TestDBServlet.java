package org.eustrosoft.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestDBServlet() {
       
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = "springstudent";
		String pass = "springstudent";
		// здесь не нужно &amp
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		PrintWriter out = response.getWriter();
		try {
			out.println("Connecting to database: " + jdbcUrl);
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
			out.println("Success!");
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
