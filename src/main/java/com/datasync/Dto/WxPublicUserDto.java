package com.datasync.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxPublicUserDto implements Serializable {

    private String id;
    private String wx_open_id;
    private Date create_time;
    private Date delete_time;
    private String account;
    private String is_delete;
    private String is_follow;
    private String villages_id;
    private String plat_number;
    private String company_id;
    private String union_id;
    private String city;
    private String plat_num_colour;
}
