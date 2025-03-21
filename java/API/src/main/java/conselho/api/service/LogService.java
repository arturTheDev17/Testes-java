package conselho.api.service;

import conselho.api.model.entity.Log;
import conselho.api.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void logAction(String actionMessage) {
        Log log = new Log();
        log.setActionType(actionMessage);
        log.setMessage(actionMessage);
        logRepository.save(log);
    }
}
