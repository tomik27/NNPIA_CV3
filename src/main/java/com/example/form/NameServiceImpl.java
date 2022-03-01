package com.example.form;

import org.springframework.stereotype.Service;

@Service
public class NameServiceImpl implements NameService {
    private String names="";

    @Override
    public void add(String name){
        this.names+=name+", ";
    }
    @Override
    public String getString(){
        return names;
    }
}
