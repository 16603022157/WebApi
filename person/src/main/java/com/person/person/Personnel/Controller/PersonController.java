package com.person.person.Personnel.Controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.Personinformation;
import com.person.person.Personnel.Service.PersoinfoService;
import com.person.person.model.ParamterModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "人员信息")
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersoinfoService persoinfoService;

    private ResponseResult result=new ResponseResult();

    //新增
    @PostMapping("/Persons") //另外不要这种写法，你很难调试的
    public ResponseResult AddPersion(@RequestBody Personinformation personinformation){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.Addpersion(personinformation));
    }
    //修改
    @PostMapping("/UpdatePersion")
    public ResponseResult UpdatePersion(@RequestBody(required = false) Personinformation personinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.Updatepersion(personinformation));
    }
    //删除
    @PostMapping("/DelPersion")
    public ResponseResult DelPersion(@RequestBody(required = false) ParamterModel paramterModel,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.Delpersion(paramterModel));
    }

    //获取
    @GetMapping("/GetPersoin")
    public ResponseResult GetPersoin(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(persoinfoService.Getpersoin(id));
    }
}
