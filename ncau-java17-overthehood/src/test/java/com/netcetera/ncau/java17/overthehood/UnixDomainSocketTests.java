package com.netcetera.ncau.java17.overthehood;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnixDomainSocketTests {

  private UnixDomainSocketAddress address;

  @BeforeEach
  void setUp() throws IOException {
    String tmpDir = System.getProperty("java.io.tmpdir");
    Path socketPath = Path.of(tmpDir, "server.socket");

    Files.deleteIfExists(socketPath);

    this.address = UnixDomainSocketAddress.of(socketPath);
  }

  @Test
  void test() throws InterruptedException {
    CountDownLatch serverStarted = new CountDownLatch(1);
    Thread serverThread = new Thread(() -> new Server(this.address, serverStarted).serve(), "server-thread");
    serverThread.start();

    serverStarted.await();
    Thread clientThread = new Thread(() -> new Client(this.address).send(), "client-thread");
    clientThread.start();

    clientThread.join();
    serverThread.join();
  }

  static final class Server {

    private final UnixDomainSocketAddress address;
    private final CountDownLatch serverStarted;

    Server(UnixDomainSocketAddress address, CountDownLatch serverStarted) {
      this.address = address;
      this.serverStarted = serverStarted;
    }


    void serve() {
      ByteBuffer buffer= ByteBuffer.allocate(1024);
      try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
        serverChannel.bind(this.address);

        this.serverStarted.countDown();
        try (SocketChannel clientChannel = serverChannel.accept()) {
          clientChannel.read(buffer);
          buffer.flip();
          System.out.println("Client sent:" + new String(buffer.array(), buffer.position(), buffer.limit()));
        }

      } catch (IOException e) {
        e.printStackTrace(System.err);
      }
    }

  }

  static final class Client {

    private final UnixDomainSocketAddress address;

    Client(UnixDomainSocketAddress address) {
      this.address = address;
    }


    void send() {
      try (SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX)) {
        channel.connect(this.address);

        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes());
        while(buffer.hasRemaining()) {
          channel.write(buffer);
        }
      } catch (IOException e) {
        e.printStackTrace(System.err);
      }
    }

  }

}
