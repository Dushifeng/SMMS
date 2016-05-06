package com.dodo.smms.search;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import com.dodo.smms.entity.Goods;

public class ELSearch {
	
	public static void bulkUpdateGoods(List<Goods> goods) throws IOException, InterruptedException, ExecutionException {

		BulkRequestBuilder req = client.prepareBulk();

		for (Goods good : goods) {
			req.add(client.prepareIndex("smms", "goods", good.getId() + "")
					.setSource(jsonBuilder().startObject().field("unit", good.getUnit()).field("price", good.getPrice())
							.field("name", good.getName()).field("barcode", good.getBarCode()).endObject()));
		}

		BulkResponse res = req.execute().actionGet();

		if (res.hasFailures()) {
			System.out.println("Error");
		} else {
			System.out.println("Done");
		}

		client.close();
	}
	
	
	
	public static void updateGoods(Goods good ) throws IOException, InterruptedException, ExecutionException {
		
		UpdateRequest req = new UpdateRequest();
		req.index("smms");
		req.type("goods");
		req.id(good.getId()+"");
		req.doc(jsonBuilder().startObject().field("unit", good.getUnit()).field("price", good.getPrice())
				.field("name", good.getName()).field("barcode", good.getBarCode()).endObject());
		client.update(req).get();
		client.close();
	}
	
	
	static Client client = null;
	static {
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("hadoop-slave4"), 9300));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	public static Page<Goods> queryGoods(RequestInfo requestInfo) {
		
		
		
		SearchRequestBuilder  builder = client.prepareSearch("smms").setTypes("goods");
		SearchResponse actionGet = null;
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		QueryBuilder nameFilter = null;
		QueryBuilder barcodeFilter = null;
		QueryBuilder priceFilter = null;
		QueryBuilder unitFilter = null;

		if(!requestInfo.getName().equals("")){
			nameFilter = QueryBuilders.termQuery("name", requestInfo.getName());
		
			qb.must(nameFilter);
		}
		
		if(!requestInfo.getBarcode().equals("")){
			String regexp="[0-9]*"+requestInfo.getBarcode()+"[0-9]*";
			System.out.println(regexp);
			barcodeFilter = QueryBuilders.regexpQuery("barcode", regexp);
			qb.must(barcodeFilter);
		}
		if(!requestInfo.getUnit().equals("")){
			unitFilter = QueryBuilders.termQuery("unit", requestInfo.getUnit());
			qb.must(unitFilter);
		}
		
		priceFilter = QueryBuilders.rangeQuery("price").gte(requestInfo.getMinprice()).lte(requestInfo.getMaxprice());
		qb.must(priceFilter);
		
		actionGet = builder
		.setQuery(qb)
		.setFrom(requestInfo.getFrom())
		.setSize(requestInfo.getSize())
		.execute()
		.actionGet();		
		SearchHits hits = actionGet.getHits();
		List<Goods> matchRsult = new ArrayList<>();
		
		for (SearchHit hit : hits.getHits()) {
			Map sou = hit.getSource();
			Goods goods = new Goods();
			goods.setId(Integer.parseInt(hit.getId()));
			goods.setBarCode((String) sou.get("barcode"));
			goods.setName((String) sou.get("name"));
			goods.setPrice(Float.parseFloat(sou.get("price")+""));
			goods.setUnit((String) sou.get("unit"));
			matchRsult.add(goods);
		}
		Page<Goods> page = new Page<>();
	
		page.setContent(matchRsult);
		page.setSize(requestInfo.getSize());
		page.setTotalElements(hits.getTotalHits());
		page.setCurPageNum(requestInfo.getFrom()/requestInfo.getSize()+1);
		
		
		
		return page;
		
	}
}
