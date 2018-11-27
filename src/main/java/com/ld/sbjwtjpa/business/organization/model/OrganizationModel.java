package com.ld.sbjwtjpa.business.organization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "OrganizationModel", description = "组织机构表")
@Entity(name = "organization_table")
@Table(comment = "组织机构表", appliesTo = "organization_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    //    组织机构名称
    @ApiModelProperty(value = "组织机构名称", name = "orgName")
    @NotBlank(message = "组织机构名称不能为空")
    @Size(max = 200, message = "组织机构名称最大长度为200位")
    @Column(name = "org_name", nullable = false)
    private String orgName;
    //    组织机构父级
    @ApiModelProperty(value = "组织机构父级", name = "orgParent")
    @NotBlank(message = "组织机构父级不能为空")
    @Size(max = 200, message = "组织机构父级最大长度为200位")
    @Column(name = "org_parent", nullable = false)
    private String orgParent;
    //数据库中不创建该字段
    @Transient
    private List<OrganizationModel> list = new ArrayList<>();

    @Version
    private Long version;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgParent() {
        return orgParent;
    }

    public void setOrgParent(String orgParent) {
        this.orgParent = orgParent;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<OrganizationModel> getList() {
        return list;
    }

    public void setList(List<OrganizationModel> list) {
        this.list = list;
    }

    public OrganizationModel() {
        super();
    }

    public OrganizationModel(String uuid, @NotBlank(message = "组织机构名称不能为空") @Size(max = 200, message = "组织机构名称最大长度为200位") String orgName, @NotBlank(message = "组织机构父级不能为空") @Size(max = 200, message = "组织机构父级最大长度为200位") String orgParent, Long version) {
        this.uuid = uuid;
        this.orgName = orgName;
        this.orgParent = orgParent;
        this.version = version;
    }

    public OrganizationModel(String uuid, @NotBlank(message = "组织机构名称不能为空") @Size(max = 200, message = "组织机构名称最大长度为200位") String orgName, @NotBlank(message = "组织机构父级不能为空") @Size(max = 200, message = "组织机构父级最大长度为200位") String orgParent, List<OrganizationModel> list, Long version) {
        this.uuid = uuid;
        this.orgName = orgName;
        this.orgParent = orgParent;
        this.list = list;
        this.version = version;
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "uuid='" + uuid + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgParent='" + orgParent + '\'' +
                ", version=" + version +
                '}';
    }
}
