package com.ld.sbjwtjpa.business.user.people.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 */
@ApiModel(value = "PeopleModel", description = "员工信息补充表，也是员工具体信息表，同账户表有数据关联")
@Entity(name = "people_table")
@Table(comment = "员工信息补充表", appliesTo = "people_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//jackjson 序列化忽略不存在的字段
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(value = "账号主键", name = "accId", dataType = "String", allowableValues = "账号主键")
    @NotBlank(message = "账号主键不能为空")
    @Size(max = 200, message = "账号主键最大长度为200位")
    @Column(name = "accId")
    private String accId;

    @ApiModelProperty(value = "姓名", name = "name", dataType = "String", allowableValues = "姓名")
    @Size(max = 200, message = "姓名最大长度为200位")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "性别", name = "sex", dataType = "String", allowableValues = "性别")
    @Size(max = 1, message = "性别最大长度为1位")
    @Column(name = "sex")
    private String sex;

    @ApiModelProperty(value = "国家", name = "country", dataType = "String", allowableValues = "国家")
    @Size(max = 200, message = "国家最大长度为200位")
    @Column(name = "country")
    private String country;

    @ApiModelProperty(value = "身份证", name = "idCody", dataType = "String", allowableValues = "身份证")
    @Size(max = 200, message = "身份证最大长度为200位")
    @Column(name = "id_cody")
    private String idCody;

    @ApiModelProperty(value = "籍贯省", name = "nativePlaceProvince", dataType = "String", allowableValues = "籍贯省")
    @Size(max = 200, message = "籍贯省最大长度为200位")
    @Column(name = "native_place_province")
    private String nativePlaceProvince;

    @ApiModelProperty(value = "籍贯市", name = "nativePlaceCity", dataType = "String", allowableValues = "籍贯市")
    @Size(max = 200, message = "籍贯市最大长度为200位")
    @Column(name = "native_place_city")
    private String nativePlaceCity;

    @ApiModelProperty(value = "籍贯区/县", name = "nativePlaceCount", dataType = "String", allowableValues = "籍贯区/县")
    @Size(max = 200, message = "籍贯区/县最大长度为200位")
    @Column(name = "native_place_count")
    private String nativePlaceCount;

    @ApiModelProperty(value = "籍贯详细地址", name = "nativePlaceAddress", dataType = "String", allowableValues = "籍贯详细地址")
    @Size(max = 200, message = "籍贯详细地址最大长度为200位")
    @Column(name = "native_place_address")
    private String nativePlaceAddress;

    @ApiModelProperty(value = "现住省", name = "currentResidenceProvince", dataType = "String", allowableValues = "现住省")
    @Size(max = 200, message = "现住省最大长度为200位")
    @Column(name = "current_residence_province")
    private String currentResidenceProvince;

    @ApiModelProperty(value = "现住市", name = "currentResidenceCity", dataType = "String", allowableValues = "现住市")
    @Size(max = 200, message = "现住市最大长度为200位")
    @Column(name = "current_residence_city")
    private String currentResidenceCity;

    @ApiModelProperty(value = "现住区/县", name = "currentResidenceCount", dataType = "String", allowableValues = "现住区/县")
    @Size(max = 200, message = "现住区/县最大长度为200位")
    @Column(name = "current_residence_count")
    private String currentResidenceCount;

    @ApiModelProperty(value = "现住详细地址", name = "currentResidenceAddress", dataType = "String", allowableValues = "现住详细地址")
    @Size(max = 200, message = "现住详细地址最大长度为200位")
    @Column(name = "current_residence_address")
    private String currentResidenceAddress;

    @ApiModelProperty(value = "电话", name = "phone", dataType = "String", allowableValues = "电话")
    @Size(max = 100, message = "电话最大长度为100位")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value = "紧急联系人", name = "emergencyContact", dataType = "String", allowableValues = "紧急联系人")
    @Size(max = 200, message = "紧急联系人最大长度为200位")
    @Column(name = "emergency_contact")
    private String emergencyContact;

    @ApiModelProperty(value = "紧急联系电话", name = "emergencyContactNumber", dataType = "String", allowableValues = "紧急联系电话")
    @Size(max = 200, message = "紧急联系电话最大长度为200位")
    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;

    @ApiModelProperty(value = "入职日期", name = "dateOfEntry", dataType = "Date", allowableValues = "入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "date_of_entry")
    private Date dateOfEntry;

    @ApiModelProperty(value = "离职日期", name = "leaveDate", dataType = "Date", allowableValues = "离职日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "leave_date")
    private Date leaveDate;

    @ApiModelProperty(value = "工龄", name = "workingYears", dataType = "int", allowableValues = "工龄")
    @Range(min = -1, max = 100, message = "工龄的值应该在-1-100之间")
    @Digits(integer = 99, fraction = 0, message = "工龄为两位的正整数")
    @Column(name = "working_years")
    private int workingYears;

    @ApiModelProperty(value = "专业职称", name = "professionalTitle", dataType = "String", allowableValues = "专业职称")
    @Size(max = 200, message = "专业职称最大长度为200位")
    @Column(name = "professional_title")
    private int professionalTitle;

    @ApiModelProperty(value = "婚姻状况", name = "marital", dataType = "String", allowableValues = "婚姻状况")
    @Size(max = 1, message = "婚姻状况最大长度为1位")
    @Column(name = "marital", length = 1)
    private String marital;

    @ApiModelProperty(value = "政治面貌", name = "political", dataType = "String", allowableValues = "政治面貌")
    @Size(max = 1, message = "政治面貌最大长度为1位")
    @Column(name = "political", length = 1)
    private String political;

    @ApiModelProperty(value = "微信", name = "weChat", dataType = "String", allowableValues = "微信")
    @Size(max = 100, message = "微信最大长度为100位")
    @Column(name = "we_chat")
    private String weChat;

    @ApiModelProperty(value = "QQ", name = "qq", dataType = "String", allowableValues = "QQ")
    @Size(max = 100, message = "微信最大长度为100位")
    @Column(name = "qq")
    private String qq;

    @ApiModelProperty(value = "钉钉", name = "dd", dataType = "String", allowableValues = "钉钉")
    @Size(max = 100, message = "钉钉最大长度为100位")
    @Column(name = "dd")
    private String dd;

    @ApiModelProperty(value = "电子邮箱", name = "email", dataType = "String", allowableValues = "电子邮箱")
    @Size(max = 100, message = "电子邮箱最大长度为100位")
    @Email(message = "不是标准的邮箱格式")
    @Column(name = "email")
    private String email;

    @Version
    private Long version;

}
