package ihm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.http.HttpStatus;

import com.auth0.jwt.JWTSigner;

import bizz.BizzFactory;
import dto.UserDto;
import ucc.UserUcController;

public class Servlet extends HttpServlet {

  private UserUcController userUcc = null;
  private BizzFactory bizzFactory = null;

  private UserDto userDtoRecept = null;

  public Servlet(UserUcController userUcc, BizzFactory bizzFactory) {
    this.userUcc = userUcc;
    this.bizzFactory = bizzFactory;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String action = req.getParameter("action");
      HttpSession session = req.getSession();

      switch (action) {

        case "login":
          String username = req.getParameter("username");
          String password = req.getParameter("password");
          /*
           * UserDto userDtoSend = bizzFactory.getUserDto(); userDtoSend.setMdp(mdp);
           * userDtoSend.setPseudo(pseudo);
           */
          userDtoRecept = userUcc.login(username, password);

          if (userDtoRecept == null) {
            session.setAttribute("username", username);
            createJwtCookie(resp, username);
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

  private void createJwtCookie(HttpServletResponse resp, String login) {

    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("username", login);
    claims.put("id", "1");

    String token =
        new JWTSigner("kjcajn edea zfalzenf  faezfbalzbflf5f5eaz45 546 a4f5 af46 aez").sign(claims);

    Cookie cookie = new Cookie("user", token);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 60 * 24 * 365);
    resp.addCookie(cookie);
  }

}
