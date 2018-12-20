package com.yl.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * nio读取文件
 * @author Administrator
 *
 */
public class TestJavaNio {

	private static final Logger log = LoggerFactory.getLogger(TestJavaNio.class);
	
	static void readNIO() {  
        String pathname = "F:\\gongju\\学习资料\\面试相关\\nio\\nio.txt";  
        FileInputStream fin = null;  
        try {  
            fin = new FileInputStream(new File(pathname));  
            FileChannel channel = fin.getChannel();  
            int capacity = 100;// 字节  
            //分配一个新的字节缓冲区
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            log.info("限制是：" + bf.limit() + "容量是：" + bf.capacity()  
                    + "位置是：" + bf.position());  
            int length = -1;  
  
            while ((length = channel.read(bf)) != -1) {
  
                /*  
                 * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储  
                 */  
                bf.clear();  
                byte[] bytes = bf.array();
                System.out.write(bytes, 0, length);  
                System.out.println();  
  
                log.info("限制是：" + bf.limit() + "容量是：" + bf.capacity()  
                        + "位置是：" + bf.position());  
  
            }  
  
            channel.close();  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (fin != null) {  
                try {  
                    fin.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    static void writeNIO() {
        String filename = "out.txt";  
        FileOutputStream fos = null;  
        try {  
  
            fos = new FileOutputStream(new File(filename));  
            FileChannel channel = fos.getChannel();  
            ByteBuffer src = Charset.forName("utf8").encode("你好你好你好你好你好");  
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的  
            log.info("初始化容量和limit：" + src.capacity() + ","  
                    + src.limit());  
            int length = 0;  
  
            while ((length = channel.write(src)) != 0) {  
                /*  
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读  
                 */  
            	log.info("写入长度:" + length);  
            }  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    static void testReadAndWriteNIO() {
        String pathname = "F:\\gongju\\学习资料\\面试相关\\nio\\nio.txt";  
        FileInputStream fin = null;  
          
        String filename = "F:\\gongju\\学习资料\\面试相关\\nio\\niocopy.txt";  
        FileOutputStream fos = null;  
        try {  
            fin = new FileInputStream(new File(pathname));  
            FileChannel channel = fin.getChannel();  
  
            int capacity = 100;// 字节  
            ByteBuffer bf = ByteBuffer.allocate(capacity);  
            log.info("限制是：" + bf.limit() + "容量是：" + bf.capacity()+ "位置是：" + bf.position());  
            int length = -1;  
  
            fos = new FileOutputStream(new File(filename));  
            FileChannel outchannel = fos.getChannel();  
              
              
            while ((length = channel.read(bf)) != -1) {  
                  
                //将当前位置置为limit，然后设置当前位置为0，也就是从0到limit这块，都写入到同道中  
                bf.flip();  
                  
                int outlength =0;  
                while((outlength=outchannel.write(bf)) != 0){  
                	log.info("读，"+length+"写,"+outlength);  
                }  
                  
                //将当前位置置为0，然后设置limit为容量，也就是从0到limit（容量）这块，  
                //都可以利用，通道读取的数据存储到  
                //0到limit这块  
                bf.clear();  
                  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
            if (fin != null) {  
                try {  
                    fin.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
    
    static void testReadAndWriteFilesNIO() {
        String pathname = "F:\\gongju\\学习资料\\面试相关\\nio\\nio.txt";  
        FileInputStream fin = null;  
          
        try {  
            fin = new FileInputStream(new File(pathname));  
            FileChannel channel = fin.getChannel();  
  
            int capacity = 100;// 字节  
            ByteBuffer bf = ByteBuffer.allocate(capacity);  
            log.info("限制是：" + bf.limit() + "容量是：" + bf.capacity()+ "位置是：" + bf.position());  
            int length = -1;  
  
            int idx = 0;
            while ((length = channel.read(bf)) != -1) {  
                  
                //将当前位置置为limit，然后设置当前位置为0，也就是从0到limit这块，都写入到同道中  
                bf.flip();  
                //分批写入到多个文件
                idx++;
                String filename = "F:\\gongju\\学习资料\\面试相关\\nio\\nio"+idx+".txt";
                FileOutputStream fos = null;
                fos = new FileOutputStream(new File(filename));  
                FileChannel outchannel = fos.getChannel();  
                int outlength =0;  
                while((outlength=outchannel.write(bf)) != 0){  
                	log.info("读，"+length+"写,"+outlength);  
                }  
                
                //将当前位置置为0，然后设置limit为容量，也就是从0到limit（容量）这块，  
                //都可以利用，通道读取的数据存储到  
                //0到limit这块  
                bf.clear();
                if (fos != null) {  
                    try {
                        fos.close();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }
                }
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
            if (fin != null) {  
                try {  
                    fin.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }
    public static void main(String[] args) {  
//        testReadAndWriteNIO();  
    	testReadAndWriteFilesNIO();
    }
}
