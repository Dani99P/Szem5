package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ServiceTest {
    private static Service service;
    private static StudentXMLRepository studentXmlRepo;
    private static HomeworkXMLRepository homeworkXmlRepo;
    private static GradeXMLRepository gradeXmlRepo;


    @BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");
        studentXmlRepo = fileRepository1;
        homeworkXmlRepo = fileRepository2;
        gradeXmlRepo = fileRepository3;
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    public int StudentCount(Iterable<Student> findAll) {
        int db =0 ;
        for (Student s: findAll){
            db++;
        }
        return db;
    }

    public int HomeworkCount(Iterable<Homework> findAll) {
        int db =0 ;
        for (Homework s: findAll){
            db++;
        }
        return db;
    }

    @Test
    public void findAllStudents() {
        Iterable<Student> findAll2 = service.findAllStudents();
        int db1 = StudentCount(findAll2);
        int result = service.saveStudent("7", "Janko", 333);
        Iterable<Student> findAll1 = service.findAllStudents();
        int db2 = StudentCount(findAll1);
        assertTrue(db1<db2, "Megtalalta mind");
    }

    @Test
    public void findAllHomework() {
        Iterable<Homework> findAll2 = service.findAllHomework();
        int db1 = HomeworkCount(findAll2);
        int result = service.saveHomework("7", "gui", 8, 8);
        Iterable<Homework> findAll1 = service.findAllHomework();
        int db2 = HomeworkCount(findAll1);
        assertTrue(db1<db2, "Megtalalta mind");
    }

    @Test
    public void extendDeadline() {
        assertNotEquals(1, service.extendDeadline("125", 200));
    }

    @Test
    public void deleteStudent() {
        assertEquals(1, service.deleteStudent("7"));
    }

    @ParameterizedTest
    @ValueSource(strings  = {"1", "3"})
    public void deleteHomework(String number) {
        assertEquals(1, service.deleteHomework(number));

    }
}