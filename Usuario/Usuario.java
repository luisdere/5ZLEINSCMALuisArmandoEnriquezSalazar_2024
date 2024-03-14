public class Usuario {
    private int id;
    private String correo;
    private String contraseña;
    
    // Getters y setters
}

public class UsuarioDAO {
    public Usuario iniciarSesion(String correo, String contraseña) {
        // Lógica para verificar el correo y la contraseña en la base de datos
        // Devuelve un objeto Usuario si la autenticación es exitosa, null en caso contrario
    }
}

// En login.jsp
<form action="autenticar.jsp" method="post">
    Correo: <input type="text" name="correo"><br>
    Contraseña: <input type="password" name="contraseña"><br>
    <input type="submit" value="Iniciar sesión">
</form>

// En autenticar.jsp
<%
String correo = request.getParameter("correo");
String contraseña = request.getParameter("contraseña");

UsuarioDAO usuarioDAO = new UsuarioDAO();
Usuario usuario = usuarioDAO.iniciarSesion(correo, contraseña);

if (usuario != null) {
    session.setAttribute("usuario", usuario);
    response.sendRedirect("consultaAlumnos.jsp");
} else {
    response.sendRedirect("error.jsp");
}
%>

// En inicio.jsp
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
if (usuario != null) {
    // Contenido de la página inicio.jsp
    <a href="registroAlumnos.jsp">Registrar alumnos</a>
} else {
    response.sendRedirect("login.jsp");
}
%>
// Usuario.java
public class Usuario {
    private int id;
    private String correo;
    private String contraseña;

    // Getters y setters
}

// UsuarioDAO.java
public class UsuarioDAO {
    public Usuario obtenerUsuarioPorCorreo(String correo) {
        // Lógica para obtener un usuario por su correo de la base de datos
        // Retorna un objeto Usuario si se encuentra, o null si no se encuentra
    }
}

// LoginServlet.java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");

        Usuario usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);

        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("consultaAlumnos");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}

// InicioServlet.java
@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null) {
            // Contenido de la página inicio.jsp
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
