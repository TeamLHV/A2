/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.SeedDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface ISeedDAO {

    List<SeedDTO> queryAll();

    SeedDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(SeedDTO seed);

    void update(SeedDTO seed);
}
