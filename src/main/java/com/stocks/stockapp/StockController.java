package com.stocks.stockapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class StockController {

    @GetMapping("/")
    public String index(@RequestParam(name = "price", required = false) Double price, Model model) {
        Map<String, String> tickers = new LinkedHashMap<>();
        tickers.put("NVDL", "GraniteShares 2x Long NVDA Daily ETF");
        tickers.put("NVD", "GraniteShares 2x Short NVDA Daily ETF");
        tickers.put("SOXS", "Direxion Daily Semiconductor Bear 3X Shares");
        tickers.put("SOXL", "Direxion Daily Semiconductor Bull 3X Shares");
        model.addAttribute("tickers", tickers);

        if (price != null) {
            model.addAttribute("price", price);
            model.addAttribute("plus1", calculate(price, 1.01));
            model.addAttribute("plus08", calculate(price, 1.008));
            model.addAttribute("minus2", calculate(price, 0.98));
        }
        return "index";
    }

    private double calculate(double price, double factor) {
        return BigDecimal.valueOf(price * factor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
