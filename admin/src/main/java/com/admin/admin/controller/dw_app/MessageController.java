package com.admin.admin.controller.dw_app;


import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.service.dw_app.MessageService;
import com.admin.admin.service.dw_task.Tasking;
import com.admin.timer.Datatimer;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private Tasking tasking;

    private ResponseResult result = new ResponseResult();
    //  private TokenUtil tokenUtil =new TokenUtil();


    @GetMapping("/Get")
    public ResponseResult getNotificationList(String UserId) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(messageService.getNotificationList(UserId));
    }
    @ApiOperation("测试")
    @GetMapping("/Gettest")
    public List<TMessage> test() throws Exception{

        tasking.GetMessageList(5);

        return  tasking.GetMessageList(5);
    }
}
