package board.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import board.login.dto.MembersDto;

import java.util.List;

@Mapper
public interface LoginMapper {
    List<MembersDto> selectMembers(String userId) throws Exception;
    void insertMembers(MembersDto membersDto) throws Exception;
    void insertMyInfo(String userNo) throws Exception;
    void updateMembersCert(MembersDto membersDto) throws Exception;
}
