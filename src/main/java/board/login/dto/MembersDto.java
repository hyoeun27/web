package board.login.dto;

import lombok.Data;

@Data
public class MembersDto {
    private String userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String roleName;
    private String enabled;
    private String registDate;
    private String certificationDate;
}
