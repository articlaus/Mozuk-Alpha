package com.model.util;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Enkhbat
 */
public class SequenceUtil {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    public static long nextLong() {
        return SEQ.incrementAndGet();
    }

    public static BigDecimal nextBigDecimal() {
        return BigDecimal.valueOf(nextLong());
    }

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

}