package trader.web;

import trader.*;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by horbachevsky on 16.06.2016.
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController", "/AllCustomers"})
public class CustomerController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BrokerModel brokerModel = BrokerModelImpl.getInstance();
//        HttpSession session = request.getSession(true);
        String path = request.getServletPath();

        if (path.equals("/CustomerController")) {
            String id = request.getParameter("customerIdentity");
            String name = request.getParameter("customerName");
            String address = request.getParameter("customerAddress");
            String submit = request.getParameter("submit");

            try {
                if (submit.equals("Get Customer")) {
                    Customer customer = brokerModel.getCustomer(id);
                    request.setAttribute("customer", customer);
                }
                if (submit.equals("Update Customer")) {
                    brokerModel.updateCustomer(new Customer(id, name, address));
                    Customer customer = brokerModel.getCustomer(id);
                    request.setAttribute("customer", customer);
                }
                if (submit.equals("Add Customer")) {
                    brokerModel.addCustomer(new Customer(id, name, address));
                    Customer customer = brokerModel.getCustomer(id);
                    request.setAttribute("customer", customer);
                }
                if (submit.equals("Delete Customer")) {
                    brokerModel.deleteCustomer(new Customer(id, name, address));
                }

            } catch (BrokerException e) {
                request.setAttribute("message", e.getMessage());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerDetails");
            dispatcher.forward(request, response);
        }

        if (path.equals("/AllCustomers")) {
            try {

                Customer[] customers = brokerModel.getAllCustomers();
                request.setAttribute("customers", customers);

            } catch (BrokerException e) {
                request.setAttribute("message", e.getMessage());
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("AllCustomers.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

