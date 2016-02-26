/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.TreeDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface ITreesDAO {

    List<TreeDTO> queryAllTrees();

    TreeDTO queryTreesByProductCode(String productCode);

    void deleteTreeByProductCode(String produceCode);

    void insertTree(TreeDTO tree);

    void updateTree(TreeDTO tree);
}
