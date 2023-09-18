package game.dev.Services;

import game.dev.Models.StudentAnswers;

import java.util.List;

public interface StudentAnswersService {


    StudentAnswers getById(Long id);

    void save(StudentAnswers studentAnswers);
    public StudentAnswers patchStudentAnswers(Long id, StudentAnswers studentAnswers);
    void  deleteById(Long id);
    List<StudentAnswers> getAll();

}
