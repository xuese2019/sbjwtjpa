package com.ld.sbjwtjpa.business.sys.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author ld
 * @name
 * @table
 */
@ApiModel(value = "AccountModel", description = "账户表")
@Entity(name = "account_table")
@Table(comment = "账户表", appliesTo = "account_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(value = "", name = "account", dataType = "String", required = true, allowableValues = "账号")
    @NotBlank(message = "账户不能为空")
    @Size(max = 200, message = "账户最大长度为200位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账户由大小写字母或数字组成")
    @Column(name = "account", nullable = false)
    private String account;

    @ApiModelProperty(value = "密码", name = "password", dataType = "String", required = true, allowableValues = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(max = 200, message = "密码最大长度为200位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码由大小写字母或数字组成")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "systimes", nullable = false)
    private Timestamp systimes;

    @Column(name = "org_id")
    private String orgId;

    @ApiModelProperty(value = "邮箱", name = "emails", dataType = "String", allowableValues = "邮箱")
    @Size(max = 200, message = "邮箱最大长度为200位")
    @Column(name = "emails")
    private String emails;

    @ApiModelProperty(value = "邮箱授权码", name = "emailsAuthorizationCode", dataType = "String", allowableValues = "邮箱授权码")
    @Size(max = 200, message = "邮箱授权码最大长度为200位")
    @Column(name = "emails_authorization_code")
    private String emailsAuthorizationCode;

    @ApiModelProperty(value = "邮箱smt地址", name = "emailsHost", dataType = "String", allowableValues = "邮箱smt地址")
    @Column(name = "emails_host")
    private String emailsHost;

    @ApiModelProperty(value = "邮箱imap地址", name = "emailsImap", dataType = "String", allowableValues = "邮箱imap地址")
    @Column(name = "emails_imap")
    private String emailsImap;

    @Version
    private Long version;

}
