package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();
        HttpSession session = ((HttpServletRequest) request).getSession();


        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        long likes_count = (long)em.createNamedQuery("getLikesCount", Long.class)
                .setParameter("report_id", r.getId())
                .getSingleResult();
        Employee e = (Employee) session.getAttribute("login_employee");

        long getReportId = (long)em.createNamedQuery("getReportId", Long.class)
                .setParameter("report_id", r.getId())
                .setParameter("employee_id", e.getId())
                .getSingleResult();

        em.close();
        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());

        request.setAttribute("likes_count", likes_count);
        request.getSession().setAttribute("getReportId", getReportId);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}
