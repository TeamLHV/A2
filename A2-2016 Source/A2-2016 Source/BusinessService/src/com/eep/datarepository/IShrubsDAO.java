/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.ShrubsDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IShrubsDAO {

    List<ShrubsDTO> queryAllShrubs();

    ShrubsDTO queryShrubsByProductCode(String productCode);

    void deleteShrubByProductCode(String produceCode);

    void insertShrub(ShrubsDTO shrub);

    void updateShrub(ShrubsDTO shrub);
}
