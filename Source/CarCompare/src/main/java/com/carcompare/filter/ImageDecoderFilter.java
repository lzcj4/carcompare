package com.carcompare.filter;

import com.carcompare.Imageencryption.IImageDEncrypter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片解密过滤器
 */
@Component
public class ImageDecoderFilter implements  Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("imageDEncrypter")
    IImageDEncrypter imageDEncrypter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ImageResponseWrapper wrapper = new ImageResponseWrapper(response);
        filterChain.doFilter(servletRequest, wrapper);
        OutputStream out = response.getOutputStream();
        response.setContentType(wrapper.getContentType());

        byte[] bytes = wrapper.getData();
        if(bytes != null && bytes.length > 0){
            bytes = this.imageDEncrypter.decrypt(bytes);
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
        }
        out.close();
    }

    @Override
    public void destroy() {

    }
}
