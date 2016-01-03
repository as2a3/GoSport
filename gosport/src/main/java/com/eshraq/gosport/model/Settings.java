package com.eshraq.gosport.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ahmed on 12/29/2015.
 */
@DatabaseTable(tableName = "settings")
public class Settings {
    @DatabaseField(id = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
