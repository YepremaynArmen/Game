package game.dev.Services;

import game.dev.Models.Questions;

import java.util.List;

public interface QuestionsService {


    Questions getById(Long questionId);

    void save(Questions questions);

    public Questions patchQuestions(Long questionId, Questions questions);

    void  deleteById(Long questionId);
    List<Questions> getAll();

}
