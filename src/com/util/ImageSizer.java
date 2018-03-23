package com.util;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 图像压缩工具
 * 
 * @author KevinKuang
 * 
 */
@org.springframework.stereotype.Component("imageSizer")
public class ImageSizer 
{
	  /**图片格式：JPG*/
    private static final String PICTRUE_FORMATE_JPG = "jpg";
    
	public static final MediaTracker tracker = new MediaTracker(
			new Component()
			{
				private static final long serialVersionUID = 1234162663955668507L;
			});

	/**
	 *            原图像
	 * @param resizedFile
	 *            压缩后的图像
	 * @param width
	 *            图像宽
	 * @param format
	 *            图片格式 jpg, png, gif(非动画)
	 * @throws IOException
	 */
	public void resize(InputStream fis, File resizedFile,
			String format,Integer width) throws Exception
	{
		if (format != null && "gif".equals(format.toLowerCase())) 
		{
			return;
		}
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int readLength = -1;
		int bufferSize = 1024;
		byte bytes[] = new byte[bufferSize];
		while ((readLength = fis.read(bytes, 0, bufferSize)) != -1) 
		{
			byteStream.write(bytes, 0, readLength);
		}
		byte[] in = byteStream.toByteArray();
		fis.close();
		byteStream.close();
		Image inputImage = Toolkit.getDefaultToolkit().createImage(in);
		waitForImage(inputImage);
		int imageWidth = inputImage.getWidth(null);
		
		if(imageWidth > 1300)
		{
			imageWidth = 1300;
		}
		if (imageWidth < 1)
			throw new IllegalArgumentException("image width " + imageWidth
					+ " is out of range");
		int imageHeight = inputImage.getHeight(null);
		if (imageHeight < 1)
			throw new IllegalArgumentException("image height " + imageHeight
					+ " is out of range");

		// Create output image.
		int height = -1;
		double scaleW = (double) imageWidth / (double) imageWidth;
		double scaleY = (double) imageHeight / (double) height;
		if (scaleW >= 0 && scaleY >= 0) {
			if (scaleW > scaleY) {
				height = -1;
			} else {
				imageWidth = -1;
			}
		}
		Image outputImage = inputImage.getScaledInstance(imageWidth, height,
				java.awt.Image.SCALE_DEFAULT);
		checkImage(outputImage);
		encode(new FileOutputStream(resizedFile), outputImage, format);
		/*压缩其他尺寸的图片*/
		if(width!=null){
			outputImage = inputImage.getScaledInstance(width, height,
					java.awt.Image.SCALE_DEFAULT);
			checkImage(outputImage);
			encode(new FileOutputStream(resizedFile.getPath().replace(".jpg", "_"+width+".jpg")), outputImage, format);
		}
	}

	/** Checks the given image for valid width and height. */
	private static void checkImage(Image image) throws Exception
	{
		waitForImage(image);
		int imageWidth = image.getWidth(null);
		if (imageWidth < 1)
			throw new IllegalArgumentException("image width " + imageWidth
					+ " is out of range");
		int imageHeight = image.getHeight(null);
		if (imageHeight < 1)
			throw new IllegalArgumentException("image height " + imageHeight
					+ " is out of range");
	}

	/**
	 * Waits for given image to load. Use before querying image
	 * height/width/colors.
	 */
	private static void waitForImage(Image image) throws Exception
	{
		tracker.addImage(image, 0);
		tracker.waitForID(0);
		tracker.removeImage(image, 0);	
	}

	/** Encodes the given image at the given quality to the output stream. */
	private void encode(OutputStream outputStream, Image outputImage,
			String format) throws java.io.IOException 
	{
		int outputWidth = outputImage.getWidth(null);
		
		if (outputWidth < 1)
			throw new IllegalArgumentException("output image width "
					+ outputWidth + " is out of range");
		int outputHeight = outputImage.getHeight(null);
		if (outputHeight < 1)
			throw new IllegalArgumentException("output image height "
					+ outputHeight + " is out of range");
		
		BufferedImage bi = new BufferedImage(outputWidth, outputHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D biContext = bi.createGraphics();
		biContext.drawImage(outputImage, 0, 0, null);
		ImageIO.write(bi, format, outputStream);
//		System.out.println("----the end ------");
		outputStream.flush();
		outputStream.close();
	}
	
	/**
	 * 压缩图片
	 *
	 * 
	 * @param outFile
	 * 
	 * @param postfix
	 * 
	 * @throws Exception
	 */
	public int imageCompress(InputStream in,File outFile,String postfix,Integer width)
	{
		try{
			resize(in,outFile,postfix,width);
			//压缩成功返回0
			return 0;
		}catch(Exception e){
			//压缩失败返回1
			return 1;
		}
	}
	
	/**
     * 添加图片水印
     * 
     * @author KevinKuang
     *
     * @param waterImg  水印图片路径，如：C://myPictrue//logo.png
     */
    public int pressImage(File file, String waterImg)
    {
    	try
    	{
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
        
            Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1f)); //alpha
            
            int widthDiff = (width/2) - (width_1/2);
            int heightDiff = (height/2) - (height_1/2);
            g.drawImage(waterImage, widthDiff,heightDiff, width_1, height_1, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
            return 0;
        } catch (IOException e) 
        {
        	e.printStackTrace();
          	return 1;
        }   
    }
	
	public static void main(String[] args) throws Exception
	{
		new ImageSizer().imageCompress(new FileInputStream("d://1.jpg"), new File("e://2.jpg"), "jpg",null);
//		new ImageSizer().imageCompress(new FileInputStream("D:\\ww.jpg"), new File("D:\\shangPin\\123.jpg"), "jpg");
//		File file = new File("c://11.jpg");
//		new ImageSizer().pressImage(file, "c://5.png");
//		byte  []values = new ImageSizer().bufferedImageTobytes(new FileInputStream("D:\\a.jpg"));
//		
//		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\c.jpg"));
//		out.write(values);
//		out.close();
 		Image img = Toolkit.getDefaultToolkit().getImage("D:\\1.jpg");
	}
}
