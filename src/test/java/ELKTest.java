import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class ELKTest {
	@Test
	public void test() throws Exception{
		Client client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("hadoop-slave4"), 9200));  ;
		
		Map<String,Object> map = Maps.newHashMap();
		map.put("barcode", "6921913320112");
		map.put("name", "502胶");
		map.put("price", "1.50");
		map.put("unit", "支");
		String s = new Gson().toJson(map);
		IndexResponse res = null;
		res = client.prepareIndex("smms","goods").setSource(s).execute().actionGet();
		System.out.println(res);
	}
	
	
}
