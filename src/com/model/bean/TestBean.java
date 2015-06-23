package com.model.bean;

import com.model.util.BaseEJB;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by tseegii on 6/23/15.
 */
@LocalBean
@Stateless
public class TestBean extends BaseEJB {

    public void findAll() {
        System.out.println("findall");
    }
}
