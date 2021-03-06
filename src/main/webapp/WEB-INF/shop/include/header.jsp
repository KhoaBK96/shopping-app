<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:set var="context" value="${pageContext.request.contextPath}" />
<!-- Header Section Begin -->
<header class="header">
  <div class="header__top">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 col-md-7">
          <div class="header__top__left">
            <p>Free shipping, 30-day return or refund guarantee.</p>
          </div>
        </div>
        <div class="col-lg-6 col-md-5">
          <div class="header__top__right">
            <div class="header__top__links">
            	<c:if test="${sessionScope.USER != null}"><a href="<c:out value="${context}/Signout"/>">Sign out</a></c:if>
	            <c:if test="${sessionScope.USER == null}"><a href="<c:out value="${context}/Signin"/>">Sign in</a></c:if>
	           
            </div>         
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-3">
        <div class="header__logo">
          <a href="<c:out value="${context}/Home"/>"><img src="shop/img/logo.png" alt="" /></a>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <nav class="header__menu mobile-menu">
          <ul>
            <li><a href="<c:out value="${context}/Home"/>">Home</a></li>
            <li class="active"><a href="<c:out value="${context}/Shop"/>">Shop</a></li>
            <li><a href="<c:out value="${context}/Contact"/>">Contacts</a></li>
          </ul>
        </nav>
      </div>
      <div class="col-lg-3 col-md-3">
        <div class="header__nav__option">
          <a href="<c:out value="${context}/Shoppingcart"/>"><img src="shop/img/icon/cart.png" alt="" /> <span>0</span></a>
          <div class="price"><c:out value="${TOTAL}"/>$</div>
        </div>
      </div>
    </div>
    <div class="canvas__open"><i class="fa fa-bars"></i></div>
  </div>
</header>
<!-- Header Section End -->
