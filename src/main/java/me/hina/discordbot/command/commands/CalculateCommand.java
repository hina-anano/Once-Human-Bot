package me.hina.discordbot.command.commands;

import me.hina.OnceHumanData;
import me.hina.discordbot.command.Command;
import me.hina.utils.MathUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.IntegrationType;
import net.dv8tion.jda.api.interactions.InteractionContextType;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.utils.FileUpload;

import javax.imageio.ImageIO;
import javax.lang.model.element.ElementVisitor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CalculateCommand extends Command {

    @Override
    public String getCommandName() {
        return "計算建築材料";
    }

    @Override
    public String getDescription() {
        return "計算建築材料";
    }

    @Override
    public void onCommandInit(SlashCommandData data) {

        for (String building : OnceHumanData.BUILDING_MATERIALS.keySet()) {
            data.addOptions(new OptionData(OptionType.INTEGER, building, "數量"));
        }

        data.setContexts(InteractionContextType.ALL); // Allow the command to be used anywhere (Bot DMs, Guild, Friend DMs, Group DMs)
        data.setIntegrationTypes(IntegrationType.ALL); // Allow the command to be installed anywhere (Guilds, Users)
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent event) throws Exception {
        List<OptionMapping> options = event.getOptions();

        ArrayList<String> buildingNames = new ArrayList<>();

        if (options.isEmpty()) {
            event.reply("請輸入要蓋的建築 :3").queue();
            return;
        }


        HashMap<String, Integer> totalMaterials = new HashMap<>();

        for (OptionMapping option : options) {
            HashMap<String, Integer> materials = OnceHumanData.BUILDING_MATERIALS.get(option.getName());
            if (materials == null) {
                event.reply("報錯: 找不到 " + option.getName() + " 的所需材料").queue();
                return;
            }

            for (Map.Entry<String, Integer> entry : materials.entrySet()) {
                totalMaterials.put(entry.getKey(), totalMaterials.getOrDefault(entry.getKey(), 0) + entry.getValue() * option.getAsInt());
            }

            buildingNames.add(option.getName() + " x" + option.getAsInt());
        }

        //event.reply(totalMaterials.toString()).queue();


        BufferedImage image = new BufferedImage(750, 100 + totalMaterials.size() * 100, BufferedImage.TYPE_INT_ARGB);
        File imageFile = new File("temp/" + UUID.randomUUID() + ".png");
        Graphics2D g2 = image.createGraphics();

        // TODO: REPLACE WITH A GOOD LOOKING BLURRED BACKGROUND
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.setColor(Color.WHITE);


        Font font = Font.createFont(Font.PLAIN, new File("font.ttf")).deriveFont(36f);
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        int i = 0;
        for (Map.Entry<String, Integer> materialEntry : totalMaterials.entrySet()) {
            int y = 50 + i * 100;
            g2.drawString(materialEntry.getKey(), 160, y + 40 + font.getSize() / 2);

            String amountString = String.valueOf(materialEntry.getValue());
            g2.drawString(amountString, image.getWidth() - 50 - g2.getFontMetrics().stringWidth(amountString), y + 40 + font.getSize() / 2);

            g2.fillRect(48, y - 2, 84, 84);
            System.out.println("Reading File: " + materialEntry.getKey());
            g2.drawImage(ImageIO.read(new File("resources/items/" + materialEntry.getKey() + ".png")), 50, y, 80, 80, null);
            i++;
        }


        ImageIO.write(image, "png", imageFile);

        event.reply("你要蓋的建築: " + String.join(", ", buildingNames)).addFiles(FileUpload.fromData(imageFile)).queue();

        imageFile.delete();
    }

}
