/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.sessionBean;
import za.co.rhmsolutions.scrum.business.boundary.AppUserService;
import za.co.rhmsolutions.scrum.business.boundary.GroupsService;
import za.co.rhmsolutions.scrum.business.entity.AppUser;
import za.co.rhmsolutions.scrum.business.entity.groups;

/**
 *
 * @author Richard O'Brien
 */

@WebServlet(name = "AdminResource", urlPatterns = {"/AdminResource"})
@ServletSecurity(@HttpConstraint(rolesAllowed={"admin", "guest"}))
public class AdminResource extends HttpServlet {

    @EJB
    private AppUserService appUserService;
    
    @EJB
    private GroupsService GroupsService;
    
    @Inject
    sessionBean bean;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        bean = new sessionBean();
    
        String remoteUser = request.getRemoteUser();

        if (remoteUser != null)
        {
            System.out.println(remoteUser + " logged in successfully");
            
            HttpSession session = request.getSession();
            
            AppUser user = appUserService.getByUsername(remoteUser);
            String priv = GroupsService.getPrivilege(remoteUser);
            
            System.out.println("Privilege: " + priv);
            
            bean.setUsername(user.getUsername());
            bean.setLoggedIn(true);
            
            if (priv.equals("admin"))
            {
               bean.setAdmin(true); 
            }

            if(session.getAttribute("sessionBean") == null)
            {
                session.setAttribute("sessionBean", bean);
            }
        }
        response.sendRedirect("/Testing1/faces/index.xhtml");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
