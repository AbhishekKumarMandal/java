import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		try
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abhi@123");
			Statement smt=con.createStatement();
			String query="select * from account where email='"+email+"' and password='"+password+"';";
			ResultSet rs=smt.executeQuery(query);
			boolean b=rs.next();
			if(!b)
			{
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<html>"
						+"<head><title>ERROR</title><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"></head>"
						+"<body><p><b>Invalid Credentials</b>  <a href=\"employee_login.html\">Try Again</a></p></body></html>");
				out.close();
			}
			else
			{
				String name=rs.getString(1);
				String dept=rs.getString(2);
				String desig=rs.getString(3);
//				request.setAttribute("name",name);
//				request.setAttribute("dept",dept);
//				request.setAttribute("desig",desig);
//				request.setAttribute("email",email);
				if(desig.equalsIgnoreCase("employee"))
				{
					HttpSession session=request.getSession();
					session.setAttribute("username",email);
					//RequestDispatcher rd = request.getRequestDispatcher("leave.jsp");
					//rd.forward(request,response);
					//response.sendRedirect("urNextUrl.jsp?fisrtVar="+var1+"&secondVar="+var2);
					response.sendRedirect(("leave.jsp?name="+name+"&dept="+dept+"&desig="+desig+"&email="+email));
					//request.getRequestDispatcher("leave.jsp").forward(request,response);
				}
				else if(desig.equalsIgnoreCase("manager"))
				{
					HttpSession session=request.getSession();
					session.setAttribute("username",email);
					//response.sendRedirect("manager.jsp");
					response.sendRedirect("manager.jsp?name="+name+"&dept="+dept+"&desig="+desig+"&email="+email);
					//request.getRequestDispatcher("manager.jsp").forward(request,response);
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

}
