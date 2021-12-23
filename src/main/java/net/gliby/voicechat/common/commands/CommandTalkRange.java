package net.gliby.voicechat.common.commands;

import net.gliby.voicechat.VoiceChat;
import net.gliby.voicechat.common.VoiceChatServer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTalkRange extends CommandBase {

    @Override
    public String getName() {
        return "talkrange";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/talkrange <radius in blocks>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws WrongUsageException {
        if (args.length > 0) {
        	VoiceChatServer voiceServer = VoiceChat.getServerInstance();

        	Float mult;

        	try {
        		mult = Float.parseFloat((args[0].replace(',', '.'))) / voiceServer.getServerSettings().getSoundDistance();
    		}
    		catch(NumberFormatException e) {
    			throw new WrongUsageException(this.getUsage(sender));
    		}

        	voiceServer.getVoiceServer().sendTalkdistance(((EntityPlayerMP) sender).getEntityId(), mult);
        } else {
            throw new WrongUsageException(this.getUsage(sender));
        }
    }
}