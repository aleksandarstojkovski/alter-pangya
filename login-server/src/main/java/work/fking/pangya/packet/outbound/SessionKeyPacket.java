package work.fking.pangya.packet.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeMap;
import work.fking.pangya.networking.protocol.OutboundPacket;

public class SessionKeyPacket implements OutboundPacket {

    private static final int ID = 3;

    @Override
    public void encode(ByteBuf buffer, AttributeMap attributeMap) {
        buffer.writeShortLE(ID);
    }
}
