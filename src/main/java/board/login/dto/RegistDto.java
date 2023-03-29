package board.login.dto;

import lombok.Data;

@Data
public class RegistDto {
    private String userName;
    private String userId;
    private String userPwd;
    private String userConfirmPwd;
    //private String userNumber;
}
