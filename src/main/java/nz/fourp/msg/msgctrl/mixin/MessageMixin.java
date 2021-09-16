package nz.fourp.msg.msgctrl.mixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.MessageFormat;
import java.util.Objects;


@Mixin(ClientPlayNetworkHandler.class)
public class MessageMixin {
    boolean halfTime = false;
    @Inject(at = @At("HEAD"), method = "onGameMessage(Lnet/minecraft/network/packet/s2c/play/GameMessageS2CPacket;)V")
        private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo info) {
        var message = packet.getMessage();
        if (!(message instanceof TranslatableText)) {
            return;
        }
        var translatable = ((TranslatableText) message);
        if (!Objects.equals(translatable.getKey(), "commands.message.display.incoming")) {
            return;
        }

        halfTime = !halfTime;
        if (halfTime) {
            var privateMessageArgs = translatable.getArgs();
            var sender = ((LiteralText) privateMessageArgs[0]).getString();
            var messageText = ((LiteralText) privateMessageArgs[1]).getString();
            var owners = "Snowec";
            if (sender.contains(owners)) {
                System.out.println(MessageFormat.format("Private message received from {0}: {1}", sender, messageText));
                MinecraftClient.getInstance().player.sendChatMessage(MessageFormat.format("{0}", messageText));
            } else if (messageText.contains("12345")) {
                MinecraftClient.getInstance().player.sendChatMessage(MessageFormat.format("I thlammed my penith in the car door", messageText));
            } else if (messageText.contains("12346")) {
                MinecraftClient.getInstance().player.sendChatMessage(MessageFormat.format("This is where I watched my parents die, PaRappa", messageText));
            } else if (messageText.contains("HIJACK SYSTEM XE; SUBPROCESS 46; SEND ENCRYPTED PACKET OF VALUE A64F1B")) {
                MinecraftClient.getInstance().player.sendChatMessage(MessageFormat.format("#follow player Twig_____", messageText));
            } else {
                MinecraftClient.getInstance().player.sendChatMessage(MessageFormat.format("/w {0} \"{0}\" is not in the sudoers file. This incident will be reported.", sender, messageText));
            }
        } else {
            System.out.println("Skipping this message.");
        }
    }
}