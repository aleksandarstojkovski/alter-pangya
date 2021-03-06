package work.fking.pangya;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;
import work.fking.pangya.networking.protocol.InboundPacket;
import work.fking.pangya.packet.inbound.LoginRequestPacket;
import work.fking.pangya.packet.outbound.LoginKeyPacket;
import work.fking.pangya.packet.outbound.LoginResultPacket;
import work.fking.pangya.packet.outbound.ServerListPacket;
import work.fking.pangya.packet.outbound.SessionKeyPacket;

@Log4j2
public class PacketHandler extends SimpleChannelInboundHandler<InboundPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, InboundPacket packet) {

        if (packet instanceof LoginRequestPacket loginRequest) {
            LOGGER.debug("LoginRequest username={}, passwordHash={}", loginRequest.getUsername(), loginRequest.getPasswordMd5());
            LoginResultPacket loginResultPacket = LoginResultPacket.builder()
                                                                   .success()
                                                                   .username("username")
                                                                   .nickname("nickname")
                                                                   .userId(1)
                                                                   .build();
            ctx.channel().writeAndFlush(loginResultPacket);
            ctx.channel().writeAndFlush(new LoginKeyPacket());
            ctx.channel().writeAndFlush(new ServerListPacket());
        } else {
            LOGGER.warn("Unhandled inbound packet={}", packet);
        }
    }
}
