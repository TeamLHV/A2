/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.datarepository;

import com.eep.datarepository.dto.GenomicDTO;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public interface IGenomicDAO {

    List<GenomicDTO> queryAll();

    GenomicDTO queryByProductCode(String productCode);

    void deleteByProductCode(String produceCode);

    void insert(GenomicDTO g);

    void update(GenomicDTO g);
}
