package com.ld.sbjwtjpa.business.sys.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Entity(name = "admin_table")
@Table(comment = "管理员表", appliesTo = "admin_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Override
    public String toString() {
        return "AdminModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", version=" + version +
                '}';
    }
}
