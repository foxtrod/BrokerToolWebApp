package trader.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import trader.*;

/**
 * Created by horbachevsky on 21.06.2016.
 */
@WebServlet(name = "PortfolioController", urlPatterns = {"/PortfolioController"})
public class Portfolio extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String customerId = request.getParameter("customerIdentity");
        BrokerModel model = BrokerModelImpl.getInstance();

        try {
            CustomerShare [] shares = model.getAllCustomerShares(customerId);
            Customer customer = model.getCustomer(customerId);
            request.setAttribute("shares", shares);
            request.setAttribute("customer", customer);
        } catch (BrokerException be) {
            request.setAttribute("message", be.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Portfolio.jsp");
        dispatcher.forward(request, response);

    }
}
