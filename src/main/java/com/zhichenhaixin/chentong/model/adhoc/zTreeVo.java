package com.zhichenhaixin.chentong.model.adhoc;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * zTree简单数据格式实体类
 * @version V1.0
 * @author pwl
 * @date 2019年5月9日
 * @Description 
 */
@Setter
@Getter
public class zTreeVo implements Serializable {
    
	private static final long serialVersionUID = -94575741654L;
	private String id;
    private String pId;
    private String name;
}
