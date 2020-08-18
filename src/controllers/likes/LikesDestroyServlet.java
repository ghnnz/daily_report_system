package controllers.likes;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Like;
import models.Report;
import utils.DBUtil;


/**
 * Servlet implementation class LikesDestroyServlet
 */
@WebServlet("/likes/destroy")
public class LikesDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();
        HttpSession session = ((HttpServletRequest) request).getSession();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));

        Employee e = (Employee) session.getAttribute("login_employee");

        List<Like> getLike = em.createNamedQuery("getLike", Like.class)
                .setParameter("report_id", r.getId())
                .setParameter("employee_id", e.getId())
                .getResultList();

        Like l = getLike.get(0);




        em.getTransaction().begin();
        em.remove(l);
        em.getTransaction().commit();
        em.close();



        response.sendRedirect(request.getContextPath() + "/reports/index");






    }

}
