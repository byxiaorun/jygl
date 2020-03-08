<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="pagin">
    <div class="message">
        <i class="blue"><%=p.getRecordCount()%></i>条记录，当前显示第&nbsp;<i class="blue"><%=p.getCurrentPage()%>&nbsp;</i>页，共&nbsp;<i class="blue" ><%=p.getTotalPage()%>&nbsp;</i>页
    </div>
   &nbsp;&nbsp;
    <ul class='paginList' id='pagelist'>
        <%
            if(p.getCurrentPage()>1){
        %>
        <li class='paginItem'><a href="?action=list&page=1" ><span class='pagefirst'></span></a></li>
        <li class='paginItem'><a href="?action=list&page=<%=p.getCurrentPage()-1%>" ><span class='pagepre'></span></a></li>
        <%
            }else{
        %>
        <li class='paginItem current'><a href="javascript:;" ><span class='pagefirst_disable'></span></a></li>
        <li class='paginItem current'><a href="javascript:;" ><span class='pagepre_disable'></span></a></li>
        <%
            }
        %>


    <%
        int interval=2;
        int start=Math.max(1,p.getCurrentPage()-interval);
        int end=Math.min(p.getCurrentPage()+interval,p.getTotalPage());

        if (p.getCurrentPage() < interval + 1) {
            end = (2 * interval + 1) > p.getTotalPage() ? p.getTotalPage() : (2 * interval + 1);
        }

        if ((p.getCurrentPage() + interval) >= p.getTotalPage()) {
            start = (p.getTotalPage() - 2 * interval) < 1 ? 1 : (p.getTotalPage() - 2 * interval);

        }
        for (int  j = start; j < end + 1; j++) {
            if (j == p.getCurrentPage()) {
                %>
                <li class='paginItem current'><a href='javascript:;'><%=j%></a></li>
            <% } else { %>
                <li class='paginItem'><a href="?action=list&page=<%=j%>"><%=j%></a></li>
           <% }
        }
    %>
    <% if(p.getCurrentPage()<p.getTotalPage()){ %>
    &nbsp;&nbsp;&nbsp;
        <li class='paginItem'><a href="?action=list&page=<%=p.getCurrentPage()+1%>" ><span class='pagenext'></span></a></li>
        <li class='paginItem'><a href="?action=list&page=<%=p.getTotalPage()%>" ><span class='pagelast'></span></a></li>
    <% }else { %>
    &nbsp;&nbsp;<li class='paginItem current'><a href="javascript:;" ><span class='pagenext_disable'></span></a></li>
        <li class='paginItem current'><a href="javascript:;" ><span class='pagelast_disable'></span></a></li>
        <%}%>

    </ul>
</div>