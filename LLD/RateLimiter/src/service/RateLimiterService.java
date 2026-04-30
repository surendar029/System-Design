package service;

import enums.RateLimiterType;
import factory.RateLimiterFactory;
import limiters.RateLimiter;

public class RateLimiterService {
    private RateLimiter rateLimiter;

    public RateLimiterService(RateLimiterType type,int capacity,int rateOrWindowSeconds){
        this.rateLimiter= RateLimiterFactory.create(type,capacity,rateOrWindowSeconds);
    }

    public boolean allowRequest(String key){
        return rateLimiter.allowRequest(key);
    }
}
