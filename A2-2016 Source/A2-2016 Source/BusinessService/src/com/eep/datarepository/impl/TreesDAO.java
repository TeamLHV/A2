/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository.impl;

import com.eep.datarepository.dto.TreeDTO;
import java.util.List;
import com.eep.datarepository.ITreesDAO;

/**
 *
 * @author zhongzhu
 */
public class TreesDAO extends AbstractDAO<TreeDTO> implements ITreesDAO {

    public TreesDAO() {
        super(TreeDTO.class, "inventory", "trees");
    }

    @Override
    public List<TreeDTO> queryAllTrees() {
        return super.queryAll();
    }

    @Override
    public TreeDTO queryTreesByProductCode(String productCode) {
        return super.queryByCode(productCode);
    }

    @Override
    public void deleteTreeByProductCode(String productCode) {
        super.deleteByProductCode(productCode);
    }

    @Override
    public void insertTree(TreeDTO tree) {
        super.insert(tree);
    }

    @Override
    public void updateTree(TreeDTO tree) {
        super.update(tree);
    }

}
