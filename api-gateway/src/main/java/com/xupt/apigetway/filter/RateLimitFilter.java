/**
 * @author: maxu1
 * @date: 2019/2/19 13:10
 */

package com.xupt.apigetway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.xupt.apigetway.exception.RateLimitException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 *
 * @author maxu
 */
public class RateLimitFilter extends ZuulFilter {
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		if (!RATE_LIMITER.tryAcquire()) {
			throw new RateLimitException();
		}
		return null;
	}
}
