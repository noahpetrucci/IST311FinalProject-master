import java.awt.Graphics;
import java.awt.Color;
import java.util.Timer;
import java.awt.Insets;
import java.util.TimerTask;

import javax.swing.JFrame;

public class animation extends JFrame {

    // Little animation piece ferda
    private static int DELAY = 500; //The speed on which the animation will run

    Insets animate_insets;

    Color colors[] = { Color.getHSBColor(0,0.551F,0.804F), Color.getHSBColor(0.167F,0.454F,0.933F), Color.getHSBColor(0.279F,0.489F,0.859F), Color.getHSBColor(0.584F,1,1),
            Color.getHSBColor(0.833F,0.16F,0.98F), Color.getHSBColor(0.908F,1,1) };

    public void paint(Graphics animation) {
        super.paint(animation);
        if (animate_insets == null) {
            animate_insets = getInsets();
        }
        int a = animate_insets.left;
        int b = animate_insets.top;
        int animation_width = getWidth() - animate_insets.left - animate_insets.right;
        int animation_height = getHeight() - animate_insets.top - animate_insets.bottom;
        int animation_start = 0;
        int animation_steps = colors.length;
        int animation_stepSize = 720 / animation_steps;
        synchronized (colors) {
            for (int i = 0; i < animation_steps; i++) {
                animation.setColor(colors[i]);
                animation.fillArc(a, b, animation_width, animation_height, animation_start, animation_stepSize);
                animation_start += animation_stepSize;
            }
        }
    }

    public void go() {
        TimerTask animation_task = new TimerTask() {
            public void run() {
                Color animation_color = colors[0];
                synchronized (colors) {
                    System.arraycopy(colors, 1, colors, 0, colors.length - 1);
                    colors[colors.length - 1] = animation_color;
                }
                repaint();
            }
        };
        Timer animation_timer = new Timer();
        animation_timer.schedule(animation_task, 0, DELAY);
    }

}
