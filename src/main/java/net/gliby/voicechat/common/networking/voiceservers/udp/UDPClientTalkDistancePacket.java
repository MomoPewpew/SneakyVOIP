package net.gliby.voicechat.common.networking.voiceservers.udp;

import com.google.common.io.ByteArrayDataOutput;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class UDPClientTalkDistancePacket extends UDPPacket {
    public float mult;

    public UDPClientTalkDistancePacket(float mult) {
        this.mult = mult;
    }

    public byte id() {
        return (byte) 6;
    }

    public void write(ByteArrayDataOutput out) {
        out.writeFloat(this.mult);
    }
}