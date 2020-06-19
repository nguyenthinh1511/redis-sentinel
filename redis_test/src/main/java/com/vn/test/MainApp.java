package com.vn.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class MainApp {

    private static final String MASTER_NAME = "mymaster";
    private static final String PASSWORD = "mypassword1";

    private static final Set<String> SENTINELS
            = new HashSet<>(
                    Arrays.asList("redis_sentinel_1:26379",
                            "redis_sentinel_2:26379",
                            "redis_sentinel_3:26379")
            );

    public static void main(String[] args) {
        System.out.println("START");
        JedisSentinelPool pool = null;
        try {
            pool = new JedisSentinelPool(MASTER_NAME, SENTINELS, PASSWORD);
            System.out.println("Fetching connection from pool.");

            int i = 0;
            while (true) {
                try (Jedis jedis = pool.getResource();) {

                    System.out.println("Connected to "
                            + jedis.getClient()
                                    .getSocket()
                                    .getRemoteSocketAddress());

                    String setRs = jedis.set("key_" + i, "value_" + i);
                    System.out.println("set i: " + setRs);
                    System.out.println(jedis.get("key_" + i));

                    i++;

                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pool != null) {
                pool.destroy();
            }
        }
    }
}
