/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eep.businessservice.exception;

/**
 *
 * @author zhongzhu
 */
public class DropTableException extends RuntimeException {

    public DropTableException(String errorMessage) {
        super(errorMessage);
    }
}
