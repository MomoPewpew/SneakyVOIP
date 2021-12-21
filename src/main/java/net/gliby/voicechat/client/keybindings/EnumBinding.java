package net.gliby.voicechat.client.keybindings;

public enum EnumBinding {
    SPEAK("Voice chat"),
    OPEN_GUI_OPTIONS("Voice chat Options Menu");

    public String name;

    EnumBinding(String name) {
        this.name = name;
    }
}