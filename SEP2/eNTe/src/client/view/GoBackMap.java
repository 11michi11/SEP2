package client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoBackMap {

    private static Map<String, String> map = new HashMap<>();
    private static List<String> simpleNames = new ArrayList<>();

    public static String getLoader(Class context, String currentUserType) {
        String key = context.getSimpleName();
        if (!simpleNames.contains(key))
            key += currentUserType;

        return map.get(key);
    }

    static {
        simpleNames.add("AdminMainHandler");
        simpleNames.add("TeacherMainHandler");
        simpleNames.add("StudentMainHandler");
        simpleNames.add("ParentMainHandler");

        simpleNames.add("CreateHomeworkHandler");
        simpleNames.add("HomeworkReplyHandler");
        simpleNames.add("FamilyListHandler");
        simpleNames.add("TeacherListHandler");
        simpleNames.add("CreateStudentHandler");
        simpleNames.add("CreateParentHandler");
        simpleNames.add("CreateTeacherHandler");
        simpleNames.add("CreateDiscussionHandler");
        simpleNames.add("CreateAnnouncementHandler");
        simpleNames.add("HomeworkRepliesListHandler");

        map.put("AdminMainHandler", "/client/view/fxml/loginPane.fxml");
        map.put("TeacherMainHandler", "/client/view/fxml/loginPane.fxml");
        map.put("StudentMainHandler", "/client/view/fxml/loginPane.fxml");
        map.put("ParentMainHandler", "/client/view/fxml/loginPane.fxml");

        map.put("HomeworkListHandlerAdministrator", "/client/view/fxml/mainPaneAdmin.fxml");
        map.put("HomeworkListHandlerTeacher", "/client/view/fxml/mainPaneTeacher.fxml");
        map.put("HomeworkListHandlerStudent", "/client/view/fxml/mainPaneStudent.fxml");
        map.put("HomeworkListHandlerParent", "/client/view/fxml/mainPaneParent.fxml");

        map.put("AnnouncementListHandlerAdministrator", "/client/view/fxml/mainPaneAdmin.fxml");
        map.put("AnnouncementListHandlerTeacher", "/client/view/fxml/mainPaneTeacher.fxml");
        map.put("AnnouncementListHandlerStudent", "/client/view/fxml/mainPaneStudent.fxml");
        map.put("AnnouncementListHandlerParent", "/client/view/fxml/mainPaneParent.fxml");

        map.put("DiscussionListHandlerAdministrator", "/client/view/fxml/mainPaneAdmin.fxml");
        map.put("DiscussionListHandlerTeacher", "/client/view/fxml/mainPaneTeacher.fxml");
        map.put("DiscussionListHandlerStudent", "/client/view/fxml/mainPaneStudent.fxml");
        map.put("DiscussionListHandlerParent", "/client/view/fxml/mainPaneParent.fxml");

        map.put("DiscussionCommentsHandlerAdministrator", "/client/view/fxml/discussionHandler.fxml");
        map.put("DiscussionCommentsHandlerTeacher", "/client/view/fxml/discussionHandler.fxml");
        map.put("DiscussionCommentsHandlerStudent", "/client/view/fxml/mainPaneStudent.fxml");
        map.put("DiscussionCommentsHandlerParent", "/client/view/fxml/mainPaneParent.fxml");

        map.put("CreateDiscussionHandler", "/client/view/fxml/discussionHandler.fxml");

        map.put("CreateAnnouncementHandler", "/client/view/fxml/announcementHandler.fxml");

        map.put("HomeworkRepliesListHandler", "/client/view/fxml/homeworkHandler.fxml");
        map.put("CreateHomeworkHandler", "/client/view/fxml/homeworkHandler.fxml");
        map.put("HomeworkReplyHandler", "/client/view/fxml/mainPaneStudent.fxml");

        map.put("FamilyListHandler", "/client/view/fxml/mainPaneAdmin.fxml");
        map.put("TeacherListHandler", "/client/view/fxml/mainPaneAdmin.fxml");

        map.put("CreateStudentHandler", "/client/view/fxml/familyList.fxml");
        map.put("CreateParentHandler", "/client/view/fxml/familyList.fxml");

        map.put("CreateTeacherHandler", "/client/view/fxml/teacherList.fxml");

        map.put("ReplyViewHandler", "/client/view/fxml/homeworkRepliesList.fxml");
    }
}
