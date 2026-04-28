package com.Inventory.dealer_inventory_system.config.Tenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
	        throws ServletException, IOException {

	    String path = request.getRequestURI();

	    
	    if (path.startsWith("/h2-console")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String tenantId = request.getHeader("X-Tenant-Id");

	    if (tenantId == null || tenantId.isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing X-Tenant-Id");
	        return;
	    }

	    TenantContext.setTenantId(tenantId);

	    try {
	        filterChain.doFilter(request, response);
	    } finally {
	        TenantContext.clear();
	    }
	}
}