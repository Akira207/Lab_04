package devmaster.edu.vn.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import devmaster.edu.vn.beans.Product;
import devmaster.edu.vn.conn.ConnectionUtils;
import devmaster.edu.vn.utils.ProductUtils;

/**
 * Servlet implementation class ProductCreateServlet
 */
@WebServlet("/ProductCreate")
public class ProductCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/webapp/views/productCreate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String errorString = null;
		String code = (String) request.getParameter("code");
		String name = (String) request.getParameter("name");
		String priceStr = (String) request.getParameter("price");
		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
			errorString = e.getMessage();
		}
		Product product = new Product(code, name, price);
		String regex = "\\w+";
		if(code == null || !code.matches(regex)) {
			errorString = "Product Code invalid!";
		}
		if(errorString != null) {
			request.setAttribute("errorString", errorString);
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/webapp/views/productCreate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Connection conn = null;
		try {
			conn = ConnectionUtils.getMSSQLConnection();
			ProductUtils.insertProduct(conn, product);
			response.sendRedirect(request.getContextPath()+"/productList");
		} catch(Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/webapp/views/productCreate.jsp");
			dispatcher.forward(request, response);
		}
	}

}
