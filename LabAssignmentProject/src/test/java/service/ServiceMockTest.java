package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ServiceMockTest {
    private static Service service;
    @Mock
    private static StudentXMLRepository studentXmlRepoMock;
    @Mock
    private static HomeworkXMLRepository homeworkXmlRepoMock;
    @Mock
    private static GradeXMLRepository gradeXmlRepoMock;

    @BeforeAll
    public static void setup() {
        studentXmlRepoMock = mock(StudentXMLRepository.class);
        homeworkXmlRepoMock = mock(HomeworkXMLRepository.class);
        gradeXmlRepoMock = mock(GradeXMLRepository.class);
        service = new Service(studentXmlRepoMock, homeworkXmlRepoMock, gradeXmlRepoMock);
    }

    @Test
    public void saveStudentMock() {
        Student result = null;
        when(studentXmlRepoMock.save(any(Student.class))).thenReturn(result);
        int proba = service.saveStudent("7", "Janko", 333);
        assertTrue(proba==1, "mukodik");
    }

    @Test
    public void saveHomeworkMock() {
        Homework result = null;
        when(homeworkXmlRepoMock.save(any(Homework.class))).thenReturn(result);
        int proba = service.saveHomework("7", "gui", 8, 8);
        assertTrue(proba==1, "mukodik");
    }

    @Test
    public void deleteHomeworkMock() {
        Homework result = null;
        when(homeworkXmlRepoMock.delete(any(String.class))).thenReturn(result);
        int proba = service.deleteHomework("5");
        assertEquals(0, proba);
    }



}
