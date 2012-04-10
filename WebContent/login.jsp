<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Krazyivan-Shop</title>
</head>
<body>
<f:view>
	<h:form>
		<h:panelGrid columns="2" >
			<h:outputText value="Email"></h:outputText>
			<h:inputText value="#{LoginController.kunde.email}" required="true"/>
		</h:panelGrid>
				<h:panelGrid columns="2">
			<h:outputText value="Passwort"></h:outputText>
			<h:inputSecret value="#{LoginController.kunde.password}" required="true"/>
		</h:panelGrid>
		<h:panelGroup>
			<h:commandButton action="#{LoginController.checkLogin}" value="save"/>
		</h:panelGroup>
	</h:form>
</f:view>
</body>
</html>