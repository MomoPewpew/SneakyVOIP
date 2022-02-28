package net.gliby.voicechat.client.keybindings;

import net.gliby.voicechat.client.VoiceChatClient;
import net.gliby.voicechat.common.PlayerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

class KeyTalkDistanceEvent extends KeyEvent {
    private final VoiceChatClient voiceChat;

    KeyTalkDistanceEvent(VoiceChatClient voiceChat, EnumBinding keyBind, int keyID, boolean repeating) {
        super(keyBind, keyID, repeating);
        this.voiceChat = voiceChat;
    }

    @Override
    public void keyDown(KeyBinding kb, boolean tickEnd, boolean isRepeat) {
    	Minecraft mc = Minecraft.getMinecraft();

    	if (mc.currentScreen != null) return;

    	int entityId = mc.player.getEntityId();
    	final PlayerProxy proxy = VoiceChatClient.getSoundManager().getPlayerData(entityId);
    	float currentDistance = proxy.getMaxTalkDistanceMultiplier();
    	float newDistance = this.voiceChat.getSettings().getNextMaxTalkDistanceMultiplier(currentDistance);

    	this.voiceChat.getClientNetwork().sendTalkDistance(entityId, newDistance);
    }

    @Override
    public void keyUp(KeyBinding kb, boolean tickEnd) {
    }
}