package com.yl.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NioDemo {

	private static final Logger log = LoggerFactory.getLogger(NioDemo.class);
	/**
	 * selector(通道的管理器)
	 * serverSocketChannel（关心Accept时间）
	 * SocketChannel（关心io时间read|write|read write）
	 * SelectionKey（时间集合）
	 */
	
	private Selector selector;
	
	public void initServer(int port) throws IOException{
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		this.selector = Selector.open();
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
		log.info("服务端启动成功。。。端口号为："+port);
		
	}
	
	public void listenSelector() throws IOException{
		while(true) {
			this.selector.select();
			Iterator<?> iteratorKey = this.selector.selectedKeys().iterator();
			while(iteratorKey.hasNext()) {
				SelectionKey selectionKey = (SelectionKey)iteratorKey.next();
				iteratorKey.remove();
				handler(selectionKey);
			}
		}
	}
	
	public void handler(SelectionKey selectionKey) throws IOException{
		if(selectionKey.isAcceptable()) {
			log.info("新的客户端连接...");
			ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
			SocketChannel channel =server.accept();
			channel.configureBlocking(false);
			channel.register(this.selector, SelectionKey.OP_READ);
		}else if (selectionKey.isReadable()) {
			SocketChannel channel = (SocketChannel)selectionKey.channel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int readData = channel.read(buffer);
			if(readData > 0) {
				String msg = new String(buffer.array(),"GBK").trim();
				log.info("服务端接收到消息"+msg);
				ByteBuffer writeBuffer = ByteBuffer.wrap("receive data".getBytes("GBK"));
				channel.write(writeBuffer);
			}else {
				log.info("客户端关闭了。。。");
				selectionKey.cancel();
			}
		}
	}
	
	/**
	 * 然后crtl+]进入命令模式，使用send发送消息，如：send hello,murphy
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NioDemo server = new NioDemo();
		server.initServer(12345);
		server.listenSelector();
	}
}
