import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class leavedata
 */
@WebServlet("/leavedata")
public class leavedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public leavedata() {
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
		int days;
		String email,name,dept,desig,from ,to, leave, text;
		email=request.getParameter("email");
		name=request.getParameter("name");
		dept=request.getParameter("dept");
		if(email=="")
			System.out.println("email empty");
		if(name=="")
			System.out.println("name empty");
		if(dept=="")
			System.out.println("dept empty");
		desig=request.getParameter("desig");
		days=Integer.parseInt(request.getParameter("days"));
		from =request.getParameter("frm");
		to=request.getParameter("too");
		leave=request.getParameter("leave");
		text=request.getParameter("text");
		
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
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABASE-NAME","USERNAME","PASSWORD");
			Statement smt=con.createStatement();
			String query="insert into leaveapp (email,name,dept,days,frm,too,lve,text,status) values('"+email+"','"+name+"','"+dept+"',"+days+",'"+from+"','"+to+"','"+leave+"','"+text+"','processing');";
			int i=smt.executeUpdate(query);
			
			boolean b=(i==1);
			PrintWriter out=response.getWriter();
			if(b)
			{
				
//				out.println("<html>"
//						+"<head><title>Successful</title><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"></head>"
//						+"<body><div class=\"alert alert-success\" role=\"alert\">\r\n" + 
//						"  Submitted Successfully\r\n"+
//						"</div></body></html>");
//				request.setAttribute("name",name);
//				request.setAttribute("dept",dept);
//				request.setAttribute("desig",desig);
//				request.setAttribute("email",email);
//				request.getRequestDispatcher("leave.jsp").include(request,response);
				out.println("{DONE} Submitted Successfully");
				//request.getRequestDispatcher("status.jsp").include(request,response);
			}
			else
			{
//				PrintWriter out=response.getWriter();
//				out.println("<html>"
//						+"<head><title>Not Successful</title><link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\"></head>"
//						+"<body><div class=\"alert alert-danger\" role=\"alert\">\r\n" + 
//						"  Not Submitted Successfully\r\n" +"<a href=\"leave.jsp\">Try Again</a>"+ 
//						"</div></body></html>");
				out.println("{ERROR} Submission Unsuccessfull Try Again");
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}
