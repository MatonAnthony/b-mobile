package ihm;

import bizz.interfaces.BizzFactory;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;
import exceptions.AuthenticationException;
import exceptions.NoDepartmentException;
import ucc.interfaces.CancelationUcController;
import ucc.interfaces.CountryUcController;
import ucc.interfaces.DepartmentUcController;
import ucc.interfaces.MobilityUcController;
import ucc.interfaces.PartnerUcController;
import ucc.interfaces.ProgramUcController;
import ucc.interfaces.UserUcController;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  private transient DepartmentUcController departmentUcc = null;
  private transient ProgramUcController programUcc = null;
  private transient PartnerUcController partnerUcc = null;
  @SuppressWarnings("unused")
  private transient CancelationUcController cancelationUcc = null;
  private transient BizzFactory bizzFactory = null;

  private transient Genson userGenson = new GensonBuilder()
      .useFields(true, VisibilityFilter.PRIVATE).useMethods(false).exclude("password").create();
  // private transient Genson defaultGenson =
  // new GensonBuilder().useFields(true, VisibilityFilter.PRIVATE).useMethods(false).create();
  private transient Genson basicGenson = new GensonBuilder().create();

  /**
   * The servlet used by the server.
   *
   * @param userUcc The use case controller for the user
   * @param bizzFactory The factory used to generate dto.
   * @param countryUcc The use case controller for the user.
   */
  public Servlet(UserUcController userUcc, BizzFactory bizzFactory,
      MobilityUcController mobilityUcc, CountryUcController countryUcc,
      DepartmentUcController departmentUcController, ProgramUcController programUcController,
      PartnerUcController partnerUcController, CancelationUcController cancelationUcController) {
    this.userUcc = userUcc;
    this.bizzFactory = bizzFactory;
    this.mobilityUcc = mobilityUcc;
    this.countryUcc = countryUcc;
    this.departmentUcc = departmentUcController;
    this.programUcc = programUcController;
    this.partnerUcc = partnerUcController;
    this.cancelationUcc = cancelationUcController;
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
      switch (action) {
        case "login":
          login(req, resp);
          break;
        case "authenticate":
          authenticate(req, resp);
          break;
        case "register":
          register(req, resp);
          break;
        case "disconnect":
          disconnect(req, resp);
          break;
        case "selectProfile":
          selectProfile(req, resp);
          break;
        case "updateUser":
          updateUser(req, resp);
          break;
        case "selectAllMobility":
          selectAllMobility(req, resp);
          break;
        case "selectConfirmedMobility":
          selectConfirmedMobility(req, resp);
          break;
        case "selectMyMobility":
          selectMyMobility(req, resp);
          break;
        case "selectCountries":
          selectCountries(req, resp);
          break;
        case "selectDepartments":
          selectDepartments(req, resp);
          break;
        case "selectPrograms":
          selectPrograms(req, resp);
          break;
        case "selectUsers":
          selectUsers(req, resp);
          break;
        case "addMobility":
          addMobility(req, resp);
          break;
        case "addPartner":
          addPartner(req, resp);
          break;
        case "changePermissions":
          changePermissions(req, resp);
          break;
        case "academicYears":
          loadAcademicYears(req, resp);
          break;
        case "selectPayments":
          loadPayments(req, resp);
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


  private void selectProfile(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    UserDto userSelected =
        userUcc.getUserById(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID)));
    String json = userGenson.serialize(userSelected);
    resp.getWriter().println(json);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  /**
   * The method used by the servlet to update a user into the DB.
   *
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   */
  private void updateUser(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    UserDto userEdited = userUcc.getUserById((Integer) req.getSession().getAttribute(KEY_ID));
    userEdited.setId(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID)));
    userEdited.setName(req.getParameter("name"));
    userEdited.setFirstname(req.getParameter("firstname"));
    userEdited.setGender(req.getParameter("gender"));
    // ici il faut ajouté la date de naissance et apres modifier le query
    userEdited.setCitizenship(req.getParameter("citizenship"));
    userEdited.setStreet(req.getParameter("street"));
    userEdited.setHouseNumber(req.getParameter("houseNumber"));
    userEdited.setMailBox(req.getParameter("mailbox"));
    userEdited.setZip(req.getParameter("zipcode"));
    userEdited.setCity(req.getParameter("city"));
    try {
      // userEdited.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    } catch (Exception exc) {
      createToaster(exc, resp);
    }
    userEdited.setTel(req.getParameter("tel"));
    userEdited.setSuccessfullYearInCollege(
        Integer.parseInt(0 + req.getParameter("successfullYearsInCollege")));
    // userEdited.setIban(req.getParameter("iban"));
    userEdited.setAccountHolder(req.getParameter("accountHolder"));
    userEdited.setBankName(req.getParameter("bankName"));
    userEdited.setBic(req.getParameter("bic"));

    userUcc.updateUser(userEdited);
  }

  private void changePermissions(HttpServletRequest req, HttpServletResponse resp) {
    userUcc.changePermissions(Integer.parseInt(req.getParameter("id")));
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectUsers(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<UserDto> users = userUcc.getAllUsers();
    String jsonUsers = userGenson.serialize(users);
    resp.getWriter().println(jsonUsers);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectPrograms(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<ProgramDto> programs = programUcc.getAllPrograms();
    String jsonPrograms = basicGenson.serialize(programs);
    resp.getWriter().println(jsonPrograms);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectDepartments(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<DepartmentDto> departments = null;
    try {
      departments = departmentUcc.getAllDepartments();
    } catch (NoDepartmentException exc) {
      createToaster(exc, resp);
    }
    String jsonDepartments = basicGenson.serialize(departments);
    resp.getWriter().println(jsonDepartments);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectCountries(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    ArrayList<CountryDto> countries = null;
    try {
      countries = countryUcc.getAllCountries();
    } catch (Exception exc) {
      createToaster(exc, resp);
    }
    String jsonCountries = basicGenson.serialize(countries);
    resp.getWriter().println(jsonCountries);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectAllMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<MobilityDto> mobilities = mobilityUcc.getMobilities();
    String jsonMobilities = null;
    if (mobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(mobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectConfirmedMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<MobilityDto> mobilities = mobilityUcc.getConfirmedMobilities();
    String jsonMobilities = null;
    if (null != mobilities && mobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(mobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectMyMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {

    ArrayList<MobilityDto> myMobilities =
        mobilityUcc.getMyMobilities((String) req.getSession().getAttribute(KEY_USERNAME));
    String jsonMobilities = null;
    if (myMobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(myMobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void disconnect(HttpServletRequest req, HttpServletResponse resp) {
    Main.LOGGER.info("[" + req.getSession().getAttribute(KEY_PERMISSIONS) + "] \""
        + req.getSession().getAttribute(KEY_USERNAME) + "\" disconnected.");
    req.getSession().invalidate();

    Cookie cookie = new Cookie("user", "");
    cookie.setPath("/");
    cookie.setMaxAge(0);
    resp.addCookie(cookie);

    resp.setStatus(HttpStatus.OK_200);

  }

  private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    UserDto userdto = bizzFactory.getUserDto();

    userdto.setPseudo(req.getParameter("username"));
    userdto.setPassword(req.getParameter("password"));
    userdto.setName(req.getParameter("name"));
    userdto.setFirstname(req.getParameter("firstname"));
    userdto.setEmail(req.getParameter("email"));
    // TODO (Martin) gérer si c'est la première inscription
    userdto.setPermissions("STUDENT");

    UserDto userDtoRecept = null;
    try {
      userDtoRecept = userUcc.register(userdto);
    } catch (AuthenticationException exc) {
      this.createToaster(exc, resp);
      resp.setStatus(HttpStatus.FORBIDDEN_403);
      return;
    }
    req.getSession().setAttribute(KEY_ID, userDtoRecept.getId());
    req.getSession().setAttribute(KEY_USERNAME, userDtoRecept.getPseudo());
    req.getSession().setAttribute(KEY_PERMISSIONS, userDtoRecept.getPermissions());
    createJwtCookie(resp, userDtoRecept);
    resp.setStatus(HttpStatus.ACCEPTED_202);
    resp.getWriter().println(dtoToJson(userDtoRecept));

  }

  private void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setStatus(HttpStatus.ACCEPTED_202);
    UserDto userDto = bizzFactory.getUserDto();
    if (null != req.getSession().getAttribute(KEY_USERNAME)) {
      userDto.setId((int) req.getSession().getAttribute(KEY_ID));
      userDto.setPermissions("" + req.getSession().getAttribute(KEY_PERMISSIONS));
      userDto.setPseudo("" + req.getSession().getAttribute(KEY_USERNAME));
      createJwtCookie(resp, userDto);
      resp.getWriter().println(dtoToJson(userDto));

    } else {
      if (readJwtCookie(req)) {
        userDto.setId((int) req.getSession().getAttribute(KEY_ID));

        userDto.setPermissions("" + req.getSession().getAttribute(KEY_PERMISSIONS));
        userDto.setPseudo("" + req.getSession().getAttribute(KEY_USERNAME));
        resp.getWriter().println(dtoToJson(userDto));
      } else {
        resp.setStatus(HttpStatus.UNAUTHORIZED_401);
      }
    }

  }

  private void login(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    UserDto userDtoRecept = null;
    try {
      userDtoRecept = userUcc.login(username, password);
    } catch (AuthenticationException exc) {
      this.createToaster(exc, resp);
      resp.setStatus(HttpStatus.UNAUTHORIZED_401);
      return;
    }
    createJwtCookie(resp, userDtoRecept);
    req.getSession().setAttribute(KEY_ID, userDtoRecept.getId());
    req.getSession().setAttribute(KEY_USERNAME, userDtoRecept.getPseudo());
    req.getSession().setAttribute(KEY_PERMISSIONS, userDtoRecept.getPermissions());
    resp.setStatus(HttpStatus.ACCEPTED_202);
    resp.getWriter().println(dtoToJson(userDtoRecept));
  }

  /**
   * The method used by the servlet to add a mobility to the DB.
   *
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   * @throws NumberFormatException If there is an error.
   * @throws IOException If there is an error.
   */
  private void addMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, NumberFormatException, SQLException {
    MobilityDto mobility = bizzFactory.getMobilityDto();
    // TODO (Martin) Poser question : aller chercher les Dtos dans la servlet ou dans l'ucc pour
    // profiter des transactions?

    mobility.setStudentDto(
        userUcc.getUserById(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID))));
    mobility.setPreferenceOrder(Integer.parseInt(req.getParameter("preferenceOrder")));
    mobility.setProgramDto(programUcc.getProgramByName(req.getParameter("program")));
    mobility.setType(req.getParameter("type"));
    mobility.setQuadrimester(Integer.parseInt(req.getParameter("quadrimestre")));
    try {
      mobility
          .setDepartementDto(departmentUcc.getDepartmentByLabel(req.getParameter("department")));
    } catch (NoDepartmentException exc) {
      createToaster(exc, resp);
    }
    try {
      mobility.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    } catch (Exception exc) {
      createToaster(exc, resp);
    }

    mobilityUcc.addMobility(mobility);

  }

  /**
   * The method used by the servlet to add a partner to the DB.
   *
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   * @throws NumberFormatException If there is an error.
   * @throws IOException If there is an error.
   */
  private void addPartner(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, NumberFormatException, SQLException {
    PartnerDto partner = bizzFactory.getPartnerDto();
    partner.setUserDto(
        userUcc.getUserById(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID))));
    partner.setLegalName(req.getParameter("legal_name"));
    partner.setBusiness(req.getParameter("business_name"));
    try {
      partner.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    } catch (Exception exc) {
      createToaster(exc, resp);
    }
    partner.setFullName(req.getParameter("full_name"));
    partner.setDepartment(req.getParameter("department"));
    partner.setType(req.getParameter("type"));
    partner.setNbEmployees(Integer.parseInt(0 + req.getParameter("nb_employees")));
    partner.setStreet(req.getParameter("street"));
    partner.setNumber(req.getParameter("number"));
    partner.setMailbox(req.getParameter("mailbox"));
    partner.setZip(req.getParameter("zip"));
    partner.setCity(req.getParameter("city"));
    partner.setState(req.getParameter("state"));
    partner.setTel(req.getParameter("tel"));
    partner.setEmail(req.getParameter("email"));
    partner.setWebsite(req.getParameter("website"));

    partnerUcc.addPartner(partner);
  }

  /**
   * The method used by the servlet to load the academic years
   * 
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   */
  private void loadAcademicYears(HttpServletRequest req, HttpServletResponse resp)
      throws SQLException {
    ArrayList<String> academicYears = mobilityUcc.getAcademicYears();
    String json = userGenson.serialize(academicYears);
    try {
      resp.getWriter().println(json);
    } catch (IOException exc) {
      exc.printStackTrace();
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  /**
   * The method used by the servlet to load the payment of the year specified in the parameter of
   * the request.
   * 
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   * @throws IOException If there is an error.
   */
  private void loadPayments(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    String academicYear = req.getParameter("academicYear");
    ArrayList<MobilityDto> mobilities = mobilityUcc.getFullPayments(academicYear);
    String jsonMobilities = null;
    if (mobilities.size() == 0) {
      resp.setStatus(HttpStatus.ACCEPTED_202);
    } else {
      jsonMobilities = basicGenson.serialize(mobilities);
    }
    resp.getWriter().println(jsonMobilities);
    resp.setStatus(HttpStatus.ACCEPTED_202);
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
      if (cookies != null) {
        for (Cookie c : cookies) {
          if ("user".equals(c.getName())) {
            token = c.getValue();
          }
        }
      }
      if (token != null) {
        decodedPayload = new JWTVerifier(SECRET).verify(token);
        req.getSession().setAttribute(KEY_ID, decodedPayload.get(KEY_ID));
        req.getSession().setAttribute(KEY_USERNAME, decodedPayload.get(KEY_USERNAME));
        req.getSession().setAttribute(KEY_PERMISSIONS, decodedPayload.get(KEY_PERMISSIONS));
        return true;
      }
    } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException
        | SignatureException | IOException | JWTVerifyException | NullPointerException exc) {
      return false;
    }
    return false;
  }

  /**
   * Cree un cookie avec un token JWT afin de ne pas perdre l'authentification d'un utilisateur.
   *
   * @param resp La reponse qui serra renvoyee par le serveur.
   * @param userDto Le pseudo de l'utilisateur.
   */
  private void createJwtCookie(HttpServletResponse resp, UserDto userDto) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put(KEY_ID, userDto.getId());
    claims.put(KEY_USERNAME, userDto.getPseudo());
    claims.put(KEY_PERMISSIONS, userDto.getPermissions());

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

  private HttpServletResponse createToaster(Exception exception, HttpServletResponse resp)
      throws IOException {

    Map<String, String> map = new HashMap<String, String>();

    // warning, success, error, info
    System.out.println(exception.getClass().toString());
    switch (exception.getClass().toString()) {
      case "class exceptions.AuthenticationException":
        resp.setStatus(HttpStatus.UNAUTHORIZED_401);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.NoCountryException":
      case "class exceptions.NoDepartmentException":
        resp.setStatus(HttpStatus.PARTIAL_CONTENT_206);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      default:
        resp.setStatus(HttpStatus.PARTIAL_CONTENT_206);
        map.put("type", "info");
        map.put("message", "Une erreur inconnue est survenue");
        break;
    }
    resp.getWriter().println(basicGenson.serialize(map));
    System.out.println(basicGenson.serialize(map));
    return resp;
  }

}
