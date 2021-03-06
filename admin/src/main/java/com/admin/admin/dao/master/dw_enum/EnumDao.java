package com.admin.admin.dao.master.dw_enum;

import com.admin.admin.entity.dw_enum.TEnum;
import com.admin.model.enummodel.EnumModel;
import com.admin.model.enummodel.EnumSearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnumDao {

    //获取枚举值
    List<Map<String,Object>> GetEnum(String Code);

    List<Map<String,Object>> GetPolice(String PoliceStation);

    //获取枚举数据
    List<Map<String,Object>> getEnum();

    /**
     * 获取所有 一定要用 List<Map<String,Object>> ？？是的
     * @return
     */
    List<Map<String,Object>> ListMechanism( @Param("condition") String condition);

    /**
     * 派出所编号
     * @param Code
     * @return
     */
    List<Map<String,String>> ListSponsor(String Code);

    List<TEnum> listEnum(@Param("typeCode")String typeCode);

    int addEnum(@Param("typeName")String typeName, @Param("typeCode")String typeCode, @Param("status")boolean status,@Param("enumCode")String enumCode, @Param("enumName")String enumName);

    int updateEnum(@Param("enumId")int enumId,@Param("typeName")String typeName, @Param("typeCode")String typeCode, @Param("status")boolean status, @Param("enumName")String enumName);

    int deleteEnum(@Param("enumId")int enumId);

    List<EnumModel> listAllEnum();

    List<EnumModel> findEnum(@Param(("date")) EnumSearchModel enumSearchModel);

    EnumModel getOneEnum(@Param("enumId")int enumId);
}
