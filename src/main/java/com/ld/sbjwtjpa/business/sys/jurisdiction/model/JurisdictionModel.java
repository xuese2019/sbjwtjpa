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
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@JsonComponent
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称(也是菜单名称)", name = "jurName", dataType = "String", required = true, allowableValues = "(也是菜单名称)")
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
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识(也是跳转路径)", name = "jurFlag", dataType = "String", required = true, allowableValues = "权限标识(也是跳转路径,如果没有路径用0标识)")
    @NotBlank(message = "权限标识不能为空")
    @Size(max = 200, message = "权限标识最大长度为200位")
    @Column(name = "jur_flag", nullable = false)
    private String jurFlag;

    /**
     * 权限类型
     */
    @ApiModelProperty(value = "权限类型（1：左侧菜单，2：内部按钮）", name = "jurType", dataType = "String", required = true, allowableValues = "权限类型（1：左侧菜单，2：内部按钮）")
    @Min(value = 1, message = "权限类型最小值为1")
    @Max(value = 2, message = "权限类型最大值为2")
    @Column(name = "jur_type", nullable = false)
    private int jurType;

    /**
     * 权限图标
     */
    @ApiModelProperty(value = "权限图标",
            name = "jurType",
            dataType = "String",
            required = true,
            allowableValues = "权限图标")
    @Size(max = 200, message = "权限图标最大长度为200位")
    @Column(name = "jur_ico")
    private String jurIco;

    /**
     * 权限角色（控制上级菜单是否显示）
     */
    @ApiModelProperty(value = "权限角色（控制上级菜单是否显示）",
            name = "jurType",
            dataType = "String",
            required = true,
            allowableValues = "权限角色（控制上级菜单是否显示）")
    @Size(max = 200, message = "权限角色最大长度为200位")
    @Column(name = "jur_role")
    private String jurRole;

    @Version
    private Long version;

    /**
     * 数据库中不创建此字段
     */
    @Transient
    private List<JurisdictionModel> list = new ArrayList<>();

}
