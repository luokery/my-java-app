package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.converter.ProductConverter;
import com.example.demo.converter.UserConverter;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.vo.RequestVO;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;
import com.example.demo.model.vo.product.ProductVO;
import com.example.demo.model.vo.user.UserVO;
import com.example.demo.service.api.UserService;
import com.example.demo.validation.Groups;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
    private final UserService userService;
    private final UserConverter userConverter;
    
	public UserController(UserService userService, UserConverter userConverter) {
		super();
		this.userService = userService;
		this.userConverter = userConverter;
	}
    
    @Operation(summary = "创建")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO<UserVO> save(@Validated(Groups.Create.class) @RequestBody RequestVO<UserVO> requestVO) {
    	log.info("业务处理: 开始; save data={}", requestVO);
        
    	UserVO dataVO = null;

		/** 参数处理 */
    	dataVO = requestVO.getData();
		
    	/** 业务处理 */
    	UserDTO DTO = UserConverter.INSTANCE.toDTO(dataVO);
    	UserDTO savedProductDto = userService.save(DTO);
        return Result.success(UserConverter.INSTANCE.toVO(savedProductDto));
    }
    @GetMapping
    public ResponseVO<List<UserVO>> findAll() {
        log.info("Finding all user");
        UserDTO DTO = new UserDTO();
        
        List<UserDTO> productDtos = userService.queryList(DTO);
        List<UserVO> productVos = productDtos.stream()
                .map(UserConverter.INSTANCE::toVO)
                .collect(Collectors.toList());
        return Result.success(productVos);
    }
}
