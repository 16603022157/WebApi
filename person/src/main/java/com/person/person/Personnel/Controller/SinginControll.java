package com.person.person.Personnel.Controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Service.SinginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Singin")
public class SinginControll {
    @Autowired
    private SinginService singinService;
    private ResponseResult result = new ResponseResult();

    @GetMapping("/GetSingin")
    public ResponseResult GetSingin(@RequestParam(required = false) int person_id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(singinService.GetSingin(person_id));
    }
}