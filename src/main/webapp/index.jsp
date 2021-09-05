<%@ page contentType="text/html; charset=UTF-8"%>


<% 
//request는 내장객체다.
//System.out.println(request.getContextPath()); //이거는 다르다. 이것과 boardwritefrom.jsp파일에 있는 것과 같다.${pageContext.request.contextPath}

//System.out.println(request.getServletContext().getContextPath()); //servletcontext를 얻어서 가져온것
//System.out.println(application.getContextPath()); ///ervletcontext를 얻어서 가져온것
//위와 아래는 같다. 둘다 같은 객체다. 둘다 결과는 같다.

response.sendRedirect(application.getContextPath()+"/ch01/content"); 

%>
