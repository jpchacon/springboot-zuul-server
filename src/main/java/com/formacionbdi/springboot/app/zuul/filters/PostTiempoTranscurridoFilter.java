package com.formacionbdi.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Log
public class PostTiempoTranscurridoFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Entrando a post filter");

        Long timepoInicio = (Long) request.getAttribute("timepoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - timepoInicio;
        log.info(String.format("Tiempo transcurrido en segudos %s", tiempoTranscurrido.doubleValue()/1000.00));
        log.info(String.format("Tiempo transcurrido en milisegudos %s", tiempoTranscurrido));
        return null;
    }
}
