package com.arsenii;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Student> students = (List<Student>) session.getAttribute(STUDENTS);
        if(students == null){
            students = new ArrayList<>();
            session.setAttribute(STUDENTS, students);
        }
        if(isValid(request)){
            students.add(createStudent(request));
        }
        response.sendRedirect(MAIN_PAGE);
    }

    private boolean isValid(HttpServletRequest request){
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
