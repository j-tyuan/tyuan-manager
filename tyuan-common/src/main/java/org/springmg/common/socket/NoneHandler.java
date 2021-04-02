package org.tyuan.common.socket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guiqijiang on 7/13/19.
 */
public class NoneHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(NoneHandler.class);

    private Channel outChannel;

    public NoneHandler(Channel outChannel) {
        this.outChannel = outChannel;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (ctx.channel().isActive() && outChannel.isActive()) {
            outChannel.write(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel().isActive() && outChannel.isActive()) {
            outChannel.flush();
        }
    }
}

