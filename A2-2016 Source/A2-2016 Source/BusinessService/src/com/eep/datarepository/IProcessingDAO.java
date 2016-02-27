/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.ProcessingDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IProcessingDAO {

    List<ProcessingDTO> queryAll();

    ProcessingDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(ProcessingDTO p);

    void update(ProcessingDTO p);
}
