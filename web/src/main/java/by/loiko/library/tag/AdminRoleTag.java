package by.loiko.library.tag;

import by.loiko.library.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class AdminRoleTag extends TagSupport {
    private static final String USER_PARAM = "user";

    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute(USER_PARAM);
        if (user != null && user.getRoleId() == 3){
            return EVAL_BODY_INCLUDE;
        }else {
            return SKIP_BODY;
        }
    }
}
