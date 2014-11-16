package com.store.hive.model.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by tinashe
 *
 * Just a class for generating sample Product Items
 */
public class ProductListGenerator {

    private static final int NUM_OF_PRODUCTS = 50;

    static String urls[] = {"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRsr8Acb0Kbyiy0-FThoWFiiDX8LkBFu8b_TNEfKlDjRQdhjL80",
                            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcS2giwXToIv5Ftr3xswriOAuaUO-fk2VtLNY7TclfGHQ_LTaYO6",
                            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSa2AIBWjic_a9uhqpuP9452qDrft74vMwfv7qOUYH2symJ_qHRPQ",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSON8VnG0q-VNYbnhBnARak7oA8UybxyVAB-NZ2vzqUiuvYBoG04g",
                            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSJLVQglPoZEvfKuKsTAukuPG1mq1aEOKYwSWPQqyZA637kD5DB-g",
                            "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSi9HFj2CPrhN9SoluXTHV-D7fsptROiWMrZhr3745ynTJVd4V9hQ",
                            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQNShmyOnlAdz0NCoqKSMBnUM9QknGT9iiWDRfYWbauixukWeo7TA",
                            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSq4xJhB7okZbctsG6NVGNBOHSirwFJ_iO3RBQRDj2VZheFinaAfw",
                            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQxmIITW1pWCsEdPjZ6uz1kVbk8zvTvSi52MZveP9x0w5vZ8oRx",
                            "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRfPI69MSBDvLr9gVkyE_nAlLk4sazMGxCqNj3Wl0pr0p5AN8FsJA",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTefdbVmTSLPmQFxPpJ5nDmDQNbpNJQjhIOtP1oZ1z8o2V7b8z-Cg",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTW0t0DxntH9vdYASgXx3gozL8QIWFU827wJ9kkTMwcc8twGm1BA",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRF3nRe3_U1s7ZDYnrAdqtiz2aMsAiahiu97ipjh281M7wO2yWtbw",
                            "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTL5hUqVD5mcuglykluuqq5ZBbXsFUn7LQhLaY_VeayxFq5RVcz",
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8c3MXEMuQzbyKk6e8qpmXW2avG8gPoDey-v2419yE6lmQDk8wiA"};

    public static ArrayList<Product> getSampleProducts(){
        ArrayList<Product> products = new ArrayList<Product>();

        for(int i = 0; i < NUM_OF_PRODUCTS; i++){

            Product product = new Product();
            product.setProductId(568);
            product.setDescription("Description for this product");
            product.setCategoryId(6);
            product.setPrice(new BigDecimal("36.00"));
            product.setImgUrl(urls[getRandomUrlPosition()]);
            products.add(product);

        }

        return products;
    }

    private static int getRandomUrlPosition(){
        Random random = new Random();
        return random.nextInt(urls.length - 1);
    }

}
