package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.constant.HttpResponseConstants;
import com.project_management.shoppingweb.dao.model.*;
import com.project_management.shoppingweb.dao.vo.CreditVO;
import com.project_management.shoppingweb.dao.vo.RequestResultVO;
import com.project_management.shoppingweb.dao.vo.UserVO;
import com.project_management.shoppingweb.repository.AssetRepository;
import com.project_management.shoppingweb.repository.CardRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.UserInformationService;
import com.project_management.shoppingweb.service.UserService;
import com.project_management.shoppingweb.service.common.ResultBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userInformation")
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AssetRepository assetRepository;

    /**
     * 查询个人信息
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Object getById(@RequestBody User user1){
        int userId = user1.getUserId();
        User user = userInformationService.findByUserId(userId);
        return ResultBuilder.buildSuccessResult(user);
    }

    /**
     * 查询所有信息
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public Object getAll(@RequestBody PageModel pageModel){
        RequestResultVO requestResultVO = ResultBuilder.buildSuccessResult();
        //传入分页参数则调用分页接口
        if(pageModel.getPageNow()!=null && pageModel.getPageSize()!=null){
            Page<User> deBuys = userInformationService.findAll(pageModel.getPageNow()-1,pageModel.getPageSize());
            requestResultVO.setData(deBuys);
        }else{  //否则调用普通接口
            List<User> deBuys = userInformationService.findAll();
            requestResultVO.setData(deBuys);
        }

        return requestResultVO;
    }

    /**
     * 更新个人信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody User user){
        userInformationService.updateUser(user);
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    /**
     * 查询个人征信信息
     */
    @RequestMapping(value = "/getPersonCredit", method = RequestMethod.POST)
    public Object getPersonCredit(@RequestBody User user1){
        int userId = user1.getUserId();
        User user = userInformationService.findByUserId(userId);
        CreditVO creditVO = new CreditVO();

        List<Card> cards = cardRepository.findByUserId(userId);
        List<Asset> assets = assetRepository.findByUserId(userId);
        BeanUtils.copyProperties(user,creditVO);
        creditVO.setCards(cards);
        creditVO.setAssets(assets);
        return ResultBuilder.buildSuccessResult(creditVO);
    }

    /**
     * 设置个人期望征信级别
     */
    @RequestMapping(value = "/setExpectedCredit", method = RequestMethod.POST)
    public Object setExpectedCredit(@RequestBody User user1){
        userInformationService.updateUser(user1);
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    /**
     * 同意修改个人征信级别
     */
    @RequestMapping(value = "/changeCreditYes", method = RequestMethod.POST)
    public Object changeCreditYes(@RequestBody User user1){
        int userId = user1.getUserId();
        User user = userInformationService.findByUserId(userId);
        user.setCredit(user.getExpectedCredit());
        user.setExpectedCredit(null);
        userInformationService.updateUser(user);
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    /**
     * 拒绝修改个人征信级别
     */
    @RequestMapping(value = "/changeCreditNo", method = RequestMethod.POST)
    public Object changeCreditNo(@RequestBody User user1){
        int userId = user1.getUserId();
        User user = userInformationService.findByUserId(userId);
        user.setExpectedCredit(null);
        userInformationService.updateUser(user);
        RequestResultVO requestResultVO = new RequestResultVO();
        requestResultVO.setCode(HttpResponseConstants.Public.SUCCESS_CODE);
        requestResultVO.setMessage(HttpResponseConstants.Public.SUCCESS_300);
        return requestResultVO;
    }

    @RequestMapping(value = "/getChangeCredit", method = RequestMethod.POST)
    public Object getChangeCredit(@RequestBody PageModel pageModel){
        List<User> user1 = userRepository.findByExpectedCredit(1);
        List<User> user2 = userRepository.findByExpectedCredit(2);
        List<User> user3 = userRepository.findByExpectedCredit(3);
        List<User> users = new ArrayList<>();
        for(User user : user1){
            users.add(user);
        }
        for(User user : user2){
            users.add(user);
        }
        for(User user : user3){
            users.add(user);
        }

        return ResultBuilder.buildSuccessResult(users);
    }


}
