<%@ include file="/WEB-INF/view/common/taglib.jsp"%>

<header id="header" class="navbar-fixed-top header" role="banner">

	<div class="logo_area">
		<a href="http://www.cisco.com" target="_blank"><div class="cisco"></div></a>
		<div class="app_name">Maestro</div>
		<div class="head_sub_title">CSRA</div>
	</div>
	
	<div class="nav_area">
		<div>
			<div class="nav_left"></div>
			<div class="nav_middle"></div>
			<div class="nav_right"></div>
		</div>

		<div class="nav_menu">
			<ul class="nav_ul">
				<li><a href="javascript:void(0);" class="main_nav main_nav_select" id="menu/dashboard"><i class="fa fa-home fa-lg"></i>&nbsp;&nbsp;Dashboard</a></li>
				<security:authorize access="hasRole('App_Admin')">
					<li><a href="javascript:void(0);" class="main_nav" id="menu/group"><i class="fa fa-group"></i>&nbsp;&nbsp;Group</a></li>
				</security:authorize>
				<c:if test="${sessionScope.showService==true}">
					<li><a href="javascript:void(0);" class="main_nav" id="menu/service"><i class="fa fa-cloud"></i>&nbsp;&nbsp;Service</a></li>
				</c:if>
				<li><a href="javascript:void(0);" class="main_nav" id="menu/policy"><i class="fa fa-gavel"></i>&nbsp;&nbsp;Policy</a></li>
				<!-- <li><a href="javascript:void(0);" class="main_nav" id="menu/policy/template"><i class="fa fa-clipboard"></i>&nbsp;&nbsp;Policy Template</a></li> -->
			</ul>
		</div>
	</div>

	<div class="inf_area">
		<div class="user_inf">
			<div>Welcome,<security:authentication property="principal.username" /></div>
			<!-- 
				<div class="side_line"></div>
				<div>
					<a href="logout">Log Out</a>
				</div> 
			-->
		</div>
		<div class="tool">
			<a id="refreshBtn" url="menu/dashboard" href="javascript:void(0);" class="refresh_icon"></a> 
			<a id="systemHelp" href="javascript:void(0);" class="help_icon" data-toggle="modal" data-target="#modalAbout"></a>
		</div>
	</div>
</header>
<jsp:include page="/WEB-INF/view/common/about.jsp" />
