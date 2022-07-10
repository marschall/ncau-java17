package com.netcetera.ncau.java17.superficial;

import static java.util.Comparator.comparing;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public record Merchant() {

  static List<Merchant> findTopMerchants(List<Merchant> merchants, Month month) {
    // Local record
    record MerchantSales(Merchant merchant, BigDecimal sales) {}

    //@formatter:off
    return merchants.stream()
                    .map(merchant -> new MerchantSales(merchant, computeSales(merchant, month)))
                    .sorted(comparing(MerchantSales::sales).reversed())
                    .map(MerchantSales::merchant)
                    .toList();
    //@formatter:on
  }

  private static BigDecimal computeSales(Merchant merchant, Month month) {
    return BigDecimal.ZERO;
  }

}
