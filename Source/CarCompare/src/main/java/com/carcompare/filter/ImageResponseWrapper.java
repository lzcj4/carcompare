package com.carcompare.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;

public class ImageResponseWrapper extends HttpServletResponseWrapper {

    ByteArrayOutputStream output;
    ImageOutputStream outputStream;
    public ImageResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream(){
        if(outputStream == null){
            outputStream =new  ImageOutputStream(output);
        }
        return  outputStream;
    }

    public byte[] getData() {
        if (output == null) return null;
        return output.toByteArray();
    }
}
