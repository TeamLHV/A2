/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.GenomicDTO;
import java.util.List;
import com.eep.datarepository.IGenomicDAO;

/**
 *
 * @author zhongzhu
 */
public class GenomicDAO extends AbstractLeafTechInventoryItemDAO<GenomicDTO> implements IGenomicDAO {

    public GenomicDAO() {
        super(GenomicDTO.class, Constants.DATABASE_LEAFTECH, Constants.TABLE_GENOMIC);
    }

    @Override
    public List<GenomicDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public GenomicDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(GenomicDTO s) {
        super.insert(s);
    }

    @Override
    public void update(GenomicDTO s) {
        super.update(s);
    }

}
