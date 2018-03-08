package org.action.admin;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.model.Comment;
import org.model.Goods;
import org.service.GoodsService;
import org.tools.Convert;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GoodsManAction extends ActionSupport {

	private Goods goods;
	private Comment comment;
	private GoodsService goodsService;
	private File pic_file;
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public File getPic_file() {
		return pic_file;
	}
	public void setPic_file(File pic_file) {
		this.pic_file = pic_file;
	}

	
	//��ʾȫ����Ʒ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showGoods() throws Exception {
		List<Goods> goodsList = this.goodsService.findAllGoods();
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("goodsList", goodsList);
		return SUCCESS;
	}
	
	//��ʾָ����Ʒ����ϸ��Ϣ
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showGoodsInfo() throws Exception {
		Goods goodsInfo = this.goodsService.findGoodsById(this.goods.getId());
		
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("goodsInfo", goodsInfo);
		request.put("test", goodsInfo == null ? "����" : "�޸�");
		request.put("show", goodsInfo == null ? "add" : "update");
		return SUCCESS;
	}
	
	//������Ʒ��Ϣ
	public String saveGoods() throws Exception {
		this.goods.setSale(new Integer(0));
		this.goodsService.addGoods(this.goods);
		this.goodsService.updatePic(this.goods.getId(), pic_file);
		return SUCCESS;
	}
	
	//��ʾ��ƷͼƬ
	public String getGoodsPic() throws Exception {
		Goods goodsInfo = this.goodsService.findGoodsById(this.goods.getId());
		
		byte[] pic = null;
		if(goodsInfo.getPic() != null && goodsInfo.getPic().length() > 0) {
			pic =  Convert.blobToBytes(goodsInfo.getPic());
		}
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/jpeg");
		//�õ������
		ServletOutputStream os = response.getOutputStream();
		
		if(pic != null && pic.length > 0){
			for(int i = 0;i < pic.length; i++){
				os.write(pic[i]);
			}
		}
		return NONE;
	}
	
	//������Ʒ��Ϣ
	public String updateGoods() throws Exception {
		this.goodsService.updateGoods(this.goods);
		this.goodsService.updatePic(this.goods.getId(), this.pic_file);
		return SUCCESS;
	}
	
	//ɾ����Ʒ��Ϣ
	public String deleteGoods() throws Exception {
		this.goodsService.deleteGoods(this.goods.getId());
		return SUCCESS;
	}
	
	//չʾ������Ϣ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showComments() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		
		List<Comment> comments = this.goodsService.getUsersGoodsComments("��Ʒ����");
		request.put("comments", comments);
		return SUCCESS;
	}
	
	//չʾ��������
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String showCommentInfo() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		Comment commentInfo = this.goodsService.getCommentById(this.comment.getId());
		request.put("commentInfo", commentInfo);
		return SUCCESS;
	}
	
	//�ظ�����
	public String addComment() throws Exception {
		this.goodsService.addGoodsComment(this.comment);
		return SUCCESS;
	}
	
	//ɾ������
	public String deleteComment() throws Exception {
		this.goodsService.deleteComment(this.comment.getId());
		return SUCCESS;
	}
}
