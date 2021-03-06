package com.prisonapp.business.service.dw_enum;

import com.prisonapp.business.dao.dw_enum.EnumDao;
import com.prisonapp.business.entity.dw_enum.AreaAddressInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnumService {
    @Autowired
    private EnumDao enumDao;

    public List<AreaAddressInfo> getProvice() {
        return enumDao.getProvice();
    }

    public List<AreaAddressInfo> getCity(String code) {
        return enumDao.getCity(code);
    }

    public List<AreaAddressInfo> getDistrict(String code) {
        return enumDao.getDistrict(code);
    }
}
