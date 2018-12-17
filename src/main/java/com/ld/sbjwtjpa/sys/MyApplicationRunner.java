package com.ld.sbjwtjpa.sys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

//    @Autowired
//    private QxglService qxglService;

    @Override
    public void run(ApplicationArguments var1) {
//        System.out.println("正在查询有无权限信息");
//        log.info("正在查询有无权限信息");
//        ResponseResult<List<QxglModel>> result = qxglService.findAll();
//        if (!result.isSuccess()) {
//            System.out.println("未查询到权限信息，开始初始化");
//            log.info("未查询到权限信息，开始初始化");
//            List<QxglModel> list = getData();
//            qxglService.saves(list);
//            System.out.println("权限初始化完成");
//            log.info("权限初始化完成");
//        } else {
//            List<QxglModel> list = getData();
//            List<QxglModel> list1 = result.getData();
//            Map<String, Integer> map = new HashMap<>(0);
//            list.forEach(j -> {
//                map.put(j.getQxbs(), 1);
//            });
//            list1.forEach(k -> {
//                Integer integer = map.get(k.getQxbs());
//                if (integer != null) {
//                    map.put(k.getQxbs(), ++integer);
//                    return;
//                } else
//                    map.put(k.getQxbs(), 1);
//            });
//            if (map.size() > 0) {
//                List<QxglModel> list2 = new ArrayList<>();
//                map.forEach((k, v) -> {
//                    if (v == 1)
//                        list.forEach(j -> {
//                            if (j.getQxbs().equals(k))
//                                list2.add(j);
//                        });
//                });
//                if (list2.size() > 0)
//                    qxglService.saves(list2);
//            }
//        }
    }

    /**
     * 删除重复语句
     * delete from qxgl_table where uuid in (select uuid from (select uuid from qxgl_table where qxbs in
     * (select qxbs from qxgl_table group by qxbs having count(qxbs)>1) and uuid not in
     * (select min(uuid) from qxgl_table group by qxbs having count(qxbs)>1)) as tmpresult)
     *
     * @return
     */
//    private List<QxglModel> getData() {
//        List<QxglModel> list = new ArrayList<>();
//        String str = "jyhc:jyhc,借用回仓-借用回仓;" +
//                "rcgl:rc,入仓管理-入仓;" +
//                "jy:update,出仓查询-借用单修改;" +
//                "jy:delete,出仓查询-借用单删除;" +
//                "ccgl:cc,出仓管理-借用出仓;" +
//                "tz:rctz,台账-入仓台账;" +
//                "tz:cctz,台账-出仓台账;" +
//                "tz:hctz,台账-回仓台账;" +
//                "jyhc:update,回仓查询-回仓单修改;" +
//                "jyhc:delete,回仓查询-回仓单删除;" +
//                "bb:zcfz,报表-资产负债;" +
//                "pd:zcpk,盘点-资产盘库;" +
//                "xtsz:ewmmm,系统设置-二维码密码;" +
//                "xtsz:account,系统设置-用户管理;" +
//                "xtsz:zccz,系统设置-资产处置;" +
//                "zhcx:rc,综合查询-入仓查询;" +
//                "zhcx:cc,综合查询-出仓查询;" +
//                "zhcx:jyhc,综合查询-回仓查询;" +
//                "zhcx:lsz,综合查询-每日统计;" +
//                "zhcx:zccx,综合查询-资产查询;" +
//                "zhcx:zczs,综合查询-资产追溯;" +
//                "zccx:update,资产查询-修改;" +
//                "zccx:delete,资产查询-删除";
//        for (int i = 0; i < str.split(";").length; i++) {
//            if (str.split(";")[i] != null) {
//                list.add(new QxglModel(str.split(";")[i].split(",")[1], str.split(";")[i].split(",")[0]));
//            }
//        }
//        return list;
//    }
}
