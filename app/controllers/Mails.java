package controllers;

import org.apache.commons.mail.EmailAttachment;

import models.BaseModel;
import models.Department;
import models.Order;
import models.OrderHistory;
import models.Teacher;
import play.mvc.Mailer;
import play.mvc.Scope.RenderArgs;

public class Mails extends Mailer {
    
    public static void noticeModifyOrder(Order o){
        OrderHistory oh = OrderHistory.find("order_message = ? order by createdAt desc", o).first();
        Department d = Department.find("state = ? and parentCode = ?", BaseModel.ACTIVE,0l).first();
        setSubject("订单 %s -- %s", o.name,o.lesson.lessonTimeType);
        
        addRecipient(d.leader.email);
        setFrom("zbh <zbhlove100@gmail.com>");
        /*EmailAttachment attachment = new EmailAttachment();
        attachment.setDescription("A pdf document");
        attachment.setPath(Play.getFile("rules.pdf").getPath());
        addAttachment(attachment);*/
        
        send(o,d,oh);
    }
}
