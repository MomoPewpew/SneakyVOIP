package net.gliby.voicechat.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.gliby.voicechat.VoiceChat;
import net.gliby.voicechat.common.networking.MinecraftPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.icosider.voicechat.AsyncCatcher;

public class MinecraftServerTalkdistancePacket extends MinecraftPacket implements IMessageHandler<MinecraftServerTalkdistancePacket, MinecraftServerTalkdistancePacket> {
    private int entityID;
	private float mult;

	public MinecraftServerTalkdistancePacket() {
    }

    public MinecraftServerTalkdistancePacket(int entityId, float mult) {
    	this.entityID = entityId;
        this.mult = mult;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.entityID = buf.readInt();
    	this.mult = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(this.entityID);
    	buf.writeFloat(this.mult);
    }

    @Override
    public MinecraftServerTalkdistancePacket onMessage(MinecraftServerTalkdistancePacket packet, MessageContext ctx) {
        AsyncCatcher.INSTANCE.execute(() -> {
            VoiceChat.getServerInstance().getVoiceServer().sendTalkdistance(packet.entityID, packet.mult);
        });
        return null;
    }
}