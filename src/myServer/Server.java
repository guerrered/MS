package myServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

public class Server {

	//myLocation 129.89.130.91
	public static void main(String[] args)throws Exception{
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/main",new StaticFileServer("main.html"));
		server.createContext("/sub", new StaticFileServer("sub.html"));
		server.createContext("/style.css", new StaticFileServer("style.css"));
		server.createContext("/ex.js", new StaticFileServer("ex.js"));
		server.setExecutor(null);
		server.start();
	}
	
	static class StaticFileServer implements HttpHandler{
		String fileName;
		
		public StaticFileServer(String fileName){
			this.fileName = fileName;
		}
		
		public void handle(HttpExchange exchange) throws IOException{
		File file = new File(fileName);
		if(!file.exists()){
			String response = "<h1>Error 404</h1><h2>\nFile Not Found</h2>";
			exchange.sendResponseHeaders(404,  response.length());
			OutputStream output = exchange.getResponseBody();
			output.write(response.getBytes());
			output.flush();
			output.close();
		}else{
			exchange.sendResponseHeaders(200, 0);
			OutputStream output = exchange.getResponseBody();
			FileInputStream fs = new FileInputStream(file);
			final byte[] buffer = new byte[4096];
			int count = 0;
			while((count = fs.read(buffer)) >=0){
				output.write(buffer, 0, count);
			}
			output.flush();
			output.close();
			fs.close();
		}
			
		}
	}
}
