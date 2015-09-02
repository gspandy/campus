package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserVO", description = "用户信息")
public class UserVO {

    private String userId;

    private String nickName;

    private String headPic;

    private String signature;

    private String qrcode;

    private int postCount;

    private int fansCount;

    private int attentionCount;

    private boolean isAttention = false;

    private long integral;

    private String schoolId;

    private String schoolName;

    private String collegeId;

    private String collegeName;

    private String professionId;

    private String professionName;

    private int inSchollYear;

    private String signName;

    @ApiModelProperty(value = "用户Id", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "用户Id", required = true)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "头像", required = false)
    public String getHeadPic() {
        return headPic;
    }

    @ApiModelProperty(value = "头像", required = false)
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @ApiModelProperty(value = "个人介绍", required = false)
    public String getSignature() {
        return signature;
    }

    @ApiModelProperty(value = "个人介绍", required = false)
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @ApiModelProperty(value = "二维码地址", required = false)
    public String getQrcode() {
        return qrcode;
    }

    @ApiModelProperty(value = "二维码地址", required = false)
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @ApiModelProperty(value = "发帖数", required = true)
    public int getPostCount() {
        return postCount;
    }

    @ApiModelProperty(value = "发帖数", required = true)
    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public int getFansCount() {
        return fansCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public int getAttentionCount() {
        return attentionCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    @ApiModelProperty(value = "是否关注", required = true)
    public boolean isAttention() {
        return isAttention;
    }

    @ApiModelProperty(value = "是否关注", required = true)
    public void setAttention(boolean isAttention) {
        this.isAttention = isAttention;
    }

    @ApiModelProperty(value = "积分")
    public long getIntegral() {
        return integral;
    }

    @ApiModelProperty(value = "积分")
    public void setIntegral(long integral) {
        this.integral = integral;
    }

    @ApiModelProperty(value = "学校ID")
    public String getSchoolId() {
        return schoolId;
    }

    @ApiModelProperty(value = "学校ID")
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @ApiModelProperty(value = "学校名称")
    public String getSchoolName() {
        return schoolName;
    }

    @ApiModelProperty(value = "学校名称")
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @ApiModelProperty(value = "院系ID")
    public String getCollegeId() {
        return collegeId;
    }

    @ApiModelProperty(value = "院系ID")
    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @ApiModelProperty(value = "院系名称")
    public String getCollegeName() {
        return collegeName;
    }

    @ApiModelProperty(value = "院系名称")
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @ApiModelProperty(value = "专业ID")
    public String getProfessionId() {
        return professionId;
    }

    @ApiModelProperty(value = "专业ID")
    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @ApiModelProperty(value = "专业名称")
    public String getProfessionName() {
        return professionName;
    }

    @ApiModelProperty(value = "专业名称")
    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    @ApiModelProperty(value = "入学年份")
    public int getInSchollYear() {
        return inSchollYear;
    }

    @ApiModelProperty(value = "入学年份")
    public void setInSchollYear(int inSchollYear) {
        this.inSchollYear = inSchollYear;
    }

    @ApiModelProperty(value = "个人签名")
    public String getSignName() {
        return signName;
    }

    @ApiModelProperty(value = "个人签名")
    public void setSignName(String signName) {
        this.signName = signName;
    }

}
