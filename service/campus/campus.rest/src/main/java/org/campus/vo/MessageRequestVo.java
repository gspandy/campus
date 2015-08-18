package org.campus.vo;
import org.springframework.data.domain.Pageable;

/**
 * 
 * 会话列表请求参数封装对象
 *
 * @author dengzhi
 *
 */
public class MessageRequestVo {
    // 要查询的用户id
    private String userId;
    
    // 是否已读(0:未读;1:已读)
    private String isRead;
    
    // 分页信息,传参方式：?page=0&size=10
    private Pageable pageable;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
