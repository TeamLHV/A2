/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.ReferenceMaterialDTO;
import java.util.List;
import com.eep.datarepository.IReferenceMaterialDAO;

/**
 *
 * @author zhongzhu
 */
public class ReferenceMaterialDAO extends AbstractInventoryItemDAO<ReferenceMaterialDTO> implements IReferenceMaterialDAO {

    public ReferenceMaterialDAO() {
        super(ReferenceMaterialDTO.class, Constants.DATABASE_INVENTORY, Constants.TABLE_REFERENCE_MATERIAL);
    }

    @Override
    public List<ReferenceMaterialDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public ReferenceMaterialDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(ReferenceMaterialDTO s) {
        super.insert(s);
    }

    @Override
    public void update(ReferenceMaterialDTO s) {
        super.update(s);
    }

}
