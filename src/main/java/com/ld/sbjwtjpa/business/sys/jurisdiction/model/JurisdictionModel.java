package com.ld.sbjwtjpa.business.sys.jurisdiction.model;

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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ApiModel(value = "JurisdictionModel", description = "权限基础表")
@Entity(name = "jurisdiction_table")
@Table(comment = "权限表", appliesTo = "jurisdiction_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称", name = "jurName", dataType = "String", required = true, allowableValues = "权限名称")
    @NotBlank(message = "权限名称名称不能为空")
    @Size(max = 200, message = "权限名称名称最大长度为200位")
    @Column(name = "jur_name", nullable = false)
    private String jurName;

    /**
     * 权限父级
     */
    @ApiModelProperty(value = "权限父级", name = "jurParent", dataType = "String", required = true, allowableValues = "权限父级,第一级用0标识")
    @NotBlank(message = "权限父级不能为空")
    @Size(max = 200, message = "权限父级最大长度为200位")
    @Column(name = "jur_parent", nullable = false)
    private String jurParent;

    /**
     * 权限父级
     */
    @ApiModelProperty(value = "权限标识", name = "jurFlag", dataType = "String", required = true, allowableValues = "权限标识")
    @NotBlank(message = "权限标识不能为空")
    @Size(max = 200, message = "权限标识最大长度为200位")
    @Column(name = "jur_flag", nullable = false)
    private String jurFlag;

    @Version
    private Long version;

    /**
     * 数据库中不创建此字段
     */
    @Transient
    private List<JurisdictionModel> list = new ArrayList<>();


    @Override
    public String toString() {
        return "JurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", jurName='" + jurName + '\'' +
                ", jurParent='" + jurParent + '\'' +
                ", jurFlag='" + jurFlag + '\'' +
                ", version=" + version +
                ", list=" + list +
                '}';
    }
}
