package com.prisonapp.business.dao.dw_message;

import com.prisonapp.business.entity.dw_message.MessageListModel;
import com.prisonapp.business.entity.dw_message.MessageModel;
import com.prisonapp.business.entity.dw_message.NotificationMessageModel;
import com.prisonapp.business.entity.dw_message.SearchNotificationModel;
import com.prisonapp.business.entity.dw_supervise.FaceRecognizeModel;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageDao {
    List<NotificationMessageModel> getNotificationList(@Param("userId") String userId );

    List<MessageListModel> getAllMessageList( @Param("count") int  count, @Param("requestCount") int requestCount, @Param("key") String key, @Param("userId") String userId);

    List<MessageListModel> getMessageList(@Param("type")int  type, @Param("count") int  count, @Param("requestCount") int requestCount, @Param("key") String key, @Param("userId") String userId);

    int readMessage(@Param("type")int  type, @Param("messageTimestamp") Date messageTimestamp, @Param("userId")String userId );

    List<SearchNotificationModel> searchNotification(@Param("key")String key , @Param("userId")String userId);

    List<MessageModel> unreadCount(@Param("type")int  type,@Param("userId")String userId);

    List<MessageModel> messageTotalCount(@Param("type")int  type,@Param("userId")String userId);

    List<MessageModel> messageAllTotalCount(@Param("userId")String userId);


    List<MessageListModel> getNewestMessageList(@Param("count")int count,@Param("requestCount")int requestCount, @Param("todayDate")String  todayDate,@Param("tomorrowDate")String tomorrowDate,@Param("userId")String userId);

    List<MessageListModel> newestMessageTotalCount(@Param("todayDate")String  todayDate,@Param("tomorrowDate")String tomorrowDate,@Param("userId")String userId);

    NotificationMessageModel getNotification(@Param("userId")String userId,@Param("type")int  type);

    TPersoninformation RelatedId(@Param("accountName")String accountName);
}
