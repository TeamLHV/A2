/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.CultureBoxDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface ICultureBoxDAO {

    List<CultureBoxDTO> queryAll();

    CultureBoxDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(CultureBoxDTO cb);

    void update(CultureBoxDTO cb);
}
