package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import shared.HandlerReadWrite;

/**
 * @author Samuel Nuttall
 */
public class ToLowerCaseHandler extends HandlerReadWrite implements HttpHandler
{
    
    @Override
    public void handle(HttpExchange httpExchange)
    {
    
    }
}
