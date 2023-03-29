package board.login.service;

import board.login.dto.MembersDto;
import board.login.dto.RegistDto;

public interface LoginService {
    String checkUser (MembersDto mbr) throws Exception;
    String saveRegisterInfo(RegistDto mbr) throws Exception;
    String saveCertification(MembersDto mbr) throws Exception;
}
