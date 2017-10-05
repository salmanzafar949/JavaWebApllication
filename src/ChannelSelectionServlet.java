import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChannelSelectionServlet")
public class ChannelSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChannelSelectionServlet() {
        super();
    }
    public enum Command{Select, Confirm}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String[] channels = null;
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		String action = request.getParameter("action");
		Command command = Command.valueOf(action);
		switch(command)
		{
		case Select:
			channels = request.getParameterValues("Channels");
			// setting values in sessions
			if(session!=null)
			{
				session.setAttribute("Channels", channels);
			}
			printWriter.println("<html>");
			printWriter.println("<head><title>Confirm slection</title></head>");
			printWriter.println("<body>");
			printWriter.println("<body>");
			printWriter.println("<b> Confirm Selection </b><br>");
			for(int x=0; x<channels.length; x++) printWriter.println(channels[x]+"<br>");
			printWriter.println("<form method=\"GET\" action=\"/HttpSessionStateServletProject/ChannelSelectionServlet\">");
			printWriter.println("<input type=\"Submit\" name=\"action\" value=\"Confirm\">");
			printWriter.println("</form>");
			printWriter.println("</body>");
			printWriter.println("</html>");
			printWriter.close();
			
			break;
		case Confirm:
			if(session!=null)
			{
				channels = (String[]) session.getAttribute("Channels");
			}
			printWriter.println("<html>");
			printWriter.println("<head><title>selection Summary</title></head>");
			printWriter.println("<body>");
			printWriter.println("<b>Selection Summary </b><br>");
			for(int x=0; x<channels.length; x++) printWriter.println(channels[x]+"<br>");
			printWriter.println("</body>");
			printWriter.println("</html>");
			printWriter.close();
			break;
		}
		
	}

}
