package model;

import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoBackMap {

    private static Map<String, FXMLLoader> map = new HashMap<>();
    private static List<String> simpleNames = new ArrayList<>();

    public static FXMLLoader getLoader(Class context, User currentUser) {
        String key = context.getSimpleName();
        if (!simpleNames.contains(context.getSimpleName()))
            key += currentUser.getClass().getSimpleName();

        return map.get(key);
    }

    static {
        simpleNames.add("AdminMainHandler");
        simpleNames.add("TeacherMainHandler");
        simpleNames.add("StudentMainHandler");
        simpleNames.add("ParentMainHandler");

        simpleNames.add("DiscussionCommentsHandler");
        simpleNames.add("CreateHomeworkHandler");
        simpleNames.add("HomeworkReplyHandler");
        simpleNames.add("FamilyListHandler");
        simpleNames.add("TeacherListHandler");
        simpleNames.add("CreateStudentHandler");
        simpleNames.add("CreateParentHandler");
        simpleNames.add("CreateTeacherHandler");

        map.put("AdminMainHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/loginPane.fxml")));
        map.put("TeacherMainHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/loginPane.fxml")));
        map.put("StudentMainHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/loginPane.fxml")));
        map.put("ParentMainHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/loginPane.fxml")));

        map.put("HomeworkListHandlerAdmin", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneAdmin.fxml")));
        map.put("HomeworkListHandlerTeacher", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneTeacher.fxml")));
        map.put("HomeworkListHandlerStudent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneStudent.fxml")));
        map.put("HomeworkListHandlerParent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneParent.fxml")));

        map.put("AnnouncementListHandlerAdmin", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneAdmin.fxml")));
        map.put("AnnouncementListHandlerTeacher", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneTeacher.fxml")));
        map.put("AnnouncementListHandlerStudent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneStudent.fxml")));
        map.put("AnnouncementListHandlerParent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneParent.fxml")));

        map.put("DiscussionListHandlerAdmin", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneAdmin.fxml")));
        map.put("DiscussionListHandlerTeacher", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneTeacher.fxml")));
        map.put("DiscussionListHandlerStudent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneStudent.fxml")));
        map.put("DiscussionListHandlerParent", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneParent.fxml")));

        map.put("DiscussionCommentsHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/discussionHandler.fxml")));

        map.put("HomeworkRepliesListHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/homeworkHandler.fxml")));
        map.put("CreateHomeworkHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/homeworkHandler.fxml")));
        map.put("HomeworkReplyHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/homeworkHandler.fxml")));

        map.put("FamilyListHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneAdmin.fxml")));
        map.put("TeacherListHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/mainPaneAdmin.fxml")));

        map.put("CreateStudentHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/familyList.fxml")));
        map.put("CreateParentHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/familyList.fxml")));

        map.put("CreateTeacherHandler", new FXMLLoader(GoBackMap.class.getResource("/client/view/fxml/teacherList.fxml")));
    }
}
