/*******************************************************************************
 * Copyright (c) 2015 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 *    Martin Lanter - architect and re-implementation
 *    Francesco Corazza - HTTP cross-proxy
 ******************************************************************************/
package org.eclipse.californium.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.californium.core.network.Exchange;
import org.eclipse.californium.proxy.resources.ForwardingResource;


public class DirectProxyCoapResolver implements ProxyCoapResolver {

	private final static Logger LOGGER = Logger.getLogger(DirectProxyCoapResolver.class.getCanonicalName());
	
	private ForwardingResource proxyCoapClientResource;
	
	public DirectProxyCoapResolver() { 
		LOGGER.setLevel(Level.ALL);	
		
	}
	
	public DirectProxyCoapResolver(ForwardingResource proxyCoapClientResource) {
		this.proxyCoapClientResource = proxyCoapClientResource;
	}

	public ForwardingResource getProxyCoapClientResource() {
		return proxyCoapClientResource;
	}

	public void setProxyCoapClientResource(ForwardingResource proxyCoapClientResource) {
		this.proxyCoapClientResource = proxyCoapClientResource;
	}

	@Override
	public void forwardRequest(Exchange exchange) {
		LOGGER.fine("(DirectProxyCoapResolver 把请求交给资源 ForwardResource) Forward CoAP request to ProxyCoap2Coap: \n\t"+exchange.getRequest());
		/*
		 * 这里的 exchange 没有包含响应结果吧？
		 */
		proxyCoapClientResource.handleRequest(exchange);
	}
}