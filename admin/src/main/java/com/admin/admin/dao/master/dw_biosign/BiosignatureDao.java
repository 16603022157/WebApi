package com.admin.admin.dao.master.dw_biosign;

import com.admin.admin.entity.dw_bios.BiosignatureInformation;
import com.admin.model.biosignature.BiosignatureReturnModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BiosignatureDao {

    int insertBiosignature(BiosignatureInformation biosignatureInformation);

    List<BiosignatureReturnModel> getBiosignature(@Param("personid")String personid, @Param("type")int type);

}
