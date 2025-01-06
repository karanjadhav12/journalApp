package net.engineeringdigest.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntryDTO {

    private ObjectId id;
    @NotEmpty
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

}
