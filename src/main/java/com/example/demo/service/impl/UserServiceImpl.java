package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.converter.UserConverter;
import com.example.demo.cosnst.UserEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.UserException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.PageDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.PageEntity;
import com.example.demo.model.entity.User;
import com.example.demo.service.api.UserService;

@Service
public class UserServiceImpl implements UserService<UserDTO, UserDTO> {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private UserMapper userMapper;
	
	private SqlSessionFactory sqlSessionFactory;
	
	public UserServiceImpl(UserMapper userMapper, SqlSessionFactory sqlSessionFactory) {
		super();
		this.userMapper = userMapper;
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public UserDTO save(UserDTO DTO) {
		
		if (DTO == null) {
			throw new UserException(UserEnum.PARAM_DATA_NOT_NULL);
		}
		
        User existing = userMapper.findByNo(DTO.getUserNo());
        if (existing != null) {
            throw new BusinessException(UserEnum.USER_already_exists);
        }
        
		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);

		// 修改公共字段
		Date curDate = new Date();
		// FIXME 用户编号生成未完成
		entityPO.setUserNo(StringUtils.substring(UUID.randomUUID().toString().replaceAll("-", ""), 0, 15));
		// FIXME 用户密码生成未完成
		entityPO.setUserPassword(StringUtils.substring(UUID.randomUUID().toString().replaceAll("-", ""), 0, 15));
		// FIXME 盐生 成未完成
		entityPO.setSalt(StringUtils.substring(UUID.randomUUID().toString().replaceAll("-", ""), 0, 8));

		
		entityPO.setRecStatus("0001");
		entityPO.setCrtTime(curDate);
		entityPO.setCrtUser("crtUser");// FIXME 创建用户 成未完成
		entityPO.setLstUpdTime(curDate);
		entityPO.setLstUpdUser("lstUpdUser");// FIXME 最后更新用户 成未完成
		
		userMapper.insert(entityPO);
		
		return UserConverter.INSTANCE.entityToDTO(entityPO);
	}

	@Override
	public UserDTO delete(UserDTO DTO) {

		// 增加用户校验规则: 数据是否存在
		User entityDb = userMapper.findByNo(DTO.getUserNo());
		
		if(null == entityDb) {
			throw new UserException(UserEnum.BUSS_DATA_NON_EXISTENT);
		}
		
		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);
		
		// 修改公共字段
		Date curDate = new Date();

		entityPO.setRecStatus("0002");// FIXME: 删除状态定义化
		entityPO.setCrtTime(null);
		entityPO.setCrtUser(null);
		entityPO.setLstUpdTime(curDate);
//		entityPO.setLstUpdUser(lstUpdUser); // FIXME: 用户功能未实现
		userMapper.delete(entityPO);// FIXME: 软逻辑删除, 修改 recstatus而不是delete
		
		return UserConverter.INSTANCE.entityToDTO(entityPO);
	}

	@Override
	@Transactional
	public int deleteBatch(String[] ids) {
		
		SqlSession sqlSession = null;
		int resultNum = 0;
		try {
			
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			
		    // 获取 Mapper
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			
			// 批量插入
			int batchCount = 1000;//提交数量,到达这个数量就提交
			for (int index = 0; index < ids.length; index++) {
				resultNum ++;
				String obj = ids[index];
				mapper.deleteById(obj);
			    if(index != 0 && index%batchCount == 0){
			        sqlSession.commit();
			    }
			}
		    // 执行批量操作
		    sqlSession.commit();
		} catch (Exception e){
			
			log.error("批量操作失败", e);
			if(sqlSession != null){
				sqlSession.rollback();
			}
			throw new RuntimeException("批量操作失败", e);
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return resultNum;
	}

	@Override
	public UserDTO updateAllField(UserDTO DTO) {
		
		// 增加用户校验规则: 数据是否存在
		User entityDb = userMapper.findByNo(DTO.getUserNo());
		
		if(null == entityDb) {
			throw new UserException(UserEnum.BUSS_DATA_NON_EXISTENT);
		}
		
		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);
		
		// 修改公共字段
		Date curDate = new Date();
//			userPO.setCrtTime(curDate);
//			userPO.setCrtUser(crtUser);
		entityPO.setLstUpdTime(curDate);
//			userPO.setLstUpdUser(lstUpdUser);
		userMapper.updateAllFieldByNo(entityPO);
		
		return UserConverter.INSTANCE.entityToDTO(entityPO);
	}

	@Override
	public UserDTO updateNotNull(UserDTO DTO) {
		
		// 增加用户校验规则: 数据是否存在
		User entityDb = userMapper.findByNo(DTO.getUserNo());
		
		if(null == entityDb) {
			throw new UserException(UserEnum.BUSS_DATA_NON_EXISTENT);
		}
		
		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);
		
		// 修改公共字段
		Date curDate = new Date();
//			userPO.setCrtTime(curDate);
//			userPO.setCrtUser(crtUser);
		entityPO.setLstUpdTime(curDate);
//			userPO.setLstUpdUser(lstUpdUser);
		userMapper.updateNotNullByNo(entityPO);
		
		return UserConverter.INSTANCE.entityToDTO(entityPO);
	}
	
	@Override
	public UserDTO query(UserDTO DTO) {

		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);

		User entityDb = userMapper.findByNo(entityPO.getUserNo());
        if (entityDb == null) {
        	log.warn("数据没有找到: {}", entityPO.getUserNo());
            throw new BusinessException(UserEnum.BUSS_DATA_NON_EXISTENT);
        }
		return UserConverter.INSTANCE.entityToDTO(entityDb);
	}

	@Override
	public List<UserDTO> queryList(UserDTO DTO) {

		// 拷贝数据
		User entityPO = UserConverter.INSTANCE.toEntity(DTO);
		
		List<User> entityPoList = userMapper.queryList(entityPO);
		
		return UserConverter.INSTANCE.entityToDTO(entityPoList);
	}

	@Override
	public PageDTO<UserDTO, UserDTO> queryPageList(PageDTO<UserDTO, UserDTO> pageDTO) {

		// 拷贝数据: 查询参数拷贝
		User entityParam = UserConverter.INSTANCE.toEntity(pageDTO.getParamData());

		Integer nCount = userMapper.findCount(entityParam);

		// 没查到数据
		if (nCount <= 0) {
			pageDTO.setResultData(new ArrayList<UserDTO>(0));
			return pageDTO;
		}
		
		int pageNum = pageDTO.getPageNum();
		int pageSize = pageDTO.getPageSize();
		// 设置行总数
		pageDTO.setRowCount(nCount);
		// 计算出总页数
		int pageCount = (nCount + pageSize - 1) / pageSize;
		pageDTO.setPageCount(pageCount);
		// 当前分页没有数据
		if ((nCount + pageSize) - (pageNum * pageSize) <= 0) {
			throw new UserException(UserEnum.BUSS_DATA_NON_EXISTENT_PAGE);
		}
		
		// 设置分页点: Offset分页点与分页数据分离
		PageEntity pageEntity = new PageEntity(pageNum, pageSize);
		pageEntity.setOffset((pageNum-1) * pageSize);
		
		List<User> pageListDb = userMapper.queryPageList(pageEntity, entityParam);
		// 设置结果数据
		List<UserDTO> pageListDTO = UserConverter.INSTANCE.entityToDTO(pageListDb);
		pageDTO.setResultData(pageListDTO);
		return pageDTO;
	}
}
