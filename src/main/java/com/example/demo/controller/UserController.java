package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.UserConverter;
import com.example.demo.model.dto.PageDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.vo.PageParamVO;
import com.example.demo.model.vo.PageVO;
import com.example.demo.model.vo.RequestVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;
import com.example.demo.model.vo.user.UserCreateVO;
import com.example.demo.model.vo.user.UserVO;
import com.example.demo.service.api.UserService;
import com.example.demo.validation.Groups;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;

@Tag(name="用户管理", description = "提供用户的操作")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
    private final UserService<UserDTO, UserDTO> userService;
    
	public UserController(UserService<UserDTO, UserDTO> userService) {
		super();
		this.userService = userService;
	}
    
    @Operation(summary = "创建", description = "创建数据")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO<UserCreateVO> save(@Validated(Groups.Create.class) @RequestBody RequestVO<UserCreateVO> requestVO) {
    	log.info("业务处理: 开始; save data={}", requestVO);

		/** 参数处理 */
    	UserCreateVO dataVO = requestVO.getData();
		
    	/** 业务处理 */
    	UserDTO DTO = UserConverter.INSTANCE.toDTO(dataVO);
    	UserDTO savedProductDto = userService.save(DTO);
        return Result.success(UserConverter.INSTANCE.toUserCreateVO(savedProductDto));
    }
    
    @Operation(summary = "删除用户")
    @Parameter(name = "id", example = "TEST00001")
    @DeleteMapping("/{id}")
    public ResponseVO<UserVO> deleteById(@Validated(Groups.Delete.class) @NotBlank @PathVariable(name = "id") String id) {
    	log.info("处理: 开始; UserController: delete data={}", id);
    	
		// 构建service dto
		UserDTO paramDTO = new UserDTO();
		paramDTO.setUserNo(id);
		
		// 执行任务
    	userService.delete(paramDTO);
    	
		/** 控制器路由 */
		return Result.success(null);
    }
    
    @Operation(summary = "修改用户: 非空字段更新")
    @PutMapping("/{id}")
    public ResponseVO<UserVO> update(
    		@Parameter(name = "id", example = "TEST00001" ,required = true)
    		@PathVariable String id, @Validated(Groups.Update.class) @RequestBody UserVO userVO) {
        log.info("Updating users with id: {}, data: {}", id, userVO);
        
        // 构建service dto
        UserDTO userDTO = UserConverter.INSTANCE.toDTO(userVO);
        userDTO.setUserNo(id);
        
        // 执行任务
        UserDTO updatedUserDTO = userService.updateNotNull(userDTO);
        return Result.success(UserConverter.INSTANCE.toVO(updatedUserDTO));
    }
    
    @Operation(summary = "修改用户: 强制更新所有可改字段")
    @PutMapping
    public ResponseVO<UserVO> updateAllField(@Validated(Groups.Update.class) @RequestBody UserVO userVO) {
        log.info("Updating users with id: {}, data: {}", userVO);
        
        // 构建service dto
        UserDTO userDTO = UserConverter.INSTANCE.toDTO(userVO);
        
        // 执行任务
        UserDTO updatedUserDTO = userService.updateAllField(userDTO);
        return Result.success(UserConverter.INSTANCE.toVO(updatedUserDTO));
    }
    /**
     * 查询: 通过编号
     * @param id
     * @return
     */
    @Operation(summary = "查询用户: 编号")
    @Parameter(name = "id", example = "TEST00001")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseVO<UserVO> queryByNo(@Validated(Groups.Query.class) @NotBlank @PathVariable(name = "id") String id) {
		log.info("处理: 开始; UserController: queryByUserNo data={}", id);

		/** 业务处理 */
		// 构建service dto
		UserDTO paramDTO = new UserDTO();

		// 拷贝数据 vo->dto
		paramDTO.setUserNo(id);

		UserDTO userDbDTO = userService.query(paramDTO);
		
		/** 控制器路由 */
		return Result.success(UserConverter.INSTANCE.toVO(userDbDTO));
	}
    
	/**
	 * 通过条件查询用户
	 * @param requestVO
	 * @return
	 */
    @Operation(summary = "查询用户: 条件查询")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseVO<List<UserVO>> queryUser(@Validated(Groups.Query.class) @RequestBody UserVO requestVO) {
		log.info("处理: 开始; UserController: queryUser data={}", requestVO);

		/** 业务处理 */

		// 构建service dto

		// 拷贝数据 vo->dto
		UserDTO paramDTO = UserConverter.INSTANCE.toDTO(requestVO);

		List<UserDTO> resultDTO = userService.queryList(paramDTO);
		
		/** 控制器路由 */
		return Result.success(UserConverter.INSTANCE.toVO(resultDTO));
	}
	
    @Operation(summary = "查询: 分页")
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public ResponseVO<PageVO<UserVO, UserVO>> queryPage( @Validated(Groups.QueryPage.class) @RequestBody PageParamVO<UserVO> requestVO) {

		log.info("业务处理: 开始; UserController: queryPage data={}", requestVO);
//		if(1==1) {
//			throw new UserException( UserEnum.FILE_v_0001);
//		}

		/** 业务处理 */
		// 返回的page vo
		
		// 1. 分页参数处理
		int pageNum = requestVO.getPageNum();
		int pageSize = requestVO.getPageSize();
		PageDTO<UserDTO, UserDTO> pageDTO = new PageDTO<UserDTO, UserDTO>(pageNum, pageSize);

		// 2. 参数处理
		// 构建service dto
		// 拷贝数据 vo->dto
		UserDTO paramDTO = UserConverter.INSTANCE.toDTO(requestVO.getParamData());
		pageDTO.setParamData(paramDTO);
		
		// 3. 查询
		PageDTO<UserDTO, UserDTO> pageResultDTO = userService.queryPageList(pageDTO);
		
		// 结果转换
		/** 控制器路由 */
		return Result.success(UserConverter.INSTANCE.toPageVO(pageResultDTO));
	}
}
