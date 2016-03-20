package ihm;

import bizz.BizzFactory;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.ProgramDto;
import dto.UserDto;
import ucc.CountryUcController;
import ucc.DepartmentUcController;
import ucc.MobilityUcController;
import ucc.ProgramUcController;
import ucc.UserUcController;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.reflect.VisibilityFilter;

import org.eclipse.jetty.http.HttpStatus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static final String SECRET =
      "LICORNEkjcajn edea zfalzenf  faezfbalzbflf5f5eaz45 546 a4f5 af46 aezPONEY";

  private static final String CONTENT_TYPE = "text/html";
  private static final String HTML_BODY_PAGES_PATH = "www/assets/pages/";

  private static final String KEY_USERNAME = "username";
  private static final String KEY_PERMISSIONS = "permissions";
  private static final String KEY_ID = "id";

  private transient UserUcController userUcc = null;
  private transient MobilityUcController mobilityUcc = null;
  private transient CountryUcController countryUcc = null;
  private transient DepartmentUcController departmentUcController = null;
  private transient ProgramUcController programUcController = null;
  private transient BizzFactory bizzFactory = null;

  private transient Genson userGenson = new GensonBuilder()
      .useFields(true, VisibilityFilter.PRIVATE).useMethods(false).exclude("password").create();
  private transient Genson defaultGenson =
      new GensonBuilder().useFields(true, VisibilityFilter.PRIVATE).useMethods(false).create();

  /**
   * The servlet used by the server.
   * 
   * @param userUcc The use case controller for the user
   * @param bizzFactory The factory used to generate dto.
   * @param countryUcc The use case controller for the user.
   */
  public Servlet(UserUcController userUcc, BizzFactory bizzFactory,
      MobilityUcController mobilityUcc, CountryUcController countryUcc,
      DepartmentUcController departmentUcController, ProgramUcController programUcController) {
    this.userUcc = userUcc;
    this.bizzFactory = bizzFactory;
    this.mobilityUcc = mobilityUcc;
    this.countryUcc = countryUcc;
    this.departmentUcController = departmentUcController;
    this.programUcController = programUcController;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType(CONTENT_TYPE);
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();
    out.println(htmlToString("www/assets/header.html"));
    out.println(htmlToString("www/assets/logo.html"));
    out.println(htmlToString("www/assets/navBarStudent.html"));
    out.println(htmlToString("www/assets/navBarTeacher.html"));
    File folder = new File(HTML_BODY_PAGES_PATH);
    File[] listOfFiles = folder.listFiles();
    if (null != listOfFiles) {
      for (File file : listOfFiles) {
        if (file.isFile()) {
          out.println(htmlToString(HTML_BODY_PAGES_PATH + file.getName()));
        }
      }
    }
    out.println(htmlToString("www/assets/footer.html"));

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    try {
      String action = req.getParameter("action");
      HttpSession session = req.getSession();

      switch (action) {

        case "login":
          String username = req.getParameter("username");
          String password = req.getParameter("password");

          UserDto userDtoRecept = userUcc.login(username, password);

          if (userDtoRecept == null) {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
          } else {
            session.setAttribute("username", userDtoRecept.getPseudo());
            session.setAttribute("permissions", userDtoRecept.getPermissions());
            createJwtCookie(resp, userDtoRecept);
            resp.setStatus(HttpStatus.ACCEPTED_202);
            resp.getWriter().println(dtoToJson(userDtoRecept));

          }
          break;

        case "authenticate":
          resp.setStatus(HttpStatus.ACCEPTED_202);
          UserDto userDto = bizzFactory.getUserDto();
          if (null != session.getAttribute(KEY_USERNAME)) {
            userDto.setPermissions("" + session.getAttribute(KEY_ID));
            userDto.setPermissions("" + session.getAttribute(KEY_PERMISSIONS));
            userDto.setPseudo("" + session.getAttribute(KEY_USERNAME));
            createJwtCookie(resp, userDto);
            resp.getWriter().println(dtoToJson(userDto));

          } else {
            if (readJwtCookie(req)) {
              userDto.setPermissions("" + session.getAttribute(KEY_ID));
              userDto.setPermissions("" + session.getAttribute(KEY_PERMISSIONS));
              userDto.setPseudo("" + session.getAttribute(KEY_USERNAME));
              resp.getWriter().println(dtoToJson(userDto));
            } else {
              resp.setStatus(HttpStatus.FORBIDDEN_403);
            }
          }
          break;

        case "register":
          UserDto userdto = bizzFactory.getUserDto();

          userdto.setPseudo(req.getParameter("username"));
          userdto.setPassword(req.getParameter("password"));
          userdto.setName(req.getParameter("name"));
          userdto.setFirstname(req.getParameter("firstname"));
          userdto.setEmail(req.getParameter("email"));

          userDtoRecept = userUcc.register(userdto);
          if (userDtoRecept == null) {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
          } else {
            session.setAttribute(KEY_ID, userDtoRecept.getId());
            session.setAttribute(KEY_USERNAME, userDtoRecept.getPseudo());
            session.setAttribute(KEY_PERMISSIONS, userDtoRecept.getPermissions());
            createJwtCookie(resp, userDtoRecept);
            resp.setStatus(HttpStatus.ACCEPTED_202);
            resp.getWriter().println(dtoToJson(userDtoRecept));
          }

          break;

        case "disconnect":

          break;

        case "editProfile":

          break;

        case "selectCountries":
          ArrayList<CountryDto> countries = countryUcc.getAllCountries();
          String jsonCountries = defaultGenson.serialize(countries);
          resp.getWriter().println(jsonCountries);
          resp.setStatus(HttpStatus.ACCEPTED_202);
          break;

        case "selectConfirmedMobility":
          ArrayList<MobilityDto> mobilities = mobilityUcc.getconfirmedMobilities();
          String jsonMobilities = defaultGenson.serialize(mobilities);
          resp.getWriter().println(jsonMobilities);
          resp.setStatus(HttpStatus.ACCEPTED_202);
          break;

        case "selectMyMobility":

          break;

        case "selectDepartments":
          ArrayList<DepartmentDto> departments = departmentUcController.getAllDepartments();
          String jsonDepartments = defaultGenson.serialize(departments);
          resp.getWriter().println(jsonDepartments);
          resp.setStatus(HttpStatus.ACCEPTED_202);
          break;
        case "selectPrograms":
          ArrayList<ProgramDto> programs = programUcController.getAllPrograms();
          String jsonPrograms = defaultGenson.serialize(programs);
          resp.getWriter().println(jsonPrograms);
          resp.setStatus(HttpStatus.ACCEPTED_202);
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

  /**
   * Lit le cookie JWT afin de verifier si l'utilisateur est authentifie.
   *
   * @param req La requete envoyee par la page
   * @return true si l'utilisateur etait authentifie via JWT. false si il n'était pas authentifie.
   */
  private boolean readJwtCookie(HttpServletRequest req) {
    Map<String, Object> decodedPayload;
    try {
      Cookie[] cookies = req.getCookies();
      String token = null;
      for (Cookie c : cookies) {
        if ("user".equals(c.getName())) {
          token = c.getValue();
        }
      }
      decodedPayload = new JWTVerifier(SECRET).verify(token);
    } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException
        | SignatureException | IOException | JWTVerifyException | NullPointerException exc) {
      return false;
    }
    req.getSession().setAttribute(KEY_ID, decodedPayload.get(KEY_ID));
    req.getSession().setAttribute(KEY_USERNAME, decodedPayload.get(KEY_USERNAME));
    req.getSession().setAttribute(KEY_PERMISSIONS, decodedPayload.get(KEY_PERMISSIONS));
    return true;
  }

  /**
   * Cree un cookie avec un token JWT afin de ne pas perdre l'authentification d'un utilisateur.
   *
   * @param resp La reponse qui serra renvoyee par le serveur.
   * @param login Le pseudo de l'utilisateur.
   */
  private void createJwtCookie(HttpServletResponse resp, UserDto userDto) {

    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put(KEY_USERNAME, userDto.getPseudo());
    claims.put(KEY_PERMISSIONS, userDto.getPermissions());
    claims.put(KEY_ID, userDto.getId());

    String token = new JWTSigner(SECRET).sign(claims);

    Cookie cookie = new Cookie("user", token);
    cookie.setPath("/");
    cookie.setMaxAge(60 * 60 * 24 * 365);
    resp.addCookie(cookie);
  }

  private String dtoToJson(UserDto dto) {
    return userGenson.serialize(dto);
  }

  /**
   * Extract html code from html file in an string.
   *
   * @param file path of html file.
   * @return String.
   */
  private String htmlToString(String file) {
    StringBuilder contentBuilder = new StringBuilder();
    try {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));
      String str;
      while ((str = in.readLine()) != null) {
        contentBuilder.append(str);
      }
      in.close();
    } catch (IOException exc) {
      exc.printStackTrace();
    }
    return contentBuilder.toString();
  }

}
