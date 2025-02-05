package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 12 ? * SAT")
    public void fetchUsersAndSendSaEmail() {
        List<User> userForSA = userRepositoryImpl.getUserForSA();
        for (User user : userForSA) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCount = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
                }
                Sentiment mostFrequentSentiment = null;
                int maxCount = 0;
                for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        mostFrequentSentiment = entry.getKey();
                    }
                }
                if (mostFrequentSentiment != null) {
                    emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }

}
