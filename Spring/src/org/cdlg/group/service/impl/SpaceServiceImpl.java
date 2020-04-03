package org.cdlg.group.service.impl;

import java.util.List;
import java.util.Map;

import org.cdlg.group.dao.ReplyDao;
import org.cdlg.group.dao.SpaceDao;
import org.cdlg.group.entity.Reply;
import org.cdlg.group.entity.Space;
import org.cdlg.group.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service(value="spaceService")
public class SpaceServiceImpl implements SpaceService {

	@Autowired
	private SpaceDao spaceDao;
	@Autowired
	private ReplyDao replyDao;

	public ReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public SpaceDao getSpaceDao() {
		return spaceDao;
	}

	public void setSpaceDao(SpaceDao spaceDao) {
		this.spaceDao = spaceDao;
	}

	@Override
	public void addSpace(Space space) {
		// TODO 自动生成的方法存根
		spaceDao.addSpace(space);
	}

	//@Override
	//public Space findSpaceByUid() {
		// TODO 自动生成的方法存根
//		List<Space> spaceList = spaceDao.findSpaceByUid(uid);
//		for(int i=0;i<spaceList.size();i++){
//			Space space = spaceList.get(i);
//			List<Reply> replyList = replyDao.findReplyBySid(space.getId());
//			space.setReplyList(replyList);
//		}
//		return spaceDao.findSpaceByUid();
//	}
	@Override
	public List<Space> findSpace(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		List<Space> spaceList = spaceDao.findSpace(map);
		for(int i=0;i<spaceList.size();i++){
			Space space = spaceList.get(i);
			List<Reply> replyList = replyDao.findReplyBySid(space.getId());
			space.setReplyList(replyList);
		}
		return spaceList;

	}

	@Override
	public int spacePages(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		int rocount = this.spaceDao.spaceCount(map);
		if(rocount % 3 == 0)
			return rocount /3;
		return rocount /3+1;
	}

	@Override
	public List<Space> findfriendSpace(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		List<Space> spaceList = spaceDao.findfriendSpace(map);
		for(int i=0;i<spaceList.size();i++){
			Space space = spaceList.get(i);
			List<Reply> replyList = replyDao.findReplyBySid(space.getId());
			space.setReplyList(replyList);
		}
		return spaceList;
	}

	@Override
	public int spacefriendPages(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		int rocount = this.spaceDao.spacefriendCount(map);
		if(rocount % 3 == 0)
			return rocount /3;
		return rocount /3+1;
	}

	@Override
	public void addReply(Reply reply) {
		// TODO 自动生成的方法存根
		replyDao.addReply(reply);
	}

	@Override
	public int findReplyCount(Integer id) {
		// TODO 自动生成的方法存根
		return replyDao.findReplyCount(id);
	}

	@Override
	public void updateSpaceCount(Integer count) {
		// TODO 自动生成的方法存根
		spaceDao.updateSpaceCount(count);
	}


}
