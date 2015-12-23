<!-- Left side column. contains the logo and sidebar -->
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <%--<div class="user-panel">--%>
            <%--<div class="pull-left image">--%>
                <%--<img src="<c:url value='/resources/common/AdminLTE/img/user2-160x160.jpg' />" class="img-circle" alt="User Image">--%>
            <%--</div>--%>
            <%--<div class="pull-left info">--%>
                <%--<p>Alexander Pierce</p>--%>
                <%--<!-- Status -->--%>
                <%--<a href="#"><i class="fa fa-circle text-success"></i> Online</a>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- search form (Optional) -->
        <%--<form action="#" method="get" class="sidebar-form">--%>
            <%--<div class="input-group">--%>
                <%--<input type="text" name="q" class="form-control" placeholder="Search...">--%>
              <%--<span class="input-group-btn">--%>
                <%--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>--%>
              <%--</span>--%>
            <%--</div>--%>
        <%--</form>--%>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">Options</li>
            <!-- Optionally, you can add icons to the links -->
            <li><a href="#" id="menu-order" url="order/view"><i class="fa fa-link"></i> <span>Order</span></a></li>
                <li><a href="#" id="menu-todo" url="todo/view"><i class="fa fa-link"></i> <span>Todo</span></a></li>
            <li><a href="#" id="menu-bus" url="bus/view"><i class="fa fa-link"></i> <span>Bus</span></a></li>
            <security:authorize access="hasRole('App_Admin')">
            <li><a href="#" id="menu-bill" url="bill/view"><i class="fa fa-link"></i> <span>Bill</span></a></li>
            </security:authorize>
        <%--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>--%>
            <%--<li class="treeview">--%>
                <%--<a href="#"><i class="fa fa-link"></i> <span>Multilevel</span> <i class="fa fa-angle-left pull-right"></i></a>--%>
                <%--<ul class="treeview-menu">--%>
                    <%--<li><a href="#">Link in level 2</a></li>--%>
                    <%--<li><a href="#">Link in level 2</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        </ul><!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>