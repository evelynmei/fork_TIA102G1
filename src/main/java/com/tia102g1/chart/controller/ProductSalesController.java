package com.tia102g1.chart.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tia102g1.chart.model.ChartOrderListRepository;
import com.tia102g1.chart.model.ChartService;
import com.tia102g1.chart.model.ProductChartService;
import com.tia102g1.chart.model.ProductSalesRepository;
import com.tia102g1.chart.model.ProductSalesVO;

@Controller
@RequestMapping("/report")
public class ProductSalesController {

    @Autowired
    private ChartService chartSvc;

    @Autowired
    private ChartOrderListRepository repo;

    @Autowired
    private ProductSalesRepository productRepo;

    @Autowired
    private ProductChartService prodSvc;

    @GetMapping("/mainPageReport")
    public String mainPageReport(Model model) {
        List<Object[]> orderList = repo.findOrderSummary();
        String salesChartFilePath = "upload/salesChart.png";
        String productChartFilePath = "upload/productChart.png";

        try {
            // 生成訂單圖表
            chartSvc.generateChart(orderList, salesChartFilePath);
            model.addAttribute("chartPath", "/report/upload/salesChart.png");

            // 生成產品圖表
            List<ProductSalesVO> productSalesData = productRepo.findProductSalesData();
            prodSvc.generateProductChart(productSalesData, productChartFilePath);
            model.addAttribute("productChartPath", "/report/upload/productChart.png");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "圖表生成失敗：" + e.getMessage());
        }

        return "report/mainPageReport";
    }

    @GetMapping("/upload/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Path filePath = Paths.get("upload/" + filename);
        Resource resource = new FileSystemResource(filePath.toFile());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(resource);
    }
}

