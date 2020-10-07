package com.ouyangwei.multimodule.dao.mappers;

import com.ouyangwei.multimodule.dao.entities.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ouyangwei
 * @since 2020-10-07
 */
public interface UserMapper extends BaseMapper<User> {
    User getOuyangwei();
}
