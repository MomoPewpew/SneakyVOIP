package net.gliby.voicechat.common.networking.voiceservers.udp;

import com.google.common.io.ByteArrayDataOutput;

public class UDPServerTalkDistancePacket extends UDPPacket {
    private final int entityID;
    private float mult;

    UDPServerTalkDistancePacket(int entityID, float mult) {
        this.entityID = entityID;
        this.mult = mult;
    }

    public byte id() {
        return (byte) 6;
    }

    public void write(ByteArrayDataOutput out) {
        out.writeInt(this.entityID);
        out.writeFloat(this.mult);
    }
}