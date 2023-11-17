package kth.alex.demo.entityDTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoDTO {
    private String socialNr;
    private List<String> role;
}
