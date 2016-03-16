package ihm;

import bizz.BizzFactory;
import dto.UserDto;
import ucc.UserUcController;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.reflect.VisibilityFilter;

import org.eclipse.jetty.http.HttpStatus;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
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

    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    private static final String HTML_BODY_PAGES_PATH = "www/assets/pages/";

    private transient UserUcController userUcc = null;
    private transient BizzFactory bizzFactory = null;

    private transient Genson genson =
        new GensonBuilder().useFields(true, VisibilityFilter.PRIVATE).useMethods(false)
            .exclude("password").create();

    public Servlet(UserUcController userUcc, BizzFactory bizzFactory) {
        this.userUcc = userUcc;
        this.bizzFactory = bizzFactory;
    }

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setContentType(CONTENT_TYPE);
        PrintWriter out = resp.getWriter();
        out.println(htmlToString("www/assets/header.html"));
        out.println(htmlToString("www/assets/logo.html"));
        out.println(htmlToString("www/assets/navBarStudent.html"));
        out.println(htmlToString("www/assets/navBarTeacher.html"));
        File folder = new File(HTML_BODY_PAGES_PATH);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                out.println(htmlToString(HTML_BODY_PAGES_PATH + file.getName()));
            }
        }
        out.println(htmlToString("www/assets/footer.html"));

    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
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
                        createJwtCookie(resp, userDtoRecept);
                        resp.setStatus(HttpStatus.ACCEPTED_202);
                        resp.getWriter().println(dtoToJson(userDtoRecept));

                    }
                    break;

                case "authenticate":
                    resp.setStatus(HttpStatus.ACCEPTED_202);
                    UserDto userDto = bizzFactory.getUserDto();
                    if (null != session.getAttribute("username")) {
                        userDto.setPermissions("" + session.getAttribute("rights"));
                        userDto.setPseudo("" + session.getAttribute("username"));
                        createJwtCookie(resp, userDto);
                        resp.getWriter().println(dtoToJson(userDto));

                    } else {
                        if (readJwtCookie(req)) {
                            userDto.setPermissions("" + session.getAttribute("rights"));
                            userDto.setPseudo("" + session.getAttribute("username"));
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
                    String confirmation = req.getParameter("confirmation");

                    userDtoRecept = userUcc.register(userdto, confirmation);
                    if (userDtoRecept == null) {
                        resp.setStatus(HttpStatus.FORBIDDEN_403);
                    } else {
                        session.setAttribute("username", userDtoRecept.getPseudo());
                        createJwtCookie(resp, userDtoRecept);
                        resp.setStatus(HttpStatus.ACCEPTED_202);
                        resp.getWriter().println(dtoToJson(userDtoRecept));
                    }

                    break;

                case "confirmedMobility":
                    // Appel de fonction
                    break;

                case "editProfile":

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

        req.getSession().setAttribute("username", decodedPayload.get("username"));
        req.getSession().setAttribute("rights", decodedPayload.get("rights"));
        return true;
    }

    /**
     * Cree un cookie avec un token JWT afin de ne pas perdre l'authentification d'un utilisateur.
     *
     * @param resp  La reponse qui serra renvoyee par le serveur.
     * @param login Le pseudo de l'utilisateur.
     */
    private void createJwtCookie(HttpServletResponse resp, UserDto userDto) {

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", userDto.getPseudo());
        claims.put("rights", userDto.getPermissions());
        claims.put("id", "1");

        String token = new JWTSigner(SECRET).sign(claims);

        Cookie cookie = new Cookie("user", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(cookie);
    }

    private String dtoToJson(UserDto dto) {
        return genson.serialize(dto);
    }

    /**
     * Extract html code from html file in an string
     *
     * @param file path of html file
     * @return String
     */
    private String htmlToString(String file) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
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
