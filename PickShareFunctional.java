package com.groupproject1;
import java.util.Comparator;
import java.util.Optional;
import java.time.Duration;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class PickShareFunctional {
  
    public static ShareInfo findHighPriced(Stream<String> prices){
      return prices
              .map((s) -> new ShareInfo(s, APIFinance.getPrice(s)))
              .filter((info) -> info.price.compareTo(new BigDecimal(500)) < 0)
              .max(Comparator.comparing(p -> p.price))
              .get();
    }

    public static void main(String[] args) {
        
        System.out.println("Part b:");
        long start = System.currentTimeMillis();
        System.out.println(findHighPriced(Shares.symbols.stream()));
        long stop = System.currentTimeMillis();
        System.out.println("Execution Time is " + (stop - start) + " Milliseconds");
        System.out.println("Negating time spent sleeping, Execution Time is " + ((stop - start)%60000) + " Milliseconds");


        System.out.println("Waiting 60 seconds");
        try
        {
            Thread.sleep(60000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        System.out.println("Part c:");
        start = System.currentTimeMillis();
        System.out.println(findHighPriced(Shares.symbols.parallelStream()));
        stop = System.currentTimeMillis();
        System.out.println("Execution Time is " + (stop - start) + " Milliseconds");
        System.out.println("Negating time spent sleeping, Execution Time is " + ((stop - start)%60000) + " Milliseconds");

    }
}


