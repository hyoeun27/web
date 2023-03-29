package board.login.dto;

import lombok.Data;

@Data
public class LoginHistoryDto {
    private long userNo;
    private String userId;
    private String loginDttm;
    private String lastUpdateDttm;
    private String loginIp;
    private String ServerIp;
    private int ServerPort;
}
