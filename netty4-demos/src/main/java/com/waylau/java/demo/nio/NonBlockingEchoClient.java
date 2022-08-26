/**
 * Welcome to https://waylau.com
 */
package com.waylau.java.demo.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Non Blocking Echo Client.
 * 
 * @since 1.0.0 2019年9月28日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Slf4j
public class NonBlockingEchoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hostName = "127.0.0.1";
		int portNumber = NonBlokingEchoServer.DEFAULT_PORT;

		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress(hostName, portNumber));
		} catch (IOException e) {
			log.info("NonBlockingEchoClient异常： " + e.getMessage());
			System.exit(1);
		}

		ByteBuffer writeBuffer = ByteBuffer.allocate(32);
		ByteBuffer readBuffer = ByteBuffer.allocate(32);

		try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				writeBuffer.put(userInput.getBytes());
				writeBuffer.flip();
				writeBuffer.rewind();

				// 写消息到管道
				socketChannel.write(writeBuffer);

				// 管道读消息
				socketChannel.read(readBuffer);

				// 清理缓冲区
				writeBuffer.clear();
				readBuffer.clear();
				System.out.println("echo: " + userInput);
			}
		} catch (UnknownHostException e) {
			log.info("不明主机，主机名为： " + hostName);
			System.exit(1);
		} catch (IOException e) {
			log.info("不能从主机中获取I/O，主机名为：" + hostName);
			System.exit(1);
		}
	}

}
