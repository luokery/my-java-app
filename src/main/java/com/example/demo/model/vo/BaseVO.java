package com.example.demo.model.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * CamelCase策略,Java对象属性:personId,序列化后属性:persionId
 * PascalCase策略，Java对象属性：personId，序列化后属性：PersonId
 * SnakeCase策略，Java对象属性：personId，序列化后属性：person_id
 * KebabCase策略，Java对象属性：personId，序列化后属性：person-id
 * @author 
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class BaseVO {

}
