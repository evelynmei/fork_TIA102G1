package com.tia102g1.chart.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aspose.cells.Chart;
import com.aspose.cells.ChartType;
import com.aspose.cells.PageOrientationType;
import com.aspose.cells.PageSetup;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

@Service
public class ProductChartService {

	public void generateProductChart(List<ProductSalesVO> productList, String outputFilePath) throws Exception {
		Workbook workbook = new Workbook();
		Worksheet sheet = workbook.getWorksheets().get(0);
		sheet.getCells().get("A1").putValue("商品名稱");
		sheet.getCells().get("B1").putValue("銷售數量");
		
		int row = 1;
		
		for(ProductSalesVO list: productList) {
			sheet.getCells().get(row, 0).putValue(list.getProName());
			sheet.getCells().get(row, 1).putValue(list.getTotalQuantity());
			row++;
		}
		
		PageSetup pageSetup = sheet.getPageSetup();
		pageSetup.setOrientation(PageOrientationType.LANDSCAPE);
		
		int chartIndex = sheet.getCharts().add(ChartType.COLUMN, 30, 0, 30, 20);
		Chart chart = sheet.getCharts().get(chartIndex);
		
		chart.getNSeries().add("B2:B" + row, true);
		chart.getNSeries().setCategoryData("A2:A" + row);
		
        chart.getTitle().setText("商品銷售統計");
        chart.getNSeries().get(0).setName("銷售個數");
        chart.getLegend().setPosition(com.aspose.cells.LegendPositionType.BOTTOM);
		
		chart.getChartObject().setUpperLeftRow(10);  // 設置圖表的起始行位置
        chart.getChartObject().setUpperLeftColumn(0); // 設置圖表的起始列位置
        chart.getChartObject().setHeight(400); // 設置圖表的高度
        chart.getChartObject().setWidth(800);  // 設置圖表的寬度
		
        workbook.save(outputFilePath);
	}
}
