/*
package util.utils;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;


import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

*/
/**
 * 类 名: MatrixToImageWriter<br/>
 * 描 述: 二维码工具类<br/>
 * 作 者: 王建强<br/>
 * 创 建： 2016年3月23日<br/>
 * <p/>
 * 历 史: (版本) 作者 时间 注释 <br/>
 *//*

public class MatrixToImageWriter {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter() {
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static void writeToStream(String content, String format, int width, int height, OutputStream stream) throws IOException {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, String> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            if (!ImageIO.write(image, format, stream)) {
                throw new IOException("Could not write an image of format " + format);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 描 述：创建二维码<br/>
     * 作 者：王建强<br/>
     *
     * @param content  二维码内容 字符串
     * @param path     文件路径
     * @param fileName 文件名称
     * @param width    宽
     * @param height   高
     *//*

    public static String create(String content, String path, String fileName, int width, int height) throws Exception {

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        File file = new File(path, fileName);
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        writeToFile(bitMatrix, suffix, file);
        return file.getAbsolutePath();

    }


    public static void main(String[] args) {
        try {

            String content = "userId;ticketId";
            String path = "D:/";
            String create = create(content, path, "test.jpg", 400, 400);
            System.out.println(create);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
