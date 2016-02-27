/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.ShrubDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IShrubDAO {

    List<ShrubDTO> queryAll();

    ShrubDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(ShrubDTO shrub);

    void update(ShrubDTO shrub);
}
