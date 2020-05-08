package com.springcloudone.dataV;

import org.junit.Test;
import tech.tablesaw.api.*;
import tech.tablesaw.columns.numbers.NumberColumnFormatter;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.AreaPlot;

import java.io.IOException;

/**
 * @author xw
 * @date 2020/5/7 9:35
 */
//@RunWith(SpringRunner.class)
public class TestTableSaw {
    @Test
    public void test1(){
        String[] studentNames = {"张三", "李四", "王二"};
        Double[] scores = {90.1, 70.0, 84.0};
        Table table = Table.create("学生信息表");
        table.addColumns(StringColumn.create("姓名", studentNames), DoubleColumn.create("分数", scores));
        System.out.println(table.print());
    }

    @Test
    public void test2() throws IOException {
        Table table = Table.read().csv("E:/1aydhyj/t_wx_praise.csv");
        Table mediaPer = table.xTabPercents("'MEDIA_ID'");// 设置分类列指定x轴
        mediaPer.columnsOfType(ColumnType.DOUBLE)
                .forEach(x -> ((NumberColumn) x).setPrintFormatter(
                        NumberColumnFormatter.percent(2) // 设置小数点后2位
                ));

        //PieTrace trace = PieTrace.builder(mediaPer.categoricalColumn("Category"), mediaPer.doubleColumn("Percents")).build();
        //Figure figure = new Figure(trace);
        //File outputFile = Paths.get("E:/1aydhyj/output1.html").toFile();
        //Plot.show(figure, "target", outputFile);
        System.out.println(mediaPer.printAll());
        Plot.show(AreaPlot.create("投票分析", mediaPer, "Category", "Percents"));
    }
}
