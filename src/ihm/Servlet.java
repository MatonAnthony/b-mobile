package ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import bizz.BizzFactory;
import bizz.UserDto;
import bizz.UserUcController;

public class Servlet extends HttpServlet {

  private UserUcController userUcc = null;
  private UserDto userDtoRecept = null;
  private BizzFactory bizzFactory = null;

  public Servlet(UserUcController userUcc, BizzFactory bizzFactory) {
    this.userUcc = userUcc;
    this.bizzFactory = bizzFactory;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String action = req.getParameter("action");

      switch (action) {

        case "login":
          String pseudo = req.getParameter("userName");
          String mdp = req.getParameter("password");
          UserDto userDtoSend = bizzFactory.getUserDto();
          userDtoSend.setMdp(mdp);
          userDtoSend.setPseudo(pseudo);
          userDtoRecept = userUcc.login(userDtoSend);

          if (userDtoRecept == null) {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
          } else {
            resp.setStatus(HttpStatus.ACCEPTED_202);
          }
          break;

        default:
          resp.setStatus(HttpStatus.BAD_REQUEST_400);
      }

    } catch (Exception exc) {
      exc.printStackTrace();
      resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
      resp.getWriter().println(exc.getMessage());
      resp.flushBuffer();
    }

  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    super.doGet(req, resp);
  }


}
