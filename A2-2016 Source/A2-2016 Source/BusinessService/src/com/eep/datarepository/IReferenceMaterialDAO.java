/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.ReferenceMaterialDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IReferenceMaterialDAO {

    List<ReferenceMaterialDTO> queryAll();

    ReferenceMaterialDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(ReferenceMaterialDTO rm);

    void update(ReferenceMaterialDTO rm);
}
