package com.dodo.smms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dodo.smms.entity.Distance;
import com.dodo.smms.entity.Goods;
import com.dodo.smms.entity.Indent;
import com.dodo.smms.entity.IndentGroup;
import com.dodo.smms.entity.OrderBean;
import com.dodo.smms.entity.SuperMarket;
import com.dodo.smms.entity.WareHouse;
import com.dodo.smms.repository.DistanceRepository;
import com.dodo.smms.repository.IndentGroupRepository;
import com.dodo.smms.repository.IndentRepository;
import com.dodo.smms.repository.SuperMarketRepository;
import com.dodo.smms.repository.WareHouseRepository;
import com.dodo.smms.service.CalculationService;



public class CalculationServiceImpl implements CalculationService{

	@Autowired
	WareHouseRepository wRepository;
	
	@Autowired
	DistanceRepository dRepository;
	
	@Autowired
	SuperMarketRepository sRepository;
	
	@Autowired
	IndentRepository iRepository;
	
	@Autowired
	IndentGroupRepository gRepository;
	
	List<OrderBean> matrixs = new ArrayList<>();
	float[][] distanceCache;
	int rowNum;
	int colNum;
	public void init(){
		rowNum = Integer.parseInt(wRepository.count()+"")+2;
		colNum = Integer.parseInt(sRepository.count()+"")+2;
		distanceCache = new float[rowNum-2][colNum-2];
		
		List<Distance> ds = dRepository.findAll();
		for(Distance distance:ds){
			distanceCache[distance.getHouse().getId()-1][distance.getMarket().getId()-1]=distance.getDistance();
		}
		
		for(int i =0;i<rowNum-2;i++){
			for(int j =0;j<colNum-2;j++){
				System.out.print(distanceCache[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	@Override
	public void dealOrders(List<IndentGroup> groups) {
		//1 int[][]
		
		List<WareHouse> wareHouses = wRepository.findAll();
		List<OrderBean> maps = convertMaps(wareHouses,groups);
		List<OrderBean> resule = calculate(maps);
		saveStrategy(resule);
		terminateTask(groups);
	}

	
	private void terminateTask(List<IndentGroup> groups) {
		for(IndentGroup group:groups){
			group.setDealedTime(new Date());
			gRepository.save(group);
		}
	}

	private void saveStrategy(List<OrderBean> resule) {
		for(OrderBean bean:resule){
			float[][] result = bean.getResult();
			for(Indent indent:bean.getOrders()){
				int col = indent.getGid().getSm().getId()-1;
				int sum=0;
				for(int i =0;i<rowNum-2;i++){
					if(result[i][col]!=0){
						WareHouse wareHouse = wRepository.findOne(i+1);
						int n=0;
						for(Map.Entry<Goods,Integer> entry:wareHouse.getGoods_num().entrySet()){
							if(entry.getKey().getId()==indent.getGoods().getId()){
								System.out.println(entry.getKey());
								System.out.println(entry.getKey().equals(indent.getGoods()));
								entry.setValue(entry.getValue()-(int) result[i][col]);
								break;
							}
						}
						
						indent.getHouse().put(wareHouse, (int) result[i][col]);
						sum+=result[i][col];
						
						wRepository.save(wareHouse);
					}
				}
				indent.setRealNum(sum);
				
				
				iRepository.save(indent);
			}
		}
	}

	private List<OrderBean> calculate(List<OrderBean> maps) {
		List<OrderBean> resultBean = new ArrayList<>();
		
		for(OrderBean bean:maps){
			float[][] map = bean.getMap();	
			//1.计算差值
			float[][] resultMap = new float[rowNum-2][colNum-2];
			boolean finished = false;
			while(!finished ){
				int result = calculateDV(map);
				printMap(map);
				switch (result) {
					case 0:
						calculateMap(map,resultMap);
						break;
					case 1:
						dealOneRow(map,resultMap);
						bean.setResult(resultMap);
						finished=true;
						break;
					case 2:
						dealOneCol(map,resultMap);
						bean.setResult(resultMap);
						finished=true;
						break;
					case -1:
						break;
		
				}
			}
			//2.定位
			
			resultBean.add(bean);
		}
		return resultBean;
	}


	

	private void dealOneCol(float[][] map, float[][] resultMap) {
		
		for(int j=0;j<colNum-2;j++){
			if(map[rowNum-2][j]!=0){
				while(true){
					int c = 0;
					float min = Float.MAX_VALUE;
					for(int i=0;i<rowNum-2;i++){
						if(map[i][colNum-2]!=0&&min>map[i][j]){
							min=map[i][j];
							c=i;
						}
					}
					if(min==Float.MAX_VALUE){
						return;
					}
					if(map[c][colNum-2]>=map[rowNum-2][j]){ //供大于需
						resultMap[c][j] =map[rowNum-2][j];
						return;
					}else{   //供不应求
						resultMap[c][j] =map[c][colNum-2];
						map[rowNum-2][j]-=map[c][colNum-2];
						map[c][colNum-2] = 0;
					}
				}
			}
		}
		
		
	}

	private void dealOneRow(float[][] map, float[][] resultMap) {

		float sum = 0;
		for(int i=0;i<colNum-2;i++){
			sum+=map[rowNum-2][i];
		}
		int n=0;
		for(int i=0;i<rowNum-2;i++){
			if(map[i][colNum-2]!=0){
				n=i;
			}
		}
		if(map[n][colNum-2]>=sum){
			for(int i=0;i<colNum-2;i++){
				if(map[rowNum-2][i]!=0){
					resultMap[n][i] = map[rowNum-2][i];
				}
			}
		}else{
			float scale = map[n][colNum-2]/sum;
			for(int i=0;i<colNum-2;i++){
				if(map[rowNum-2][i]!=0){
					resultMap[n][i] = (int)map[rowNum-2][i]*scale;
				}
			}
		}
		
	}

	private void calculateMap(float[][] map, float[][] resultMap) {
		//1 找到行/列
		int o = 0,n = 0;
		int x = 0;int y=0;
		float max = 0;
		
		for(int i=0;i<rowNum-2;i++){
			if(map[i][colNum-1]>max){
				max = map[i][colNum-1];
				o=1;
				n=i;
			}
		}
		
		for(int i=0;i<colNum-2;i++){
			if(map[rowNum-1][i]>max){
				max = map[rowNum-1][i];
				o=0;
				n=i;
			}
		}
		//2.找到点
		float min=Float.MAX_VALUE;
		
		if(o==0){ 
			y=n;
			for(int i=0;i<colNum-2;i++){
				if(map[i][n]<min&&map[i][n]!=0){
					min=map[i][n];
					x=i;
				}
			}
		}else{
			x=n;
			for(int i=0;i<rowNum-2;i++){
				if(map[n][i]<min&&map[n][i]!=0){
					min=map[n][i];
					y=i;
				}
			}
		}
		if(map[x][colNum-2]>map[rowNum-2][y]){
			resultMap[x][y] += map[rowNum-2][y];
			map[x][colNum-2]-=map[rowNum-2][y];
			map[rowNum-2][y]=0;
			map[rowNum-1][y]=0;
		
		}else{
			resultMap[x][y]+=map[x][colNum-2];
			map[rowNum-2][y]-=map[x][colNum-2];
			map[x][colNum-2]=0;
			map[x][colNum-1]=0;
		}
			
		
	}

	private void printMap(float[][] map) {

		for(int i=0;i<rowNum;i++){
			for(int j=0;j<colNum;j++){
				System.out.print(map[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	/**
	 * 正常结束返回0
	 * 无可处理元素返回-1
	 * 只有一列返回2
	 * 只有一行返回1
	 * 
	 * @param map
	 * @return
	 */
	private int calculateDV(float[][] map) {
		int row=0;
		for(int i=0;i<rowNum-2;i++){
			if(map[i][colNum-2]==0){
				continue;
			}
			float min1 = 0F;
			float min2 = Float.MAX_VALUE;
			for(int j=0;j<colNum-2;j++){
				if(map[i][j]!=0){
					if(min1==0F||min1>map[i][j]){
						if(min1!=0F){
							min2=min1;
						}
						min1 = map[i][j];
					}
					
					if(min1!=0F){
						if(min1<map[i][j]&&min2>map[i][j]){
							min2 = map[i][j];
						}
					}
				}
			}
			if(min1!=0F&&min2!=Float.MAX_VALUE){
				row++;
				map[i][colNum-1] = (float)(Math.round((min2 - min1)*100))/100;
			}else if(min1!=0&&min2==Float.MAX_VALUE){
				return 1;
			}
		}
		int col=0;
		for(int j=0;j<colNum-2;j++){
			if(map[rowNum-2][j]==0){
				continue;
			}
			float min1 = 0F;
			float min2 = Float.MAX_VALUE;
			for(int i=0;i<rowNum-2;i++){
				if(map[i][j]!=0){
					if(min1==0F||min1>map[i][j]){
						if(min1!=0F){
							min2=min1;
						}
						min1 = map[i][j];
					}
					
					if(min1!=0F){
						if(min1<map[i][j]&&min2>map[i][j]){
							min2 = map[i][j];
						}
					}
				}
			}
			if(min1!=0F&&min2!=Float.MAX_VALUE){
				map[rowNum-1][j] = (float)(Math.round((min2 - min1)*100))/100;
				col++;
			}else if(min1!=0&&min2==Float.MAX_VALUE){
				return 2;
			}
		}
		if(row==0){ return -1;}
		if(row==1){return 1;}
		if(col==1){return 2;}
		return 0;
	}

	private List<OrderBean> convertMaps(List<WareHouse> wareHouses, List<IndentGroup> groups) {
		
		//缓存距离
		//
		
		
		Map<Integer,HashMap<WareHouse,Integer>> colmap = new HashMap<>();
		Map<Integer,HashMap<SuperMarket,Integer>> rowmap = new HashMap<>();
		for(IndentGroup group:groups){
			
			for(Indent indent:group.getOrder()){
				
				Goods goods = indent.getGoods();
				int needNum = indent.getNeedNum();
				SuperMarket sm = group.getSm();
			    OrderBean bean = searchBeanByGid(goods.getId());
			    if(bean == null){
			    	bean = new OrderBean();
			    	bean.setId(goods.getId());	
			    	bean.getOrders().add(indent);
			    	matrixs.add(bean);
			    }else{
			    	List<Indent> is =bean.getOrders();
			    	is.add(indent);
			    }
				
				HashMap<SuperMarket,Integer> map = rowmap.get(goods.getId());
				if(map==null){
					map = new HashMap<>();
					map.put(sm, needNum);
				}else{
					if(map.get(sm)==null)
						map.put(sm, needNum);
					else{
						map.put(sm, map.get(sm)+needNum);
					}
				}
				rowmap.put(goods.getId(), map);
			}
		}
		
		
		for(WareHouse house:wareHouses){
			for(Map.Entry<Goods, Integer> entry:house.getGoods_num().entrySet()){
				
				Goods goods = entry.getKey();
				HashMap<WareHouse,Integer> map = colmap.get(goods.getId());
				if(map==null)
					map = new HashMap<>();
				map.put(house, entry.getValue());				
				colmap.put(goods.getId(), map);
			}
		}
		
		for(Map.Entry<Integer, HashMap<SuperMarket,Integer>> entry:rowmap.entrySet()){
			HashMap<SuperMarket,Integer> map = entry.getValue();
			Integer goods = entry.getKey();
			OrderBean bean = searchBeanByGid(goods);
			HashMap<WareHouse, Integer> col = colmap.get(goods);
			float[][] matrix = new float[rowNum][colNum];
			for(Map.Entry<WareHouse, Integer> w:col.entrySet()){
				for(Map.Entry<SuperMarket, Integer> s:map.entrySet()){
					int t1=w.getKey().getId()-1;
					int t2 = s.getKey().getId()-1;
					matrix[t1][t2] = distanceCache[t1][t2];
					matrix[t1][colNum-2] = w.getValue();
					matrix[rowNum-2][t2] = s.getValue();
				}
			}
			
			bean.setMap(matrix);		
		}
		return matrixs;
	}

	private OrderBean searchBeanByGid(int id) {
		for(OrderBean bean:matrixs){
			if(bean.getId()==id){
				return bean;
			}
		}
		return null;
	}

}
