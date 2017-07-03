package com.cocshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nguyen Cong Chinh on 6/26/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblWrapper implements Serializable {

    List<TblWrapper> list;


    public List<TblWrapper> getList() {
        return list;
    }

    public void setList(List<TblWrapper> list) {
        this.list = list;
    }


}
