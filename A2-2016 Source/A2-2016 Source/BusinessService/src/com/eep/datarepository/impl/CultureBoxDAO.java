/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.ICultureBoxDAO;
import java.util.List;
import com.eep.datarepository.dto.CultureBoxDTO;

/**
 *
 * @author zhongzhu
 */
public class CultureBoxDAO extends AbstractLeafTechInventoryItemDAO<CultureBoxDTO> implements ICultureBoxDAO {

    public CultureBoxDAO(UserInfo userInfo) {
        super(CultureBoxDTO.class, Constants.DATABASE_LEAFTECH, Constants.TABLE_CULTUREBOX, userInfo);
    }

    @Override
    public List<CultureBoxDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public CultureBoxDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(CultureBoxDTO s) {
        super.insert(s);
    }

    @Override
    public void update(CultureBoxDTO s) {
        super.update(s);
    }

}
