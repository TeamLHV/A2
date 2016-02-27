/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.ProcessingDTO;
import java.util.List;
import com.eep.datarepository.IProcessingDAO;

/**
 *
 * @author zhongzhu
 */
public class ProcessingDAO extends AbstractInventoryItemDAO<ProcessingDTO> implements IProcessingDAO {

    public ProcessingDAO() {
        super(ProcessingDTO.class, Constants.DATABASE_INVENTORY, Constants.TABLE_PROCESSING);
    }

    @Override
    public List<ProcessingDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public ProcessingDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(ProcessingDTO s) {
        super.insert(s);
    }

    @Override
    public void update(ProcessingDTO s) {
        super.update(s);
    }

}
