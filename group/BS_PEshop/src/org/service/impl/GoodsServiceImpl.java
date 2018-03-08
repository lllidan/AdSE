package org.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.dao.CommentDAO;
import org.dao.GoodsDAO;
import org.hibernate.Hibernate;
import org.model.Comment;
import org.model.Goods;
import org.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDAO goodsDao;
	private CommentDAO commentDao;

	public GoodsDAO getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDAO goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public CommentDAO getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}
	
	
	public void addGoods(Goods goods) {
		this.goodsDao.save(goods);
	}
	public void updateGoods(Goods goods) {
		//goods.setPic(goodsDao.findById(goods.getId()).getPic());
		this.goodsDao.update(goods);
	}
	
	public void updatePic(Integer id, File pic) {
		Goods goods = goodsDao.findById(id);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(pic);
			goods.setPic(Hibernate.createBlob(fis));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		goodsDao.update(goods);
	}
	
	public void deleteGoods(Integer id) {
		Goods goods = this.goodsDao.findById(id);
		this.goodsDao.delete(goods);
	}
	public Goods findGoodsById(Integer id) {
		return this.goodsDao.findById(id);
	}
	public List<Goods> findGoodsByProperty(String name, Object value) {
		return this.goodsDao.findByProperty(name, value);
	}
	public List<Goods> findAllGoods() {
		return this.goodsDao.findAll();
	}
	
	
	public void addGoodsComment(Comment comment) {
		this.commentDao.save(comment);
	}
	public void updateComment(Comment comment) {
		this.commentDao.update(comment);
	}
	public void deleteComment(Integer id) {
		Comment comment = this.commentDao.findById(id);
		this.commentDao.delete(comment);
	}
	public Comment getCommentById(Integer id) {
		return this.commentDao.findById(id);
	}
	public List<Comment> getGoodsComments(Goods goods) {
		return this.commentDao.findByGoodsId(goods);
	}
	public List<Comment> getUsersGoodsComments(String theme) {
		return this.commentDao.findByTheme(theme);
	}
	
}
