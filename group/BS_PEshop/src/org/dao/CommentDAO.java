package org.dao;

import java.util.List;

import org.model.Comment;
import org.model.Goods;

public interface CommentDAO {
	
	public void save(Comment transientInstance);
	
	public void update(Comment transientInstance);

	public void delete(Comment persistentInstance);

	public Comment findById(Integer id);
	
	public List<Comment> findByGoodsId(Goods instance);

	public List<Comment> findByExample(Comment instance);

	public List<Comment> findByProperty(String propertyName, Object value);

	public List<Comment> findByTheme(Object theme);

	public List<Comment> findByContent(Object content);

	public List<Comment> findByAnonymity(Object anonymity);

	public List<Comment> findAll();

	public Comment merge(Comment detachedInstance);

	public void attachDirty(Comment instance);

	public void attachClean(Comment instance);
}
