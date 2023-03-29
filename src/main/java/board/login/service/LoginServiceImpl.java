package board.login.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.login.dto.RegistDto;
import board.login.dto.MembersDto;
import board.login.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;

    public String saveRegisterInfo(RegistDto mbr) throws Exception {
        String emailRegExp =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern;

        pattern = Pattern.compile(emailRegExp);

        if (mbr.getUserName() == null || mbr.getUserName().trim().isEmpty()) {
            return "이름(First Name)은 필수 정보 입니다.";
        }

        if (mbr.getUserPwd() == null || mbr.getUserPwd().trim().isEmpty()) {
            return "비밀번호(Password)은 필수 정보 입니다.";
        }
        if (mbr.getUserPwd().trim().length() < 8) {
            return "비밀번호(Password)는 8자 이상 입력하세요.";
        }
        if (mbr.getUserConfirmPwd() == null || mbr.getUserConfirmPwd().trim().isEmpty()) {
            return "비밀번호확인(Confirm password)은 필수 정보 입니다.";
        }
        if (!mbr.getUserPwd().equals(mbr.getUserConfirmPwd())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        return "Ok";
    }

    public String saveCertification(MembersDto mbr) throws Exception {
        // 등록상태 확인
        List<MembersDto> membersList = loginMapper.selectMembers(mbr.getUserId());
        if (membersList.isEmpty()) {
            return "신청한 내역이 존재하지 않습니다.";
        }

        MembersDto members = membersList.get(0);
        if (!members.getUserNo().equals(mbr.getUserNo())) {
            return "신청한 계정 정보와 인증 정보가 불일치합니다.";
        }
        else if (members.getCertificationDate() != null && !members.getCertificationDate().equals("")) {
            if (!members.getEnabled().equals("Y")) {
                return "계정이 비활성(사용불가) 상태입니다.";
            }
            return "Ok";
        }
        else if (members.getEnabled().equals("Y") && members.getCertificationDate() != null) {
            return "Ok";
        }

        // 계정 인증
        loginMapper.updateMembersCert(members);

        return "Ok";
    }

    public String checkUser(MembersDto mbr) throws Exception{
        // 등록상태 확인
        List<MembersDto> membersList = loginMapper.selectMembers(mbr.getUserId());
        if (membersList.isEmpty()) {
            return "가입 내역이 존재하지 않습니다.";
        }

        MembersDto members = membersList.get(0);
        if (!members.getUserPwd().equals(mbr.getUserPwd())) {
            return "비밀번호가 맞지 않습니다.";
        }
        else if (members.getCertificationDate() == null || members.getCertificationDate().equals("")) {
            return "계정이 미인증 상태 입니다.";
        }
        else if (!members.getEnabled().equals("Y")) {
            return "계정이 비활성(사용불가) 상태입니다.";
        }

        return "Ok";
    }
}
