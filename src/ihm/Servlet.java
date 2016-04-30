package ihm;

import bizz.interfaces.BizzFactory;
import dto.CancelationDto;
import dto.CountryDto;
import dto.DepartmentDto;
import dto.MobilityDto;
import dto.PartnerDto;
import dto.ProgramDto;
import dto.UserDto;
import exceptions.AuthenticationException;
import exceptions.BadMobilityStatusException;
import exceptions.NoCountryException;
import exceptions.NoDepartmentException;
import exceptions.NoMobilityException;
import exceptions.NoProgramException;
import exceptions.NotEnoughPermissionsException;
import exceptions.OptimisticLockException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
          selectProfile(req, resp, Integer.parseInt("" + req.getSession().getAttribute(KEY_ID)));
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
          selectPayments(req, resp);
          break;
        case "selectAddMobilityInformations":
          selectAddMobilityInformations(req, resp);
          break;
        case "selectUserInformationsById":
          selectProfile(req, resp, Integer.parseInt("" + req.getParameter("id")));
          break;
        case "loadCancelationsReasons":
          loadCancelationReasons(req, resp);
          break;
        case "selectMobility":
          selectMobility(req, resp);
          break;
        case "selectInfoPartner":
          selectPartnerById(req, resp);
          break;
        case "cancelMobility":
          cancelMobility(req, resp);
          break;
        case "selectPartnersForConfirm":
          selectPartnersForConfirm(req, resp);
          break;
        case "confirmPartnerInMobility":
          confirmPartnerInMobility(req, resp);
          break;
        case "updateMobilityDetail":
          updateMobilityDetail(req, resp);
          break;
        case "updatePartner":
          updatePartner(req, resp);
          break;
        case "checkPermission":
          checkPermission(req, resp);
          break;
        default:
          resp.setStatus(HttpStatus.BAD_REQUEST_400);
      }
    } catch (Exception exc) {
      createToaster(exc, resp);
    }

  }

  private void updateMobilityDetail(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, BadMobilityStatusException, OptimisticLockException {
    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }
    MobilityDto mobility = bizzFactory.getMobilityDto();
    mobility.setVerNr(Integer.parseInt("" + req.getParameter("nrVersion")));
    mobility.setId(Integer.parseInt("" + req.getParameter("id")));
    mobility.setPaymentDate1(Boolean.parseBoolean(req.getParameter("paiement1")));
    mobility.setPaymentDate2(Boolean.parseBoolean(req.getParameter("paiement2")));
    mobility.setSoftwareProeco(Boolean.parseBoolean(req.getParameter("proEco")));
    mobility.setSoftwareMobilityTools(Boolean.parseBoolean(req.getParameter("mobilitytool")));
    mobility.setSoftwareMobi(Boolean.parseBoolean(req.getParameter("mobi")));
    mobility
        .setDepartureGrantContract(Boolean.parseBoolean(req.getParameter("departContratBourse")));
    mobility.setDepartureConventionInternshipSchoolarship(
        Boolean.parseBoolean(req.getParameter("departConventionStageEtudes")));
    mobility.setDepartureStudentConvention(
        Boolean.parseBoolean(req.getParameter("departCharteEtudiant")));
    mobility.setDepartureDocAggreement(Boolean.parseBoolean(req.getParameter("departEngagement")));
    mobility.setDepartureErasmusLanguageTest(
        Boolean.parseBoolean(req.getParameter("departTestLangue")));
    mobility.setDepartDocSentHighschool(
        Boolean.parseBoolean(req.getParameter("departDocumentHauteEcole")));
    mobility
        .setReturnResidenceCert(Boolean.parseBoolean(req.getParameter("retourAttestationSejour")));
    mobility.setReturnTranscript(Boolean.parseBoolean(req.getParameter("retourReleveNotes")));
    mobility
        .setReturnInternshipCert(Boolean.parseBoolean(req.getParameter("retourCertificatStage")));
    mobility.setReturnFinalReport(Boolean.parseBoolean(req.getParameter("retourRapportFinal")));
    mobility
        .setReturnErasmusLanguageTest(Boolean.parseBoolean(req.getParameter("retourTestLangue")));
    mobility.setReturnDocSentHighschool(
        Boolean.parseBoolean(req.getParameter("retourDocumentHauteEcole")));
    mobility.setStatus("" + req.getParameter("etat"));
    mobility.setAmount(Double.parseDouble("" + req.getParameter("montant")));


    mobilityUcc.updateMobilityDetails(mobility);

  }

  private void updatePartner(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, NoCountryException, SQLException {
    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    PartnerDto partnerDto = bizzFactory.getPartnerDto();
    partnerDto.setId(Integer.parseInt("" + req.getParameter("idP")));
    partnerDto.setVerNr(Integer.parseInt("" + req.getParameter("nrVersion")));
    partnerDto.setLegalName(req.getParameter("legalName"));
    partnerDto.setBusiness(req.getParameter("businnes"));
    partnerDto.setFullName(req.getParameter("fullName"));
    partnerDto.setDepartment(req.getParameter("department"));
    partnerDto.setType(req.getParameter("type"));
    partnerDto.setNbEmployees(Integer.parseInt("" + req.getParameter("nbEmployees")));
    partnerDto.setStreet(req.getParameter("street"));
    partnerDto.setNumber(req.getParameter("number"));
    partnerDto.setZip(req.getParameter("zip"));
    partnerDto.setMailbox(req.getParameter("mailbox"));
    partnerDto.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    partnerDto.setState(req.getParameter("state"));
    partnerDto.setCity(req.getParameter("city"));
    partnerDto.setTel(req.getParameter("tel"));
    partnerDto.setEmail(req.getParameter("email"));
    partnerDto.setWebsite(req.getParameter("website"));

    partnerUcc.updatePartner(partnerDto);
  }

  private void selectMobility(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, SQLException, IOException, NoMobilityException {
    int id = Integer.parseInt(req.getParameter("id"));
    MobilityDto mobilityDto = mobilityUcc.getMobilityById(id);
    String json = basicGenson.serialize(mobilityDto);
    resp.getWriter().print(json);
    resp.setStatus(HttpStatus.ACCEPTED_202);

  }

  private void selectAddMobilityInformations(HttpServletRequest req, HttpServletResponse resp)
      throws SQLException, NoDepartmentException, IOException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<ProgramDto> programs = programUcc.getAllPrograms();
    ArrayList<DepartmentDto> departments = departmentUcc.getAllDepartments();
    ArrayList<CountryDto> countries = countryUcc.getAllCountries();
    ArrayList<PartnerDto> partners = partnerUcc.getAllPartners();

    HashMap<String, Object> datas = new HashMap<String, Object>();
    datas.put("programs", programs);
    datas.put("departments", departments);
    datas.put("countries", countries);
    datas.put("partners", partners);

    String json = basicGenson.serialize(datas);
    resp.getWriter().print(json);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectProfile(HttpServletRequest req, HttpServletResponse resp, int id)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    UserDto userSelected = userUcc.getUserById(id);
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
   * @throws NoCountryException If there is an error.
   * @throws NotEnoughPermissionsException If the user don't have the permissions to perform the
   *         action
   */
  private void updateUser(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NoCountryException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    int id = Integer.parseInt("" + req.getParameter("idUser"));
    if (id == -1) {
      id = Integer.parseInt("" + req.getSession().getAttribute(KEY_ID));
    }

    UserDto userEdited = null;
    userEdited = userUcc.getUserById(id);
    assert userEdited != null;
    userEdited.setId(id);
    userEdited.setName(req.getParameter("name"));
    userEdited.setFirstname(req.getParameter("firstname"));
    userEdited.setGender(req.getParameter("gender"));
    try {
      userEdited.setBirthDate(LocalDate.parse(req.getParameter("birthdate")));
    } catch (IllegalArgumentException | DateTimeParseException exc) {
      createToaster(exc, resp);
    }
    userEdited.setCitizenship(req.getParameter("citizenship"));
    userEdited.setStreet(req.getParameter("street"));
    userEdited.setHouseNumber(req.getParameter("houseNumber"));
    userEdited.setMailBox(req.getParameter("mailbox"));
    userEdited.setZip(req.getParameter("zipcode"));
    userEdited.setCity(req.getParameter("city"));
    userEdited.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    userEdited.setCountry(userEdited.getCountryDto().getNameFr());
    userEdited.setTel(req.getParameter("tel"));
    userEdited.setSuccessfullYearInCollege(
        Integer.parseInt(0 + req.getParameter("successfullYearsInCollege")));
    try {
      userEdited.setIban(req.getParameter("iban"));
    } catch (IllegalArgumentException exc) {
      createToaster(exc, resp);
    }
    userEdited.setAccountHolder(req.getParameter("accountHolder"));
    userEdited.setBankName(req.getParameter("bankName"));
    userEdited.setBic(req.getParameter("bic"));
    userEdited.setVerNr(Integer.parseInt("" + req.getParameter("verNr")));

    userUcc.updateUser(userEdited);
  }

  private void changePermissions(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, NumberFormatException, UserNotFoundException {

    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    userUcc.changePermissions(Integer.parseInt(req.getParameter("id")),
        Integer.parseInt(req.getParameter("vrNr")));

    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectUsers(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<UserDto> users = null;
    users = userUcc.getAllUsers();
    String jsonUsers = userGenson.serialize(users);
    resp.getWriter().println(jsonUsers);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectPrograms(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<ProgramDto> programs = programUcc.getAllPrograms();
    String jsonPrograms = basicGenson.serialize(programs);
    resp.getWriter().println(jsonPrograms);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectDepartments(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NoDepartmentException, NotEnoughPermissionsException {

    /*
     * if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) { throw new
     * NotEnoughPermissionsException( "Vous n'avez pas les droits nécessaires pour faire cela"); }
     */

    ArrayList<DepartmentDto> departments = null;
    departments = departmentUcc.getAllDepartments();
    String jsonDepartments = basicGenson.serialize(departments);
    resp.getWriter().println(jsonDepartments);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectCountries(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<CountryDto> countries = null;
    countries = countryUcc.getAllCountries();
    String jsonCountries = basicGenson.serialize(countries);
    resp.getWriter().println(jsonCountries);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectAllMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<MobilityDto> mobilities = mobilityUcc.getMobilities();
    String jsonMobilities = null;
    if (mobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(mobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectConfirmedMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<MobilityDto> mobilities = mobilityUcc.getConfirmedMobilities();
    String jsonMobilities = null;
    if (null != mobilities && mobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(mobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }


  private void selectPartnersForConfirm(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException, NoMobilityException {

    /*
     * if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("STUDENT")) { throw new
     * NotEnoughPermissionsException( "Vous n'avez pas les droits nécessaires pour faire cela"); }
     */
    int idMobility = Integer.parseInt(0 + req.getParameter("idMobility"));
    MobilityDto mobilityDto = mobilityUcc.getMobilityById(idMobility);
    ArrayList<PartnerDto> partners =
        partnerUcc.getPartnerMin(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID)),
            (String) req.getSession().getAttribute(KEY_PERMISSIONS));

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("partners", partners);
    hashMap.put("mobility", mobilityDto);

    String json;

    if (hashMap.size() != 0) {
      json = basicGenson.serialize(hashMap);
      resp.getWriter().println(json);
    }

    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void confirmPartnerInMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {
    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    MobilityDto mobilityDto = bizzFactory.getMobilityDto();
    mobilityDto.setId(Integer.parseInt("" + req.getParameter("idMobility")));
    if (req.getParameter("idPartner") == null) {
      throw new NotEnoughPermissionsException("Veuillez choisir un partenaire");
    } else {
      mobilityDto.setIdPartner(Integer.parseInt("" + req.getParameter("idPartner")));
    }
    mobilityDto.setStatus("Créée");
    mobilityDto.setVerNr(Integer.parseInt("" + req.getParameter("verNr")));

    mobilityUcc.confirmPartner(mobilityDto);

    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void selectPartnerById(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, IOException, SQLException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS).equals("STUDENT")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    PartnerDto partner = partnerUcc.getPartnerById(Integer.parseInt(req.getParameter("id")));

    String jsonPartner = basicGenson.serialize(partner);
    resp.getWriter().println(jsonPartner);
    resp.setStatus(HttpStatus.ACCEPTED_202);

  }

  private void selectMyMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {
    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<MobilityDto> myMobilities =
        mobilityUcc.getMyMobilities((String) req.getSession().getAttribute(KEY_USERNAME));
    String jsonMobilities = null;
    if (myMobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(myMobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void cancelMobility(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, SQLException {

    int idCancelation = Integer.parseInt(req.getParameter("idReason"));
    if (req.getParameter("reasonValue") != null) { // If user entered a reason by textarea.
      CancelationDto cancelationDto = bizzFactory.getCancelationDto();
      cancelationDto.setResponsible((String) req.getSession().getAttribute(KEY_PERMISSIONS));
      cancelationDto.setReason(req.getParameter("reasonValue"));
      idCancelation = cancelationUcc.insertCancelation(cancelationDto);
    }
    int verNr = Integer.parseInt(req.getParameter("verNr"));
    mobilityUcc.cancelMobility(Integer.parseInt(req.getParameter("idMobility")), idCancelation,
        verNr);
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  private void disconnect(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    Main.LOGGER.info("[" + req.getSession().getAttribute(KEY_PERMISSIONS) + "] \""
        + req.getSession().getAttribute(KEY_USERNAME) + "\" disconnected.");
    req.getSession().invalidate();

    Cookie cookie = new Cookie("user", "");
    cookie.setPath("/");
    cookie.setMaxAge(0);
    resp.addCookie(cookie);

    resp.setStatus(HttpStatus.OK_200);

  }

  private void register(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, AuthenticationException, UserAlreadyExistsException,
      NotEnoughPermissionsException, SQLException, NoDepartmentException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) != null) {
      throw new NotEnoughPermissionsException(
          "Vous ne pouvez pas faire cela si vous êtes connecté");
    }

    UserDto userdto = bizzFactory.getUserDto();

    userdto.setPseudo(req.getParameter("username"));
    userdto.setPassword(req.getParameter("password"));
    userdto.setName(req.getParameter("name"));
    userdto.setFirstname(req.getParameter("firstname"));
    userdto.setEmail(req.getParameter("email"));
    userdto.setDepartmentDto(departmentUcc.getDepartmentByLabel(req.getParameter("department")));

    UserDto userDtoRecept = null;
    userDtoRecept = userUcc.register(userdto);
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
      throws IOException, SQLException, AuthenticationException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) != null) {
      throw new NotEnoughPermissionsException("Vous êtes déjà connecté");
    }

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    UserDto userDtoRecept = null;
    userDtoRecept = userUcc.login(username, password);

    createJwtCookie(resp, userDtoRecept);
    req.getSession().setAttribute(KEY_ID, userDtoRecept.getId());
    req.getSession().setAttribute(KEY_USERNAME, userDtoRecept.getPseudo());
    req.getSession().setAttribute(KEY_PERMISSIONS, userDtoRecept.getPermissions());
    resp.setStatus(HttpStatus.ACCEPTED_202);
    resp.getWriter().println(dtoToJson(userDtoRecept));
  }

  private void addMobility(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, NumberFormatException, SQLException, NoCountryException,
      NoDepartmentException, NotEnoughPermissionsException, NoProgramException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    MobilityDto mobility = bizzFactory.getMobilityDto();

    mobility.setStudentDto(
        userUcc.getUserById(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID))));
    mobility.setPreferenceOrder(Integer.parseInt(req.getParameter("preferenceOrder")));
    mobility.setProgramDto(programUcc.getProgramByName(req.getParameter("program")));
    mobility.setType(req.getParameter("type"));
    mobility.setQuadrimester(Integer.parseInt(req.getParameter("quadrimestre")));
    mobility.setAcademicYear(req.getParameter("year"));
    mobility.setDepartmentDto(departmentUcc.getDepartmentByLabel(req.getParameter("department")));
    mobility.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
    if (Integer.parseInt("" + req.getParameter("partner")) != -1) {
      mobility.setPartnerDto(
          partnerUcc.getPartnerById(Integer.parseInt("" + req.getParameter("partner"))));
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
   * @throws NoCountryException If there is an error.
   * @throws NotEnoughPermissionsException If the user don't have the permissions to perform the
   *         action
   */
  private void addPartner(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, NumberFormatException, SQLException, NoCountryException,
      NotEnoughPermissionsException, NoDepartmentException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    PartnerDto partner = bizzFactory.getPartnerDto();
    partner.setUserDto(
        userUcc.getUserById(Integer.parseInt("" + req.getSession().getAttribute(KEY_ID))));

    // change department id of Teacher for join partner with the good department
    if (req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      partner.getUserDto().setIdDepartment(
          departmentUcc.getDepartmentByLabel(req.getParameter("schoolDepartment")).getId());
    }
    partner.setLegalName(req.getParameter("legal_name"));
    partner.setBusiness(req.getParameter("business_name"));
    partner.setCountryDto(countryUcc.getCountryByNameFr(req.getParameter("country")));
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
    if (req.getSession().getAttribute(KEY_PERMISSIONS).equals("STUDENT")) {
      partner.setExists(false);
    }
    if (req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      partner.setExists(true);
    }
    partner.setVerNr(0);

    partnerUcc.addPartner(partner);
  }

  /**
   * The method used by the servlet to load the academic years
   * 
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws SQLException If there is an error.
   * @throws IOException If there is an error.
   * @throws NotEnoughPermissionsException If the user don't have the permissions to perform the
   *         action
   */
  private void loadAcademicYears(HttpServletRequest req, HttpServletResponse resp)
      throws SQLException, IOException, NotEnoughPermissionsException {

    if (req.getSession().getAttribute(KEY_PERMISSIONS) == null) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<String> academicYears = mobilityUcc.getAcademicYears();
    String json = userGenson.serialize(academicYears);
    resp.getWriter().println(json);
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
   * @throws NotEnoughPermissionsException If the user don't have the permissions to perform the
   *         action
   */
  private void selectPayments(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException, NotEnoughPermissionsException {

    if (!req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER")) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }

    ArrayList<MobilityDto> mobilities = mobilityUcc.getFullPayments();
    String jsonMobilities = null;
    if (null != mobilities && mobilities.size() != 0) {
      jsonMobilities = basicGenson.serialize(mobilities);
      resp.getWriter().println(jsonMobilities);
    }
    resp.setStatus(HttpStatus.ACCEPTED_202);
  }

  /**
   * The method used by the servlet to load the cancelationsReasons.
   * 
   * @param req The request received by the server.
   * @param resp The response sended by the server.
   * @throws IOException IOException If there is an error to write the response.
   * @throws SQLException If there is an error with the database.
   */
  private void loadCancelationReasons(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, SQLException {
    ArrayList<CancelationDto> cancelations = cancelationUcc.getAllReasonsOfTeacher();
    String jsonMobilities = null;
    if (cancelations.size() == 0) {
      resp.setStatus(HttpStatus.ACCEPTED_202);
    } else {
      jsonMobilities = basicGenson.serialize(cancelations);
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
    exception.printStackTrace();
    Map<String, String> map = new HashMap<String, String>();
    // warning, success, error, info
    switch (exception.getClass().toString()) {
      case "class exceptions.AuthenticationException":
        resp.setStatus(HttpStatus.UNAUTHORIZED_401);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.NoCountryException":
      case "class exceptions.NoProgramException":
      case "class exceptions.NoDepartmentException":
      case "class exceptions.NoMobilityException":
        resp.setStatus(HttpStatus.EXPECTATION_FAILED_417);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      case "class java.time.format.DateTimeParseException":
        resp.setStatus(HttpStatus.EXPECTATION_FAILED_417);
        map.put("type", "warning");
        map.put("message", "La date entrée n'est pas correcte");
        break;
      case "class exceptions.UserAlreadyExistsException":
      case "class exceptions.UserNotFoundException":
        resp.setStatus(HttpStatus.NOT_FOUND_404);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.NotEnoughPermissionsException":
        resp.setStatus(HttpStatus.FORBIDDEN_403);
        map.put("type", "warning");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.UnknowErrorException":
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
        map.put("type", "warning");
        map.put("message", exception.getMessage());
        break;
      case "class java.lang.IllegalArgumentException":
        resp.setStatus(HttpStatus.EXPECTATION_FAILED_417);
        map.put("type", "warning");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.BadMobilityStatusException":
        resp.setStatus(HttpStatus.FORBIDDEN_403);
        map.put("type", "error");
        map.put("message", exception.getMessage());
        break;
      case "class exceptions.OptimisticLockException":
        resp.setStatus(HttpStatus.CONFLICT_409);
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
    return resp;
  }

  private void checkPermission(HttpServletRequest req, HttpServletResponse resp)
      throws NotEnoughPermissionsException, IOException, SQLException {

    if (!(req.getSession().getAttribute(KEY_PERMISSIONS).equals("STUDENT")
        || req.getSession().getAttribute(KEY_PERMISSIONS).equals("TEACHER"))) {
      throw new NotEnoughPermissionsException(
          "Vous n'avez pas les droits nécessaires pour faire cela");
    }
    String permission = (String) req.getSession().getAttribute(KEY_PERMISSIONS);
    String jsonPartner = basicGenson.serialize(permission);
    resp.getWriter().println(jsonPartner);
    resp.setStatus(HttpStatus.ACCEPTED_202);

  }

}
