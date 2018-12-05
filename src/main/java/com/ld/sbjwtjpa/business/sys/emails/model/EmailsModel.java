package com.ld.sbjwtjpa.business.sys.emails.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ApiModel(value = "EmailsModel", description = "邮件表")
@Entity(name = "emails_table")
@Table(comment = "邮件表", appliesTo = "emails_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonComponent
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailsModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    /**
     * 收件人
     */
    @ApiModelProperty(name = "emToPeople", dataType = "String", required = true, allowableValues = "收件人")
    @NotBlank(message = "收件人不能为空")
    @Size(max = 1000, message = "收件人最大长度为1000位")
    @Column(name = "em_to_people", nullable = false, columnDefinition = "text(1000) COMMENT '收件人'")
    private String emToPeople;

    /**
     * 邮件标题
     */
    @ApiModelProperty(name = "emTitles", dataType = "String", required = true, allowableValues = "邮件标题")
    @NotBlank(message = "邮件标题名称不能为空")
    @Size(max = 100, message = "邮件标题名称最大长度为100位")
    @Column(name = "em_titles", nullable = false, columnDefinition = "varchar(200) COMMENT '收件人'")
    private String emTitles;

    /**
     * 邮件内容
     */
    @ApiModelProperty(name = "emBodys", dataType = "String", required = true, allowableValues = "邮件内容")
    @NotBlank(message = "邮件内容不能为空")
    @Size(max = 1000, message = "邮件内容最大长度为1000位")
    @Column(name = "em_bodys", nullable = false, columnDefinition = "text(1000) COMMENT '邮件内容'")
    private String emBodys;


}
