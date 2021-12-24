package net.gliby.voicechat.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.gliby.voicechat.VoiceChat;
import net.gliby.voicechat.client.networking.ClientNetwork;
import net.gliby.voicechat.common.networking.MinecraftPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.icosider.voicechat.AsyncCatcher;

public class MinecraftClientTalkdistancePacket extends MinecraftPacket implements IMessageHandler<MinecraftClientTalkdistancePacket, MinecraftClientTalkdistancePacket> {
    private int entityID;
    private float mult;

    public MinecraftClientTalkdistancePacket() {
    }

    public MinecraftClientTalkdistancePacket(int entityId, float mult) {
    	this.entityID = entityId;
        this.mult = mult;
    }

    public void fromBytes(ByteBuf buf) {
    	this.entityID = buf.readInt();
        this.mult = buf.readFloat();
    }

    public void toBytes(ByteBuf buf) {
    	buf.writeInt(this.entityID);
        buf.writeFloat(this.mult);
    }

    public MinecraftClientTalkdistancePacket onMessage(MinecraftClientTalkdistancePacket packet, MessageContext ctx) {
        AsyncCatcher.INSTANCE.executeClient(() -> {
            final ClientNetwork network = VoiceChat.getProxyInstance().getClientNetwork();
            if (network.isConnected())
                network.getVoiceClient().handleTalkdistance(packet.entityID, packet.mult);
        });
        return null;
    }
}