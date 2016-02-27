/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.impl;

import com.eep.businessservice.IInventoryService;
import com.eep.businessservice.dto.InventoryItemInfo;
import com.eep.datarepository.ICultureBoxDAO;
import com.eep.datarepository.IGenomicDAO;
import com.eep.datarepository.IProcessingDAO;
import com.eep.datarepository.IReferenceMaterialDAO;
import com.eep.datarepository.ISeedDAO;
import com.eep.datarepository.IShrubDAO;
import com.eep.datarepository.ITreeDAO;
import com.eep.datarepository.dto.InventoryItemDTO;
import com.eep.datarepository.dto.SeedDTO;
import com.eep.datarepository.dto.ShrubDTO;
import com.eep.datarepository.dto.TreeDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhongzhu
 */
public class InventoryService implements IInventoryService {

    private final ITreeDAO treeDAO;
    private final IShrubDAO shrubDAO;
    private final ISeedDAO seedDAO;
    private final ICultureBoxDAO cultureBoxDAO;
    private final IGenomicDAO genomicDAO;
    private final IProcessingDAO processingDAO;
    private final IReferenceMaterialDAO referenceMaterialDAO;

    public InventoryService(
            ITreeDAO treeDAO,
            IShrubDAO shrubDAO,
            ISeedDAO seedDAO,
            ICultureBoxDAO cultureBoxDAO,
            IGenomicDAO genomicDAO,
            IProcessingDAO processingDAO,
            IReferenceMaterialDAO referenceMaterialDAO) {
        this.treeDAO = treeDAO;
        this.shrubDAO = shrubDAO;
        this.seedDAO = seedDAO;
        this.cultureBoxDAO = cultureBoxDAO;
        this.genomicDAO = genomicDAO;
        this.processingDAO = processingDAO;
        this.referenceMaterialDAO = referenceMaterialDAO;
    }

    @Override
    public void addTree(InventoryItemInfo info) {
        treeDAO.insert(convertToTree(info));
    }

    private TreeDTO convertToTree(InventoryItemInfo info) {
        TreeDTO dto = new TreeDTO();
        dto.setDescription(info.getDescription());
        dto.setPrice(info.getPrice());
        dto.setProductCode(info.getProductCode());
        dto.setQuantity(info.getQuantity());
        return dto;
    }

    @Override
    public void addShrub(InventoryItemInfo info) {
        shrubDAO.insert(convertToShrub(info));
    }

    private ShrubDTO convertToShrub(InventoryItemInfo info) {
        ShrubDTO dto = new ShrubDTO();
        dto.setDescription(info.getDescription());
        dto.setPrice(info.getPrice());
        dto.setProductCode(info.getProductCode());
        dto.setQuantity(info.getQuantity());
        return dto;
    }

    @Override
    public void addSeed(InventoryItemInfo info) {
        seedDAO.insert(convertToSeed(info));
    }

    private SeedDTO convertToSeed(InventoryItemInfo info) {
        SeedDTO dto = new SeedDTO();
        dto.setDescription(info.getDescription());
        dto.setPrice(info.getPrice());
        dto.setProductCode(info.getProductCode());
        dto.setQuantity(info.getQuantity());
        return dto;
    }

    @Override
    public List<InventoryItemInfo> getAllTrees() {
        List<TreeDTO> l = treeDAO.queryAll();
        List<InventoryItemInfo> result = new ArrayList<>();
        l.stream().forEach((t) -> {
            result.add(convertToInventoryItemInfo(t));
        });
        return result;
    }

    @Override
    public List<InventoryItemInfo> getAllShrubs() {
        List<ShrubDTO> l = shrubDAO.queryAll();
        List<InventoryItemInfo> result = new ArrayList<>();
        l.stream().forEach((t) -> {
            result.add(convertToInventoryItemInfo(t));
        });
        return result;
    }

    @Override
    public List<InventoryItemInfo> getAllSeeds() {
        List<SeedDTO> l = seedDAO.queryAll();
        List<InventoryItemInfo> result = new ArrayList<>();
        l.stream().forEach((t) -> {
            result.add(convertToInventoryItemInfo(t));
        });
        return result;
    }

    private InventoryItemInfo convertToInventoryItemInfo(InventoryItemDTO t) {
        InventoryItemInfo temp = new InventoryItemInfo();
        temp.setDescription(t.getDescription());
        temp.setPrice(t.getPrice());
        temp.setProductCode(t.getProductCode());
        temp.setQuantity(t.getQuantity());
        return temp;
    }
}
