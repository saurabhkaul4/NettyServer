package edu.psu.os.NettyServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import static io.netty.buffer.Unpooled.*;

public class AppHandler extends ChannelInboundHandlerAdapter {

	public static Map<Integer, Integer> myMap = new ConcurrentHashMap<Integer, Integer>();
	
	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		String req = in.toString(io.netty.util.CharsetUtil.US_ASCII);
		System.out.println(req);
		if (req.contains("STOP")) {
			System.out.println("stopped");
			ctx.close();
		} else {
			final ByteBuf resp = buffer(4);
			String value = MapUtil.getValue(req, myMap);
			resp.writeBytes(value.getBytes());
			ctx.write(resp);
			ctx.flush();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
}
