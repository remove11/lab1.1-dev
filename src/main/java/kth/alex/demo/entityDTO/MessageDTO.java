package kth.alex.demo.entityDTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MessageDTO {
    private String id;
    private String senderName;
    private String senderSocialNr;
    private String receiverName;
    private String receiverSocialNr;
    private String content;
    private LocalDateTime createdAt;

}
