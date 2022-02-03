package com.netcetera.ncau.java17.overthehood;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;

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
    Thread serverThread = new Thread(() -> new Server(this.address).serve(), "server-thread");
    Thread clientThread = new Thread(() -> new Client(this.address).send(), "client-thread");

    serverThread.start();

    clientThread.start();
    clientThread.join();

    serverThread.join();
  }

  static final class Server {

    private final UnixDomainSocketAddress address;

    Server(UnixDomainSocketAddress address) {
      this.address = address;
    }


    void serve() {
      ByteBuffer buffer= ByteBuffer.allocate(1024);
      try (ServerSocketChannel serverChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX)) {
        serverChannel.bind(this.address);

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
