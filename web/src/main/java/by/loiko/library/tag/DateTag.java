package by.loiko.library.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

/***
 Author: Aliaksei Loika
 Date: 14.12.2017
 ***/
public class DateTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        LocalDate date = LocalDate.now();

        JspWriter out = pageContext.getOut();

        try {
            out.write(date.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
