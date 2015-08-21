<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>fanfou</title>
    <%@ include file="/WEB-INF/view/common/style.jsp" %>
</head>

<body class="skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="/WEB-INF/view/common/head.jsp"/>
    <jsp:include page="/WEB-INF/view/common/left.sidebar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Page Header
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Your Page Content Here -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <jsp:include page="/WEB-INF/view/common/footer.jsp"/>
    <jsp:include page="/WEB-INF/view/common/control.sidebar.jsp"/>
</div>
<%@ include file="/WEB-INF/view/common/script.jsp" %>
</body>
</html>
