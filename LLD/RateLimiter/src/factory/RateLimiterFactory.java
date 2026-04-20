package factory;

import enums.RateLimiterType;
import limiters.*;

public class RateLimiterFactory {
    public static RateLimiter create(RateLimiterType type,int limit,int rateOrWindowSeconds){
        return switch (type){
            case TOKEN_BUCKET -> new TokenBucket(limit,rateOrWindowSeconds);
            default -> throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        };
    }
}
