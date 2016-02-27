/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.SeedDTO;
import java.util.List;
import com.eep.datarepository.ISeedDAO;

/**
 *
 * @author zhongzhu
 */
public class SeedDAO extends AbstractInventoryItemDAO<SeedDTO> implements ISeedDAO {

    public SeedDAO() {
        super(SeedDTO.class, Constants.DATABASE_INVENTORY, Constants.TABLE_SEED);
    }

    @Override
    public List<SeedDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public SeedDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(SeedDTO s) {
        super.insert(s);
    }

    @Override
    public void update(SeedDTO s) {
        super.update(s);
    }

}
