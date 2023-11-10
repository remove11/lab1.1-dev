package kth.alex.demo.RequestBodyData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class MessageCreate {
    private String senderSocialNr;
    private String receiverSocialNr;
    private String description;

}
