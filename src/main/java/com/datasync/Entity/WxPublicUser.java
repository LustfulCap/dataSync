package com.datasync.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxPublicUser implements Serializable {

    private String id;
    private String wxOpenId;
    private Date createTime;
    private Date deleteTime;
    private String account;
    private int isDelete;
    private int isFollow;
    private String villagesId;
    private String platNumber;
    private String companyId;
    private String unionId;
    private String city;
    private String platNumColour;
}
