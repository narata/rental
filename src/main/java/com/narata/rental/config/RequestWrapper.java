package com.narata.rental.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author narata
 * @since 2019/04/18
 */
public class RequestWrapper extends HttpServletRequestWrapper {
    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        body = responseStrBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };

    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

}