package com.kun.publicexam.plugin.util;

import lombok.Data;

@Data
public class HttpResponse {

    private int statusCode;

    private String body;

    private String url;

}
