package kz.iitu.ramazan.studentapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    private StudentRepository repository;
    private StudentController controller;
    private Student student;

    @BeforeEach
    void setup() {
        repository = mock(StudentRepository.class);
        controller = new StudentController(repository);

        student = new Student();
        student.setFirstName("Ramazan");
        student.setLastName("Suleimanov");
        student.setGroupCode("IT2-2308");
        student.setStudentId("37408");
    }

    @Test
    void testCreateStudent() {
        when(repository.save(student)).thenReturn(student);

        Student result = controller.create(student);

        assertEquals("Ramazan", result.getFirstName());
        assertEquals("Suleimanov", result.getLastName());
        verify(repository).save(student);
    }

    @Test
    void testGetAllStudents() {
        when(repository.findAll()).thenReturn(List.of(student));

        assertEquals(1, controller.getAll().size());
    }

    @Test
    void testGetStudentById() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        assertEquals("37408",
                controller.getById(1L).getStudentId());
    }

    @Test
    void testUpdateStudent() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(student));
        when(repository.save(any(Student.class)))
                .thenAnswer(i -> i.getArgument(0));

        Student updated = new Student();
        updated.setFirstName("Ramazan");
        updated.setLastName("Suleimanov");
        updated.setGroupCode("IT2-2308");
        updated.setStudentId("37408");

        Student result = controller.update(1L, updated);

        assertEquals("IT2-2308", result.getGroupCode());
        verify(repository).save(student);
    }

    @Test
    void testDeleteStudent() {
        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        controller.delete(1L);

        verify(repository).delete(student);
    }

    @Test
    void testStudentNotFound() {
        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> controller.getById(999L));
    }
}
