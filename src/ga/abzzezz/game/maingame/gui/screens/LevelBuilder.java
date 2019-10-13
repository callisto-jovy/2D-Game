/*
 * Copyright (c) 2019. Abzzezz
 * All code belongs to Abzzezz. Used Code/APIs are mentioned
 */

package ga.abzzezz.game.maingame.gui.screens;

import ga.abzzezz.game.Main;
import ga.abzzezz.game.core.collision.Collision;
import ga.abzzezz.game.core.rendering.RenderHelper;
import ga.abzzezz.game.maingame.gui.basis.GuiButton;
import ga.abzzezz.game.maingame.gui.basis.GuiScreen;
import ga.abzzezz.game.maingame.gui.basis.ImageButton;
import ga.abzzezz.game.maingame.gui.basis.TextBox;
import ga.abzzezz.game.maingame.object.Prevent;
import ga.abzzezz.game.maingame.object.impl.Block;
import ga.abzzezz.game.maingame.utility.ColorHelper;
import org.joml.Vector2i;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class LevelBuilder extends GuiScreen {


    /*
    For dev purposes only
     */
    private ArrayList<Prevent> prevents = new ArrayList();

    @Override
    public void initialiseGui() {
        int x = display()[0] - 64;

        guiButtons.add(new ImageButton("Block", "block.png", x, 0, 0));
        guiButtons.add(new ImageButton("Player", "player.png", x, 100, 1));
        guiButtons.add(new GuiButton("Save", 0, display()[1] - 30, 3));
        guiButtons.add(new GuiButton("Clear", 100, display()[1] - 30, 4));

        guiButtons.add(new GuiButton("Set", x - 20, display()[1] - 30, 6));
        textBoxes.add(new TextBox("ColorBox", "Color", x - 120, display()[1] - 30, !edit));
        super.initialiseGui();
    }

    @Override
    public void buttonPressed(int buttonID) {
        if (buttonID == 0) {
            prevents.add(new Block("B" + System.currentTimeMillis(), new Vector2i(display()[0] / 2, display()[1] / 2), 100, 100));
        } else if (buttonID == 1) {
            prevents.add(new Block("Player", new Vector2i(display()[0] / 2, display()[1] / 2), 30, 30, Color.GREEN));
        } else if (buttonID == 3) {
            Main.getMain().getLevelSystem().saveLevel(prevents);
        } else if (buttonID == 4) {
            prevents.clear();
        } else if (buttonID == 6) {
            if (selected != null) selected.setColor(Color.decode(textBoxes.get(0).getText()));
        }
        super.buttonPressed(buttonID);
    }

    Prevent selected;

    @Override
    public void drawScreen() {
        for (Prevent prevent : prevents) {
            prevent.draw();
        }
        if (drag) {
            selected.setxPos((int) (Collision.getMousePosition()[0] - selected.getWidth() / 2));
            selected.setyPos((int) (Collision.getMousePosition()[1] - selected.getHeight() / 2));
        }


        geTextBoxByID("ColorBox").setHide(!edit);

        RenderHelper.drawQuad(display()[0] - 80, 0, 80, display()[1], ColorHelper.colorFormHex(0xf1c40f));
        super.drawScreen();
    }

    private boolean drag, edit;

    @Override
    public void keyPressed(int keyCode, char keyChar, boolean hold) {
        if (edit) {
            switch (keyCode) {
                case Keyboard.KEY_RIGHT:
                    selected.setWidth(selected.getWidth() + 1);
                    break;
                case Keyboard.KEY_LEFT:
                    selected.setWidth(selected.getWidth() - 1);
                    break;
                case Keyboard.KEY_DOWN:
                    selected.setHeight(selected.getHeight() + 1);
                    break;
                case Keyboard.KEY_UP:
                    selected.setHeight(selected.getHeight() - 1);
                    break;
                default:
                    break;
            }
        }
        super.keyPressed(keyCode, keyChar, hold);
    }

    @Override
    public void mousePress(int mouseButton) {
        for (Prevent prevent : prevents) {
            if (Collision.mouseHovered(prevent.getxPos(), prevent.getyPos(), prevent.getWidth(), prevent.getHeight())) {
                if (mouseButton == 0) {
                    drag = !drag;
                } else if (mouseButton == 1) {
                    edit = !edit;
                }
                selected = prevent;
            }
        }

        super.mousePress(mouseButton);
    }
}
