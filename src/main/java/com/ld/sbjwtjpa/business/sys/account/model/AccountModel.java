package com.ld.sbjwtjpa.business.sys.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @Version
    private Long version;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Timestamp getSystimes() {
        return systimes;
    }

    public void setSystimes(Timestamp systimes) {
        this.systimes = systimes;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public AccountModel() {
        super();
    }

    public AccountModel(String uuid, @NotBlank(message = "账户不能为空") @Size(max = 200, message = "账户最大长度为200位") @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账户由大小写字母或数字组成") String account, @NotBlank(message = "密码不能为空") @Size(max = 200, message = "密码最大长度为200位") @Pattern(regexp = "^[A-Za-z0-9]+$", message = "密码由大小写字母或数字组成") String password, Timestamp systimes, String orgId, Long version) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.systimes = systimes;
        this.orgId = orgId;
        this.version = version;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", systimes=" + systimes +
                ", orgId='" + orgId + '\'' +
                ", version=" + version +
                '}';
    }
}
