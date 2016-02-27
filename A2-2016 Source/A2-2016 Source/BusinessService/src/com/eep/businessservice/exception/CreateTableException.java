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
public class CreateTableException extends RuntimeException {

    public CreateTableException(String errorMessage) {
        super(errorMessage);
    }
}
