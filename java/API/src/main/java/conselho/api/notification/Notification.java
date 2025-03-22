package conselho.api.notification;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Notification {

    private NotificationStatus status;
    private String message;
}
