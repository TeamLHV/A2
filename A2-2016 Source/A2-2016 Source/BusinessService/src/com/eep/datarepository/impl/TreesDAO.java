/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.businessservice.dto.UserInfo;
import com.eep.datarepository.dto.TreeDTO;
import java.util.List;
import com.eep.datarepository.ITreeDAO;

/**
 *
 * @author zhongzhu
 */
public class TreesDAO extends AbstractInventoryItemDAO<TreeDTO> implements ITreeDAO {

    public TreesDAO(UserInfo userInfo) {
        super(TreeDTO.class, Constants.DATABASE_INVENTORY, Constants.TABLE_TREE, userInfo);
    }

    @Override
    public List<TreeDTO> queryAll() {
        return super.queryAll();
    }

    @Override
    public TreeDTO queryByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insert(TreeDTO tree) {
        super.insert(tree);
    }

    @Override
    public void update(TreeDTO tree) {
        super.update(tree);
    }

}
