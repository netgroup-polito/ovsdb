/*
 * Copyright (C) 2013 EBay Software Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Authors : Ashwin Raveendran
 */
package org.opendaylight.ovsdb.lib.jsonrpc;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.TooLongFrameException;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ExceptionHandler extends ChannelHandlerAdapter {
    protected static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if ((cause instanceof InvalidEncodingException)
                || (cause instanceof TooLongFrameException)) {

            ctx.channel().disconnect();
        }
        /* In cases where a connection is quickly established and the closed
        Catch the IOException and close the channel
         */
        if (cause instanceof IOException){
            logger.info("Exception handler called, closing connection {}",ctx.channel());
            ctx.channel().close();
        }
    }
}
