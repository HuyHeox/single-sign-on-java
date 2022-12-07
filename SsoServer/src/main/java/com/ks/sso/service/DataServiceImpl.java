package com.ks.sso.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.ks.sso.config.Config;
import com.ks.sso.model.LoggedInUser;
import com.ks.sso.model.Student;
import com.ks.sso.model.Teacher;
import com.ks.sso.model.UserInfo;
import com.ks.sso.model.UserSession;
import com.ks.sso.model.basic.IBasic;
import com.ks.sso.util.ServerUtils;

public class DataServiceImpl {
	public DataServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public IBasic getUserByEmail(String email) {
		IBasic user = ofy().load().type(Teacher.class).filter("userName", email).first().now();
		if (user == null && email.compareTo(email.toLowerCase()) != 0) {
			user = ofy().load().type(Teacher.class).filter("userName", email.toLowerCase()).first().now();
		}
		if (user == null) {
			user = ofy().load().type(Student.class).filter("email", email).first().now();
			if (user == null && email.toLowerCase().contains("sis.hust.edu.vn")) {
				String studentId = Student.getStudentId(email);
				if (studentId.length() > 1 && !studentId.contains("@")) {
					user = ofy().load().type(Student.class).filter("studentId", studentId).first().now();
					if (user != null) {
						((Student) user).setEmail(email.toLowerCase());
						ofy().save().entity(user).now();
					}
				}
			}
		}
		if (user == null) {
			user = ofy().load().type(Student.class).filter("relatedEmail", email).first().now();
		}
		if (user == null) {
			user = ofy().load().type(Teacher.class).filter("relatedEmail", email).first().now();
		}
		if (user == null) {
			user = ofy().load().type(Teacher.class).filter("workEmail", email).first().now();
		}
		if (user == null) {
			user = ofy().load().type(UserInfo.class).filter("email", email).first().now();
		}
		return user;
	}
	
	public UserSession setUserSession(IBasic user, String sessionId, boolean loadMoreData) {
		List<UserSession> sessions = new ArrayList<UserSession>(ofy().load().type(UserSession.class).filter("sessionId", sessionId).list());
		UserSession session = null;
		if (sessions.isEmpty()) {
			session = getUserSesson(user, sessionId);
			if (session != null) {
				user = session.getUser();
			}
		}
		else if (!sessions.isEmpty()) {
			session = sessions.get(0);
			if (sessions.size() > 1) {
				sessions.remove(session);
				ofy().delete().entities(sessions).now();
			}
			if (user == null) {
				if (session.getType().equalsIgnoreCase(Teacher.class.getSimpleName())) {
					user = ofy().load().type(Teacher.class).id(session.getUserId()).now();
				}
				else if (session.getType().equalsIgnoreCase(Student.class.getSimpleName())) {
					user = ofy().load().type(Student.class).id(session.getUserId()).now();
				}
				else if (session.getType().equalsIgnoreCase(UserInfo.class.getSimpleName())) {
					user = ofy().load().type(UserInfo.class).id(session.getUserId()).now();
				}
//				else if (session.getType().equalsIgnoreCase(Admission.class.getSimpleName())) {
//					user = ofy().load().type(Admission.class).id(session.getUserId()).now();
//				}
			}
			session.setUser(user);
		}
		if (session != null && session.getUser() != null) {
			user = session.getUser();
			Long now = ServerUtils.getCurrentTime();
			if (user instanceof Teacher) {
//				((Teacher)user).setLastLoggedIn(now);
//				ofy().save().entity(user).now();
				((Teacher)user).setSessionId(sessionId);
//				((Teacher)user).setStaffDetail(this.getStaffDetail(((Teacher)user).getId()));
//				if (loadMoreData) {
//					loadMoreInfo((Teacher)user);
//				}
			}
			else if (user instanceof Student) {
//				((Student)user).setLastLoggedIn(now);
//				ofy().save().entity(user).now();
				((Student)user).setSessionId(sessionId);
//				if (loadMoreData) {
//					loadMoreInfo((Student)user);
//				}
			}
			else if (user instanceof UserInfo) {
				((UserInfo)user).setLastLoggedIn(now);
				ofy().save().entity(user).now();
				((UserInfo)user).setSessionId(sessionId);
//				if (loadMoreData) {
//					loadMoreInfo((UserInfo)user);
//				}
			}
//			else if (user instanceof Admission) {
////				((Admission)user).setLastLoggedIn(now);
////				ofy().save().entity(user).now();
//				((Admission)user).setSessionId(sessionId);
//				if (loadMoreData) {
//					loadMoreInfo((Admission)user);
//				}
//			}
		}
		return session;
	}
	
	public UserSession getUserSesson(IBasic user, String sessionId) {
		LoggedInUser loggedIn = ofy().load().type(LoggedInUser.class).id(sessionId).now();
		if (loggedIn != null) {
			if (user == null) {
				long userId = 0;
				try {
					userId = Long.parseLong(loggedIn.getUserId());
				} catch (Exception e) {
				}
				if (userId > 0) {
					user = ofy().load().type(Teacher.class).id(userId).now();
					if (user == null) {
						user = ofy().load().type(Student.class).id(userId).now();
						if (user == null) {
							user = ofy().load().type(UserInfo.class).id(userId).now();
//							if (user == null) {
//								user = ofy().load().type(Admission.class).id(userId).now();
//							}
						}
					}
				}
			}
			ofy().delete().entity(loggedIn).now();
		}
		if (user != null) {
			String name = user instanceof Teacher ? ((Teacher)user).getFullName() 
					: user instanceof Student ? ((Student)user).getFullName() 
					: user instanceof UserInfo ? ((UserInfo)user).getFullName() : Config.NULL_TXT; 
//					: user instanceof Admission ? ((Admission)user).getFullName() : Config.NULL_TXT;   
//			log.warning("user "+ user +" user.getClass() "+ user.getClass() );
			String simpleName = user.getClass() != null ? user.getClass().getSimpleName() : "";
//			log.warning("simpleName "+ simpleName+ " user.getId() "+ user.getId());
			Long userId = (long)user.getId();
//			log.warning("userId "+ userId);
			UserSession session = new UserSession(userId, name, simpleName, sessionId, UserSession.OS_WEB, 0);
			ofy().save().entity(session).now();
			session.setUser(user);
			return session;
		}
		return null;
	}
}
