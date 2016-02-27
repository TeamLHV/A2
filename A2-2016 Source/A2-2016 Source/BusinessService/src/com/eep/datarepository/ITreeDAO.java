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
public interface ITreeDAO {

    List<TreeDTO> queryAll();

    TreeDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(TreeDTO tree);

    void update(TreeDTO tree);
}
