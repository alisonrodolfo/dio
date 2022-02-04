package com.hatanaka.ecommerce.checkout.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author https://github.com/alisonrodolfo
 */
@Component
public class UUIDUtil {
    /* Created by Alison on 04/02/2022 */

    public UUID createUUID() {
        return UUID.randomUUID();
    }
}