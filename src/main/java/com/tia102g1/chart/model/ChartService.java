package com.tia102g1.chart.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aspose.cells.Chart;
import com.aspose.cells.ChartType;
import com.aspose.cells.PageOrientationType;
import com.aspose.cells.PageSetup;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

@Service
public class ChartService {

    public void generateChart(List<Object[]> orderList, String outputFilePath) throws Exception {
        // 創建一個新的工作簿
        Workbook workbook = new Workbook();
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.getCells().get("A1").putValue("訂單日期");
        sheet.getCells().get("C1").putValue("處理中金額");
        sheet.getCells().get("E1").putValue("已付款金額");
        sheet.getCells().get("G1").putValue("退款金額");
        
        int row = 1;
        
        // 處理原始數據並寫入工作表
        for (Object[] record : orderList) {
            String date = (String) record[0];
            BigDecimal group1Amount = (BigDecimal) record[1];
            BigDecimal group2Amount = (BigDecimal) record[2];
            BigDecimal group3Amount = (BigDecimal) record[3];
            
            sheet.getCells().get(row, 0).putValue(date);
            sheet.getCells().get(row, 2).putValue(group1Amount.doubleValue());
            sheet.getCells().get(row, 4).putValue(group2Amount.doubleValue());
            sheet.getCells().get(row, 6).putValue(group3Amount.doubleValue());
            row++;
        }
        
        PageSetup pageSetup = sheet.getPageSetup();
        pageSetup.setOrientation(PageOrientationType.LANDSCAPE);  // 設定為橫向	
        
        // 創建長條圖
        int chartIndex = sheet.getCharts().add(ChartType.COLUMN, 5, 0, 20, 10); // 調整範圍，增大寬度
        Chart chart = sheet.getCharts().get(chartIndex);
        
        // 設置圖表數據系列
        chart.getNSeries().add("C2:C" + row, true); // 處理中金額
        chart.getNSeries().add("E2:E" + row, true); // 已付款金額
        chart.getNSeries().add("G2:G" + row, true); // 退款金額
        chart.getNSeries().setCategoryData("A2:A" + row); // 設置 X 軸為日期
        
        // 添加圖例
        chart.getNSeries().get(0).setName("處理中金額");
        chart.getNSeries().get(1).setName("已付款金額");
        chart.getNSeries().get(2).setName("退款金額");  
        
        chart.getLegend().setPosition(com.aspose.cells.LegendPositionType.BOTTOM);
        
        // 設置圖表標題和 X, Y 軸標題
        chart.getTitle().setText("訂單狀態金額統計");
        
        // 設置圖表區域的位置和大小
        chart.getChartObject().setUpperLeftRow(10);  // 設置圖表的起始行位置
        chart.getChartObject().setUpperLeftColumn(0); // 設置圖表的起始列位置
        chart.getChartObject().setHeight(400); // 設置圖表的高度
        chart.getChartObject().setWidth(800);  // 設置圖表的寬度
        
        // 將工作簿輸出到流中
        workbook.save(outputFilePath);
    }
}
