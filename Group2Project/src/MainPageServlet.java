

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Servlet implementation class MainPageServlet
 */
//Everyone change your default webservlet name like /JohnSmith 
@WebServlet("/")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final String DB_URL = "jdbc:mysql://ec2-52-50-117-148.eu-west-1.compute.amazonaws.com/group2Project";
	protected static final String DB_USER = "root";
	protected static final String DB_PASSWORD = "1234";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // Notes to the Group
    // Add your link and update your page direction appropriately
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>"
					+ "<head> <title> Group 2 Homework Page </title> </head>"
					+ "<body>"
					+ "<table border=\"1\" style=\"width:100%\">"
						+ "<tr>"
							+ "<td> <a href=\"YigitOzgumus\"> Yigit Ozgumus </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yi%C4%9Fit-%C3%96zg%C3%BCm%C3%BC%C5%9F\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"YigitOzgumus\"> Gozde Berk </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"EnesOzipek\"> Enes Ozipek </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"KaganSari\"> Kagan Sari </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"YigitOzgumus\"> Erkam Uyanik </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"YigitOzgumus\"> Arda Yoruk </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
						+ "<tr>"
							+ "<td> <a href=\"YigitOzgumus\"> Osman Aka </a> </td>"
							+ "<td> <a href=\"https://github.com/bounswe/bounswe2016group2/wiki/Yiğit-Özgümüş\"> Git Profile Page </a> </td>"
						+ "</tr>"
					+ "</body>"
					+ "</html>");
		
		

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
