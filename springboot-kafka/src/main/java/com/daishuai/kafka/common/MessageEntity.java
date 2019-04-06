package com.daishuai.kafka.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/3/9 17:30
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Setter
@Getter
public class MessageEntity {

    private String id;

    private Object data;
}
