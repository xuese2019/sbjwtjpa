package com.ld.sbjwtjpa.business.sys.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Entity(name = "admin_table")
@Table(comment = "管理员表", appliesTo = "admin_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @Column(name = "account", nullable = false)
    private String account;
    @NotBlank(message = "管理员密码不能为空")
    @Size(max = 200, message = "管理员密码最大长度为200位")
    @Pattern(regexp = "/^[A-Za-z0-9]+$/", message = "密码由大小写字母或数字组成")
    @Column(name = "password", nullable = false)
    private String password;

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

    public AdminModel() {
        super();
    }

    public AdminModel(String uuid, String account, @NotBlank(message = "管理员密码不能为空") @Size(max = 200, message = "管理员密码最大长度为200位") @Pattern(regexp = "/^[A-Za-z0-9]+$/", message = "密码由大小写字母或数字组成") String password, Long version) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.version = version;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
