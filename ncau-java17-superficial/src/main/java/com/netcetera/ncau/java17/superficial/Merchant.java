package com.netcetera.ncau.java17.superficial;

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
                    .sorted((m1, m2) -> m2.sales().compareTo(m1.sales()))
                    .map(MerchantSales::merchant)
                    .toList();
    //@formatter:on
  }

  private static  BigDecimal computeSales(Merchant merchant, Month month) {
    return BigDecimal.ZERO;
  }

}
