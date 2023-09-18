package notification.sender.Services;

import notification.sender.Models.Letters;

public interface LettersService {

    Letters getById(Long id);

    void save(Letters letters);

}
