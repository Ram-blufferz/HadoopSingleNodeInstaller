package Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class reloadfind extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		PrintWriter out = response.getWriter();
		
		String username=request.getParameter("username");
	Connection conn = null;
	String connectionURL = "jdbc:mysql://localhost:3306/fakebook"; 
	
	ResultSet rs = null;
	
	Statement st = null;    
	try {	
	Class.forName("com.mysql.jdbc.Driver");
	conn= DriverManager.getConnection(connectionURL, "root", "ssn");
	st = conn.createStatement();


	
rs=st.executeQuery("select * from user where username<>'"+username+"' and username not in(select friendname from friend where username='"+username+"') and username not in(select friendname from pendingRequest where username='"+username+"')");
	String count="";
	while(rs.next()) {
	String friend=rs.getString("username");
	String image=rs.getString("imageLocation");
	count+=friend+"-"+image+"-";
	}
out.println(count);
}
catch(Exception ex)
{

}

}
}
