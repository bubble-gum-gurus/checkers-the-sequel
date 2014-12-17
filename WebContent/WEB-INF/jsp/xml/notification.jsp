<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<checkers>
<status>success</status>
 <c:if test="${notification.status == 'REFUSED'}">
 	<refused id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" challenge="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'ISSUED'}">
 	<issued id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" challenge="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'ACCEPTED'}">
 	<accepted id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" challenge="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'TURN'}">
 	<turn id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'TIED'}">
 	<tied id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'WON'}">
 	<won id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'CONCEDED'}">
 	<conceded id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'LOSS'}">
 	<loss id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
 <c:if test="${notification.status == 'STARTED'}">
 	<started id="${notification.id}" version="${notification.version}" recipient="${notification.recipient.id}" seen="${notification.seen}" game="${notification.other}" />
 </c:if>
</checkers>