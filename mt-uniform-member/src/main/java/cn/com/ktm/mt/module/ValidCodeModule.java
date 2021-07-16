package cn.com.ktm.mt.module;

import cn.com.ktm.mt.model.constant.ResponseConsts;
import cn.com.ktm.mt.model.exception.Assert;
import cn.com.ktm.mt.model.message.member.validcode.request.ValidCodeRequest;
import cn.com.ktm.mt.model.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Random;

@Component
public class ValidCodeModule {

    Logger logger = LoggerFactory.getLogger(ValidCodeModule.class);

    /**
     *WIDTH =120 HEIGHT =50
     */
    public static final int WIDTH = 200;

    public static final int HEIGHT = 80;

    public static final String RANDOM_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static Random random = new Random();

    public byte[] getValidCode(ValidCodeRequest request) {

        String imgCode = generateRandomCode(4);
        int imgCodeSize = imgCode.length();
        byte[] bytes = null;

        try {

            BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Random random = new Random();
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color[] colors = new Color[5];
            Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN,
                    Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
                    Color.PINK, Color.YELLOW};
            float[] fractions = new float[colors.length];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
                fractions[i] = random.nextFloat();
            }
            Arrays.sort(fractions);

            // 设置边框色
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(0, 0, WIDTH, HEIGHT);

            Color color = getRandomColor(200, 250);
            // 设置背景色
            graphics2D.setColor(color);

            //绘制干扰线
            Random rand = new Random();
            // 设置线条的颜色
            graphics2D.setColor(getRandomColor(160, 200));

            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(WIDTH - 1);
                int y = rand.nextInt(HEIGHT - 1);
                int xl = rand.nextInt(6) + 1;
                int yl = rand.nextInt(12) + 1;
                graphics2D.drawLine(x, y, x + xl + 40, y + yl + 20);
            }
            // 添加噪点 噪声率
            float yawpRate = 0.05f;
            int area = (int) (yawpRate * WIDTH * HEIGHT);
            for (int i = 0; i < area; i++) {
                int x = rand.nextInt(WIDTH);
                int y = rand.nextInt(HEIGHT);
                int rgb = getRandomIntColor();
                bufferedImage.setRGB(x, y, rgb);
            }
            // 使图片扭曲
            shear(graphics2D, WIDTH, HEIGHT, color);

            graphics2D.setColor(getRandomColor(100, 160));
            int fontSize = HEIGHT - 4;
            Font font = new Font("Algerian", Font.ITALIC, fontSize);
            graphics2D.setFont(font);
            char[] chars = imgCode.toCharArray();
            for (int i = 0; i < imgCodeSize; i++) {
                AffineTransform affine = new AffineTransform();
                affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (random.nextBoolean() ? 1 : -1), (WIDTH / imgCodeSize) * i + fontSize / 2, HEIGHT / 2);
                graphics2D.setTransform(affine);
                graphics2D.drawChars(chars, i, 1, ((WIDTH - 10) / imgCodeSize) * i + 5, HEIGHT / 2 + fontSize / 2 - 10);
            }
            graphics2D.dispose();

            RedisCache.db().set(request.getBody().getValidToken(), imgCode,300);

            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpeg", jpegOutputStream);
            bytes = jpegOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(ResponseConsts.MEMBER_SYSTEM_EXCEPTION);
        }
        return bytes;
    }


    public static String generateRandomCode(int count) {
        return generateRandomCode(count, RANDOM_CODES);
    }

    public static String generateRandomCode(int count, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = RANDOM_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        // 50;
        int period = random.nextInt(40) + 10;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }

}
