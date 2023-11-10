package kth.alex.demo.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageDTO {
    private String id;
    private String senderName;
    private String senderSocialNr;
    private String receiverName;
    private String receiverSocialNr;
    private String content;
    private LocalDateTime createdAt;

}
