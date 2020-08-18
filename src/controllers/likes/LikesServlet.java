package controllers.likes;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Like;
import utils.DBUtil;

/**
 * Servlet implementation class Favorite
 */
@WebServlet("/likes")
public class LikesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        HttpSession session = ((HttpServletRequest) request).getSession();

        Employee e = (Employee) session.getAttribute("login_employee");

        Like l = new Like();


        l.setEmployee_id((Integer) e.getId());
        l.setReport_id(Integer.parseInt(request.getParameter("report_id")));

        ArrayList<Integer> likeList = new ArrayList<Integer>();
        likeList.add(l.getEmployee_id());



        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        em.close();

        response.sendRedirect(request.getContextPath() + "/reports/index");


    }

}
