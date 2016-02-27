/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.ShrubDTO;
import java.util.List;
import com.eep.datarepository.IShrubDAO;

/**
 *
 * @author zhongzhu
 */
public class ShrubDAO extends AbstractInventoryItemDAO<ShrubDTO> implements IShrubDAO {

    public ShrubDAO() {
        super(ShrubDTO.class, Constants.DATABASE_INVENTORY, Constants.TABLE_SHRUB);
    }

    @Override
    public List<ShrubDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public ShrubDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(ShrubDTO s) {
        super.insert(s);
    }

    @Override
    public void update(ShrubDTO s) {
        super.update(s);
    }

}
