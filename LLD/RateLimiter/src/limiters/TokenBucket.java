package limiters;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimiter {
    private final int capacity;
    private final int reFillRate;
    private ConcurrentHashMap<String, Bucket> buckets;

    public TokenBucket(int capacity, int reFillRate) {
        this.capacity = capacity;
        this.reFillRate = reFillRate;
        this.buckets = new ConcurrentHashMap<>();
    }

    public boolean allowRequest(String key) {
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity, reFillRate));
        return bucket.tryConsume();
    }


    public static class Bucket {
        private final int capacity;
        private final int reFillRate;
        private long lastRefillTime;
        private int tokens;

        public Bucket(int capacity, int reFillRate) {
            this.capacity = capacity;
            this.reFillRate = reFillRate;
            this.lastRefillTime = System.currentTimeMillis();
            this.tokens = capacity;
        }

        public synchronized boolean tryConsume() {
            refill();
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        }


        public void refill() {
            long now = System.currentTimeMillis();
            double timeElapsedInSeconds = (double) (now - lastRefillTime) / 1000;
            int tokenToAdd=((int)(timeElapsedInSeconds*reFillRate));
            System.out.println(now+"----"+lastRefillTime+"---"+timeElapsedInSeconds+"---"+reFillRate+"---"+tokenToAdd);
            tokens=Math.min(capacity,tokenToAdd+tokens);
            if(tokenToAdd>0) lastRefillTime=now;
        }

    }
}
