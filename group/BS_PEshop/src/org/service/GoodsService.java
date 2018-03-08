package org.service;

import java.io.File;
import java.util.List;

import org.model.Comment;
import org.model.Goods;

public interface GoodsService {
	
	public void addGoods(Goods goods);
	
	public void updateGoods(Goods goods);
	
	public void updatePic(Integer id, File pic);
	
	public void deleteGoods(Integer id);
	
	public Goods findGoodsById(Integer id);
	
	public List<Goods> findGoodsByProperty(String name, Object value);
	
	public List<Goods> findAllGoods();
	
	public void addGoodsComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public void deleteComment(Integer id);
	
	public Comment getCommentById(Integer id);
	
	public List<Comment> getGoodsComments(Goods goods);
	
	public List<Comment> getUsersGoodsComments(String theme);
}
