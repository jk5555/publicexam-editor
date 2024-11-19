package com.kun.publicexam.plugin.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kun.publicexam.plugin.utils.LogUtils;
import org.jetbrains.annotations.NotNull;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;


public class HttpRequestUtils {

    private static final Cache<String, HttpResponse> httpResponseCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build();

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final CookieManager cookieManager = new CookieManager(null, (uri, cookie) -> {
        if (uri == null || cookie == null || uri.getHost().equals("hm.baidu.com")) {
            return false;
        }
        return HttpCookie.domainMatches(cookie.getDomain(), uri.getHost());
    });

    static {
        CookieHandler.setDefault(cookieManager);
    }




    @NotNull
    public static HttpResponse executeGet(HttpRequest httpRequest) {

        return CacheProcessor.processor(httpRequest, request -> {

            HttpResponse httpResponse = new HttpResponse();
            try {
                HttpRequest.HttpRequestBuilder builder = HttpRequest.
                        builderGet().body(request.getBody());
                if (request.getHeader() != null) {
                    builder.addHeader(request.getHeader());
                }



                HttpRequest request = java.net.http.HttpRequest.newBuilder()
                        .uri(URI.create(request.getUrl()))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                return buildResp(executorHttp.executeGet(builder.build()), httpResponse);

            } catch (Exception e) {
                LogUtils.LOG.error("HttpRequestUtils request error:", e);
                httpResponse.setStatusCode(-1);
            }
            return httpResponse;
        });


    }

    @NotNull
    public static HttpResponse executePost(HttpRequest httpRequest) {
        return CacheProcessor.processor(httpRequest, request -> {
            HttpResponse httpResponse = new HttpResponse();
            try {
                com.shuzijun.lc.http.HttpRequest.HttpRequestBuilder builder = com.shuzijun.lc.http.HttpRequest.
                        builderPost(request.getUrl(), request.getContentType()).body(request.getBody()).addHeader(getHeader(request.getUrl()));
                if (request.getHeader() != null) {
                    builder.addHeader(request.getHeader());
                }
                return buildResp(executorHttp.executePost(builder.build()), httpResponse);
            } catch (Exception e) {
                LogUtils.LOG.error("HttpRequestUtils request error:", e);
                httpResponse.setStatusCode(-1);
            }
            return httpResponse;
        });
    }

    public static HttpResponse executePut(HttpRequest httpRequest) {
        return CacheProcessor.processor(httpRequest, request -> {
            HttpResponse httpResponse = new HttpResponse();
            try {
                com.shuzijun.lc.http.HttpRequest.HttpRequestBuilder builder = com.shuzijun.lc.http.HttpRequest.
                        builderPut(request.getUrl(), request.getContentType()).body(request.getBody()).addHeader(getHeader(request.getUrl()));
                if (request.getHeader() != null) {
                    builder.addHeader(request.getHeader());
                }
                return buildResp(executorHttp.executePut(builder.build()), httpResponse);
            } catch (Exception e) {
                LogUtils.LOG.error("HttpRequestUtils request error:", e);
                httpResponse.setStatusCode(-1);
            }
            return httpResponse;
        });
    }




    private static class CacheProcessor {
        public static HttpResponse processor(HttpRequest httpRequest, Callable<HttpResponse> callable) {

            String key = httpRequest.hashCode() + "";
            if (httpRequest.isCache() && httpResponseCache.getIfPresent(key) != null) {
                return httpResponseCache.getIfPresent(key);
            }
            if (httpRequest.isCache()) {
                synchronized (key.intern()) {
                    if (httpResponseCache.getIfPresent(key) != null) {
                        return httpResponseCache.getIfPresent(key);
                    } else {
                        HttpResponse httpResponse = callable.call(httpRequest);
                        if (httpResponse.getStatusCode() == 200) {
                            httpResponseCache.put(key, httpResponse);
                        }
                        return httpResponse;
                    }
                }
            } else {
                return callable.call(httpRequest);

            }
        }
    }

    @FunctionalInterface
    private interface Callable<V> {
        V call(HttpRequest request);
    }








}

