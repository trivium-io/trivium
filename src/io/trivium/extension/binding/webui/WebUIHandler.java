/*
 * Copyright 2016 Jens Walter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.trivium.extension.binding.webui;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import io.trivium.anystore.statics.MimeTypes;
import io.trivium.glue.binding.http.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WebUIHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Session s = new Session(httpExchange);
        String origURI = httpExchange.getRequestURI().getPath();
        String uri = origURI;
        if (uri.equals("/ui/"))
            uri = "io/trivium/extension/binding/webui/res/index.html";
        else
            uri = "io/trivium/extension/binding/webui" + uri.substring(3);
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            Class<?> clazz = cl.loadClass(uri.replace('/', '.'));
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> iface : interfaces) {
                if (iface.getCanonicalName().equals("com.sun.net.httpserver.HttpHandler")) {
                    //is request handler
                    HttpHandler handler = (HttpHandler) clazz.newInstance();
                    handler.handle(httpExchange);
                    return;
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        InputStream is = cl.getResourceAsStream(uri);
        if (is != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[100000];
            while(is.available()>0){
                int count = is.read(buf);
                bos.write(buf,0,count);
            }
            String ending = uri.substring(uri.lastIndexOf('.') + 1);
            String contentType = MimeTypes.getMimeType(ending, "text/plain");

            s.ok(contentType, new String(bos.toByteArray()));
            return;
        }
        //if no response was send so far
        s.error(404, "resource not found");
    }
}
