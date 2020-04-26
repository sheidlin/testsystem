package com.zust.stkdy.testsystem.config.handler;

import com.zust.stkdy.testsystem.config.annotation.TokenToStudent;
import com.zust.stkdy.testsystem.entity.Student;
import com.zust.stkdy.testsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
public class TokenToStudentMethodArgumentReslover  implements HandlerMethodArgumentResolver {
    @Autowired
    private StudentService studentService;

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToStudent.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToStudent.class) instanceof TokenToStudent) {
            Student student=null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                student = studentService.findStudentByToken(token);
            }
            return student;
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
}
