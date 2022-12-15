package com.arsenii;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
    public static final String STUDENTS = "students";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String GROUP = "group";
    public static final String FACULTY = "faculty";
    public static final String MAIN_PAGE = "index.jsp";
    private final StudentRepository studentRepository;

    public StudentController() {
        this.studentRepository = new StudentRepository(new DBManager());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isValid(request)) {
            studentRepository.addStudent(createStudent(request));
        }
        request.getSession().setAttribute(STUDENTS, studentRepository.getAllStudents());
        response.sendRedirect(MAIN_PAGE);
    }

    private boolean isValid(HttpServletRequest request) {
        return !request.getParameter(FIRSTNAME).isBlank() && !request.getParameter(LASTNAME).isBlank();
    }

    private Student createStudent(HttpServletRequest request) {
        return new Student(
                request.getParameter(FIRSTNAME),
                request.getParameter(LASTNAME),
                Integer.parseInt(request.getParameter(AGE)),
                request.getParameter(EMAIL),
                request.getParameter(GROUP),
                request.getParameter(FACULTY)
        );
    }
}
