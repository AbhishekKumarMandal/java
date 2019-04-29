import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class result
 */
@WebServlet("/result")
public class result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String name,dept,desig,email;
		name=request.getParameter("name");
		dept=request.getParameter("dept");
		desig=request.getParameter("desig");
		email=request.getParameter("email");
		int i=Integer.parseInt(request.getParameter("sno"));
		String btn=request.getParameter("btn");
		//System.out.println(name+" "+dept+" "+desig+" "+email+" "+i+" "+btn);
		try
		{
			try{
				Class.forName("com.mysql.jdbc.Driver");
				}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","abhi@123");
			Statement smt=con.createStatement();
			String query="update leaveapp set status='"+btn+"' where id="+i+";";
			int j=smt.executeUpdate(query);
			boolean b=(j==1);
			response.setContentType("text/html");
			//PrintWriter out=response.getWriter();
			request.setAttribute("name",name);
			request.setAttribute("dept",dept);
			request.setAttribute("desig",desig);
			request.setAttribute("email",email);
			if(b)
				request.getRequestDispatcher("manager.jsp").forward(request,response);
			else
				request.getRequestDispatcher("manager.jsp").forward(request,response);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}